package com.djadoel.admin.user.controller;

import java.io.IOException;

import com.djadoel.admin.FileUploadUtil;
import com.djadoel.admin.security.DjadoelUserDetails;
import com.djadoel.admin.user.UserService;
import com.djadoel.common.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AccountController {

	@Autowired
	private UserService service;

	@GetMapping("/account")
	public String viewDetails(@AuthenticationPrincipal DjadoelUserDetails loggedUser, Model model) {

		String email = loggedUser.getUsername();

		User user = service.getByEmail(email);

		model.addAttribute("user", user);

		return "users/account";
	}

	@PostMapping("/account/update")
	public String saveDetails(User user, RedirectAttributes redirectAttributes,
			@RequestParam("image") MultipartFile multipartFile,
			@AuthenticationPrincipal DjadoelUserDetails loggedUser) {

		try {
			if (!multipartFile.isEmpty()) {
				String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

				user.setPhotos(fileName);

				User savedUser = service.updateAccount(user);

				String uploadDirectory = "user-photos/" + savedUser.getId();

				FileUploadUtil.cleanDirectory(uploadDirectory);

				FileUploadUtil.saveFile(uploadDirectory, fileName, multipartFile);
			} else {

				if (user.getPhotos().isEmpty())
					user.setPhotos(null);

				service.updateAccount(user);
			}

			redirectAttributes.addFlashAttribute("updateMessage", "success");

		} catch (IOException e) {

			redirectAttributes.addFlashAttribute("updateMessage", "error");

		}

		loggedUser.setFirstName(user.getFirstName());

		loggedUser.setLastName(user.getLastName());

		return "redirect:/account";
	}

}
