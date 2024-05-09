package com.djadoel.admin.product.controller;

import java.util.List;

import com.djadoel.admin.brand.BrandService;
import com.djadoel.admin.product.ProductNotFoundException;
import com.djadoel.admin.product.ProductService;
import com.djadoel.common.entity.Brand;
import com.djadoel.common.entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/* 
 * Created by: Ridho Suhaebi Arrowi
 * IDE: Spring Tool Suite 4
 * Information: ridhosuhaebi01@gmail.com
*/

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private BrandService brandService;

	@GetMapping("/products")
	public String index(Model model) {

		List<Product> listProducts = productService.listAll();

		model.addAttribute("listProducts", listProducts);

		return "products/index";
	}

	@GetMapping("/products/create")
	public String create(Model model) {

		List<Brand> listBrands = brandService.listAll();

		Product product = new Product();

		product.setEnabled(true);

		product.setInStock(true);

		model.addAttribute("product", product);

		model.addAttribute("listBrands", listBrands);

		return "products/create";
	}

	@GetMapping("/products/edit/{id}")
	public String edit(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes redirectAttributes)
			throws ProductNotFoundException {
		try {

			Product product = productService.get(id);

			List<Brand> listBrands = brandService.listAll();

			model.addAttribute("product", product);

			model.addAttribute("listBrands", listBrands);

			return "products/edit";

		} catch (ProductNotFoundException exception) {

			String message = exception.getMessage();

			redirectAttributes.addFlashAttribute("infoMessage", message);

			return "redirect:/products";
		}
	}

	@PostMapping("/products/save")
	public String insert(Product product) {
		
		System.out.println("Product Name: " + product.getName());
		System.out.println("Brand ID: " + product.getBrand().getId());
		System.out.println("Category ID: " + product.getCategory().getId());

		return "redirect:/products";
	}

}
