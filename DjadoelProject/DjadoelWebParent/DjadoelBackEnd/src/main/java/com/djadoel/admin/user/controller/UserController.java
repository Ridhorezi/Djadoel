package com.djadoel.admin.user.controller;

import java.io.IOException;
import java.util.List;

import com.djadoel.admin.FileUploadUtil;
import com.djadoel.admin.user.UserNotFoundException;
import com.djadoel.admin.user.UserService;
import com.djadoel.admin.user.export.UserCsvExporter;
import com.djadoel.admin.user.export.UserExcelExporter;
import com.djadoel.admin.user.export.UserPdfExporter;
import com.djadoel.common.entity.Role;
import com.djadoel.common.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletResponse;

/* 
 * Created by: Ridho Suhaebi Arrowi
 * IDE: Spring Tool Suite 4
 * Information: ridhosuhaebi01@gmail.com
*/

@Controller
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/users")
	public String index(Model model) {

		return listByPage(1, model, "id", "asc", null);
	}

	@GetMapping("users/page/{pageNum}")
	public String listByPage(@PathVariable(name = "pageNum") int pageNum, Model model,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir, @Param("keyword") String keyword) {

		Page<User> page = service.listByPage(pageNum, sortField, sortDir, keyword);

		List<User> listUsers = page.getContent();

		long startCount = (pageNum - 1) * UserService.USERS_PER_PAGE + 1;

		long endCount = startCount + UserService.USERS_PER_PAGE - 1;

		if (endCount > page.getTotalElements()) {

			endCount = page.getTotalElements();
		}

		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";

		model.addAttribute("currentPage", pageNum);

		model.addAttribute("totalPages", page.getTotalPages());

		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("startCount", startCount);

		model.addAttribute("endCount", endCount);

		model.addAttribute("sortField", sortField);

		model.addAttribute("sortDir", sortDir);

		model.addAttribute("reverseSortDir", reverseSortDir);

		model.addAttribute("keyword", keyword);

		model.addAttribute("listUsers", listUsers);

		return "users/index";
	}

	@GetMapping("/users/create")
	public String create(Model model) {
		List<Role> listRoles = service.listRoles();
		User user = new User();
		user.setEnabled(true);
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);

		return "users/create";
	}

	@GetMapping("/users/edit/{id}")
	public String edit(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes redirectAttributes) {
		try {
			User user = service.get(id);
			List<Role> listRoles = service.listRoles();
			model.addAttribute("user", user);
			model.addAttribute("listRoles", listRoles);

			return "users/edit";
		} catch (UserNotFoundException exception) {
			String message = exception.getMessage();
			redirectAttributes.addFlashAttribute("infoMessage", message);

			return "redirect:/users";
		}
	}

	private String getRedirectURLtoAffectedUser(User user) {
		String firstPartOfEmail = user.getEmail().split("@")[0];

		return "redirect:/users/page/1?sortField=id&sortDir=asc&keyword=" + firstPartOfEmail;
	}

	@PostMapping("/users/save")
	public String insert(User user, RedirectAttributes redirectAttributes,
			@RequestParam("image") MultipartFile multipartFile) {
		String messageKey = "addMessage";

		try {
			if (!multipartFile.isEmpty()) {
				String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
				user.setPhotos(fileName);
				User savedUser = service.insert(user);
				String uploadDirectory = "user-photos/" + savedUser.getId();
				FileUploadUtil.cleanDirectory(uploadDirectory);
				FileUploadUtil.saveFile(uploadDirectory, fileName, multipartFile);
			} else {
				user.setPhotos(null); // Set image null if file not uploaded!
				User savedUser = service.insert(user);
			}

			redirectAttributes.addFlashAttribute(messageKey, "success");
		} catch (IOException exception) {
			if (!multipartFile.isEmpty()) {
				redirectAttributes.addFlashAttribute(messageKey, "error");
			}
		}

		return getRedirectURLtoAffectedUser(user);
	}

	@PostMapping("/users/update")
	public String update(User user, RedirectAttributes redirectAttributes,
			@RequestParam("image") MultipartFile multipartFile) {

		String messageKey = "updateMessage";

		try {
			if (!multipartFile.isEmpty()) {
				String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

				user.setPhotos(fileName);

				User savedUser = service.update(user);

				String uploadDirectory = "user-photos/" + savedUser.getId();

				FileUploadUtil.cleanDirectory(uploadDirectory);

				FileUploadUtil.saveFile(uploadDirectory, fileName, multipartFile);
			} else {

				if (user.getPhotos().isEmpty())
					user.setPhotos(null);

				service.update(user);
			}

			redirectAttributes.addFlashAttribute(messageKey, "success");

		} catch (IOException exception) {

			redirectAttributes.addFlashAttribute(messageKey, "error");

		}

		return getRedirectURLtoAffectedUser(user);
	}

	@GetMapping("/users/{id}/enabled/{status}")
	public String updateUserEnabledStatus(@PathVariable("id") Integer id, @PathVariable("status") boolean enabled,
			RedirectAttributes redirectAttributes) {
		try {
			service.updateUserEnabledStatus(id, enabled);
			String status = enabled ? "enabled" : "disabled";
			String message = "The user ID " + id + " has been " + status;
			redirectAttributes.addFlashAttribute("statusMessage", message);
		} catch (Exception exception) {
			String message = exception.getMessage();
			redirectAttributes.addFlashAttribute("infoMessage", message);
		}

		return "redirect:/users";
	}

	@GetMapping("/users/delete/{id}")
	public String delete(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes redirectAttributes) {
		try {
			// get user
			User user = service.get(id);
			// delete user form database
			service.delete(id);
			// delete folder and image
			String uploadDirectory = "user-photos/" + user.getId();
			FileUploadUtil.deleteDirectory(uploadDirectory);
			redirectAttributes.addFlashAttribute("deleteMessage", "success");
		} catch (UserNotFoundException exception) {
			String message = exception.getMessage();
			redirectAttributes.addFlashAttribute("infoMessage", message);
		}

		return "redirect:/users";
	}

	@GetMapping("/users/export/csv")
	public void exportToCsv(HttpServletResponse response) throws IOException {
		List<User> listUsers = service.listAll();
		UserCsvExporter exporter = new UserCsvExporter();
		exporter.export(listUsers, response);
	}

	@GetMapping("/users/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		List<User> listUsers = service.listAll();
		UserExcelExporter exporter = new UserExcelExporter();
		exporter.export(listUsers, response);
	}

	@GetMapping("/users/export/pdf")
	public void exportToPdf(HttpServletResponse response) throws IOException {
		List<User> listUsers = service.listAll();
		UserPdfExporter exporter = new UserPdfExporter();
		exporter.export(listUsers, response);
	}
}
