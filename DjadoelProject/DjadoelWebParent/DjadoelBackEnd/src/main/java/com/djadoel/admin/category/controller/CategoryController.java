package com.djadoel.admin.category.controller;

import java.io.IOException;
import java.util.List;

import com.djadoel.admin.FileUploadUtil;
import com.djadoel.admin.category.CategoryNotFoundException;
import com.djadoel.admin.category.CategoryService;
import com.djadoel.admin.category.export.CategoryCsvExporter;
import com.djadoel.admin.category.export.CategoryExcelExporter;
import com.djadoel.admin.category.export.CategoryPdfExporter;
import com.djadoel.common.entity.Category;

import org.springframework.beans.factory.annotation.Autowired;
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
public class CategoryController {

	@Autowired
	private CategoryService service;

	@GetMapping("/categories")
	public String index(Model model) {
		List<Category> listCategories = service.listAll();
		model.addAttribute("listCategories", listCategories);

		return "categories/index";
	}

	@GetMapping("/categories/create")
	public String create(Model model) {
		List<Category> listCategories = service.listCategoriesUsedInForm();
		model.addAttribute("category", new Category());
		model.addAttribute("listCategories", listCategories);

		return "categories/create";
	}

	@GetMapping("/categories/edit/{id}")
	public String edit(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes redirectAttributes) {
		try {
			Category category = service.get(id);
			List<Category> listCategories = service.listCategoriesUsedInForm();
			model.addAttribute("category", category);
			model.addAttribute("listCategories", listCategories);

			return "categories/edit";
		} catch (CategoryNotFoundException exception) {
			String message = exception.getMessage();
			redirectAttributes.addFlashAttribute("infoMessage", message);

			return "redirect:/categories";
		}
	}

	@PostMapping("/categories/save")
	public String insert(Category category, RedirectAttributes redirectAttributes,
			@RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
		String messageKey = "addMessage";

		try {
			if (!multipartFile.isEmpty()) {
				String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
				category.setImage(fileName);
				Category savedCategory = service.insert(category);
				String uploadDirectory = "../category-images/" + savedCategory.getId();
				FileUploadUtil.cleanDirectory(uploadDirectory);
				FileUploadUtil.saveFile(uploadDirectory, fileName, multipartFile);
			}

			redirectAttributes.addFlashAttribute(messageKey, "success");
		} catch (IOException exception) {

			if (!multipartFile.isEmpty()) {
				redirectAttributes.addFlashAttribute(messageKey, "error");
			}
		}

		return "redirect:/categories";
	}

	@PostMapping("/categories/update")
	public String update(Category category, RedirectAttributes redirectAttributes,
			@RequestParam("fileImage") MultipartFile multipartFile) {

		String messageKey = "updateMessage";

		try {
			if (!multipartFile.isEmpty()) {
				String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

				category.setImage(fileName);

				Category savedCategory = service.update(category);

				String uploadDirectory = "../category-images/" + savedCategory.getId();

				FileUploadUtil.cleanDirectory(uploadDirectory);

				FileUploadUtil.saveFile(uploadDirectory, fileName, multipartFile);
			} else {
				service.update(category);
			}

			redirectAttributes.addFlashAttribute(messageKey, "success");

		} catch (IOException exception) {
			redirectAttributes.addFlashAttribute(messageKey, "error");
		}

		return "redirect:/categories";
	}

	@GetMapping("/categories/{id}/enabled/{status}")
	public String updateCategoryEnabledStatus(@PathVariable("id") Integer id, @PathVariable("status") boolean enabled,
			RedirectAttributes redirectAttributes) {

		try {
			service.updateCategoryEnabledStatus(id, enabled);
			String status = enabled ? "enabled" : "disabled";
			String message = "The category ID " + id + " has been " + status;
			redirectAttributes.addFlashAttribute("statusMessage", message);
		} catch (Exception exception) {
			String message = exception.getMessage();
			redirectAttributes.addFlashAttribute("infoMessage", message);
		}

		return "redirect:/categories";
	}

	@GetMapping("/categories/delete/{id}")
	public String delete(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes redirectAttributes) {
		try {
			// get category
			Category category = service.get(id);
			// delete category form database
			service.delete(id);
			// delete folder and image
			String categoryDirectory = "../category-images/" + category.getId();
			FileUploadUtil.deleteDirectory(categoryDirectory);
			redirectAttributes.addFlashAttribute("deleteMessage", "success");
		} catch (CategoryNotFoundException exception) {
			String message = exception.getMessage();
			redirectAttributes.addFlashAttribute("infoMessage", message);
		}

		return "redirect:/categories";
	}

	@GetMapping("/categories/export/csv")
	public void exportToCsv(HttpServletResponse response) throws IOException {
		List<Category> listCategories = service.listCategoriesUsedInForm();
		CategoryCsvExporter exporter = new CategoryCsvExporter();
		exporter.export(listCategories, response);
	}
	
	@GetMapping("/categories/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		List<Category> listCategories = service.listCategoriesUsedInForm();
		CategoryExcelExporter exporter = new CategoryExcelExporter();
		exporter.export(listCategories, response);
	}
	
	@GetMapping("/categories/export/pdf")
	public void exportToPdf(HttpServletResponse response) throws IOException {
		List<Category> listCategories = service.listCategoriesUsedInForm();
		CategoryPdfExporter exporter = new CategoryPdfExporter();
		exporter.export(listCategories, response);
	}
	
	
}
