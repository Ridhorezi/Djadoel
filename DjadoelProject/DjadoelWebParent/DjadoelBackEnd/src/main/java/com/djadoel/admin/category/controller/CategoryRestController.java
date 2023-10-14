package com.djadoel.admin.category.controller;

import com.djadoel.admin.category.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryRestController {

	@Autowired
	private CategoryService service;

	@PostMapping("/categories/check_unique")
	public String checkDuplicateCategory(@Param("id") Integer id, @Param("name") String name, @Param("alias") String alias) {
		return service.checkUniqueCategories(id, name, alias);
	}
}
