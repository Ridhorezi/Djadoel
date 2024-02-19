package com.djadoel.admin.brand.controller;

import java.io.IOException;
import java.util.List;

import com.djadoel.admin.FileUploadUtil;
import com.djadoel.admin.brand.BrandNotFoundException;
import com.djadoel.admin.brand.BrandService;
import com.djadoel.admin.brand.export.BrandCsvExporter;
import com.djadoel.admin.brand.export.BrandExcelExporter;
import com.djadoel.admin.brand.export.BrandPdfExporter;
import com.djadoel.admin.category.CategoryService;
import com.djadoel.common.entity.Brand;
import com.djadoel.common.entity.Category;

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
public class BrandController {

	@Autowired
	private BrandService brandService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/brands")
	public String index(Model model) {

		return listByPage(1, model, "id", "asc", null);
	}

	@GetMapping("brands/page/{pageNum}")
	public String listByPage(@PathVariable(name = "pageNum") int pageNum, Model model,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir, @Param("keyword") String keyword) {

		Page<Brand> page = brandService.listByPage(pageNum, sortField, sortDir, keyword);

		List<Brand> listBrands = page.getContent();

		long startCount = (pageNum - 1) * BrandService.BRANDS_PER_PAGE + 1;

		long endCount = startCount + BrandService.BRANDS_PER_PAGE - 1;

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

		model.addAttribute("listBrands", listBrands);

		return "brands/index";
	}

	@GetMapping("/brands/create")
	public String create(Model model) {
		List<Category> listCategories = categoryService.listCategoriesUsedInForm();
		model.addAttribute("brand", new Brand());
		model.addAttribute("listCategories", listCategories);

		return "brands/create";
	}

	@GetMapping("/brands/edit/{id}")
	public String edit(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes redirectAttributes) {
		try {
			Brand brand = brandService.get(id);
			List<Category> listCategories = categoryService.listCategoriesUsedInForm();
			model.addAttribute("brand", brand);
			model.addAttribute("listCategories", listCategories);

			return "brands/edit";
		} catch (BrandNotFoundException exception) {
			String message = exception.getMessage();
			redirectAttributes.addFlashAttribute("infoMessage", message);

			return "redirect:/brands";
		}
	}

	private String getRedirectURLtoAffectedBrand(Brand brand) {
		Integer firstPartOfID = brand.getId();

		return "redirect:/brands/page/1?sortField=id&sortDir=asc&keyword=" + firstPartOfID;
	}

	@PostMapping("/brands/save")
	public String insert(Brand brand, RedirectAttributes redirectAttributes,
			@RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
		String messageKey = "addMessage";

		try {
			if (!multipartFile.isEmpty()) {
				String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
				brand.setLogo(fileName);
				Brand savedBrand = brandService.insert(brand);
				String uploadDirectory = "../brand-logos/" + savedBrand.getId();
				FileUploadUtil.cleanDirectory(uploadDirectory);
				FileUploadUtil.saveFile(uploadDirectory, fileName, multipartFile);
			}
			redirectAttributes.addFlashAttribute(messageKey, "success");
		} catch (IOException exception) {
			if (!multipartFile.isEmpty()) {
				redirectAttributes.addFlashAttribute(messageKey, "error");
			}
		}

		return getRedirectURLtoAffectedBrand(brand);
	}

	@PostMapping("/brands/update")
	public String update(Brand brand, RedirectAttributes redirectAttributes,
			@RequestParam("fileImage") MultipartFile multipartFile) {

		String messageKey = "updateMessage";

		try {
			if (!multipartFile.isEmpty()) {
				String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

				brand.setLogo(fileName);

				Brand savedBrand = brandService.update(brand);

				String uploadDirectory = "../brand-logos/" + savedBrand.getId();

				FileUploadUtil.cleanDirectory(uploadDirectory);

				FileUploadUtil.saveFile(uploadDirectory, fileName, multipartFile);
			} else {
				brandService.update(brand);
			}

			redirectAttributes.addFlashAttribute(messageKey, "success");

		} catch (IOException exception) {
			redirectAttributes.addFlashAttribute(messageKey, "error");
		}

		return getRedirectURLtoAffectedBrand(brand);
	}

	@GetMapping("/brands/delete/{id}")
	public String delete(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes redirectAttributes) {
		try {
			// get brand
			Brand brand = brandService.get(id);
			// delete brand from database
			brandService.delete(id);
			// delete folder and image
			String brandDirectory = "../brand-logos/" + brand.getId();
			FileUploadUtil.deleteDirectory(brandDirectory);
			redirectAttributes.addFlashAttribute("deleteMessage", "success");
		} catch (BrandNotFoundException exception) {
			String message = exception.getMessage();
			redirectAttributes.addFlashAttribute("infoMessage", message);
		}

		return "redirect:/brands";
	}

	@GetMapping("/brands/export/csv")
	public void exportToCsv(HttpServletResponse response) throws IOException {
		List<Brand> listBrands = brandService.listAll();
		BrandCsvExporter exporter = new BrandCsvExporter();
		exporter.export(listBrands, response);
	}

	@GetMapping("/brands/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		List<Brand> listBrands = brandService.listAll();
		BrandExcelExporter exporter = new BrandExcelExporter();
		exporter.export(listBrands, response);
	}

	@GetMapping("/brands/export/pdf")
	public void exportToPdf(HttpServletResponse response) throws IOException {
		List<Brand> listBrands = brandService.listAll();
		BrandPdfExporter exporter = new BrandPdfExporter();
		exporter.export(listBrands, response);
	}
}
