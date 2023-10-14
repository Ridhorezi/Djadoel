package com.djadoel.admin.category;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import com.djadoel.common.entity.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

/* 
 * Created by: Ridho Suhaebi Arrowi
 * IDE: Spring Tool Suite 4
 * Information: ridhosuhaebi01@gmail.com
*/

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;

	public List<Category> listAll() {
		List<Category> rootCategories = categoryRepo.findRootCategories();
		return listHirarchicalCategories(rootCategories);
	}

	// For List View

	private List<Category> listHirarchicalCategories(List<Category> rootCategories) {

		List<Category> hierarchicalCategories = new ArrayList<>();

		for (Category rootCategory : rootCategories) {

			hierarchicalCategories.add(Category.copyFull(rootCategory));

			Set<Category> children = rootCategory.getChildren();

			for (Category subCategory : children) {

				String name = "➔➔" + subCategory.getName();

				hierarchicalCategories.add(Category.copyFull(subCategory, name));

				listSubHierarchicalCategories(hierarchicalCategories, subCategory, 1);

			}
		}

		return hierarchicalCategories;
	}

	private void listSubHierarchicalCategories(List<Category> hierarchicalCategories, Category parent, int subLevel) {

		int newSubLevel = subLevel + 1;

		Set<Category> children = parent.getChildren();

		for (Category subCategory : children) {

			String name = "";

			for (int i = 0; i < newSubLevel; i++) {
				name += "➔➔";
			}

			name += subCategory.getName();

			hierarchicalCategories.add(Category.copyFull(subCategory, name));

			listSubHierarchicalCategories(hierarchicalCategories, subCategory, newSubLevel);

		}
	}

	// For Form View

	public List<Category> listCategoriesUsedInForm() {

		List<Category> categoriesUsedInForm = new ArrayList<>();

		Iterable<Category> categoriesInDB = categoryRepo.findAll();

		for (Category category : categoriesInDB) {

			if (category.getParent() == null) {

				categoriesUsedInForm.add(Category.copyIdAndName(category));

				Set<Category> children = category.getChildren();

				for (Category subCategory : children) {

					String name = "➔➔" + subCategory.getName();

					categoriesUsedInForm.add(Category.copyIdAndName(subCategory.getId(), name));

					listSubCategoriesUsedInForm(categoriesUsedInForm, subCategory, 1);
				}
			}
		}

		return categoriesUsedInForm;
	}

	private void listSubCategoriesUsedInForm(List<Category> categoriesUsedInForm, Category parent, int subLevel) {

		int newSubLevel = subLevel + 1;

		Set<Category> children = parent.getChildren();

		for (Category subCategory : children) {

			String name = "";

			for (int i = 0; i < newSubLevel; i++) {
				name += "➔➔";
			}

			name += subCategory.getName();

			categoriesUsedInForm.add(Category.copyIdAndName(subCategory.getId(), name));

			listSubCategoriesUsedInForm(categoriesUsedInForm, subCategory, newSubLevel);
		}
	}

	public Category insert(Category category) {
		return categoryRepo.save(category);
	}

	public Category update(Category category) {
		return categoryRepo.save(category);
	}

	public Category get(Integer id) throws CategoryNotFoundException {
		try {
			return categoryRepo.findById(id).get();
		} catch (NoSuchElementException exception) {
			throw new CategoryNotFoundException("Could not find any category with ID " + id);
		}
	}

	public String checkUniqueCategories(Integer id, String name, String alias) {

		boolean isCreatingNew = (id == null || id == 0);

		Category categoryByName = categoryRepo.findByName(name);

		if (isCreatingNew) {

			if (categoryByName != null) {
				return "DuplicateName";
			} else {

				Category categoryByAlias = categoryRepo.findByAlias(alias);

				if (categoryByAlias != null) {
					return "DuplicateAlias";
				}
			}

		} else {

			if (categoryByName != null && categoryByName.getId() != id) {
				return "DuplicateName";
			}

			Category categoryByAlias = categoryRepo.findByAlias(alias);

			if (categoryByAlias != null && categoryByAlias.getId() != id) {
				return "DuplicateAlias";
			}
		}

		return "OK";
	}

	public void updateCategoryEnabledStatus(Integer id, boolean enabled) {
		categoryRepo.updateEnabledStatus(id, enabled);
	} 

	public void delete(Integer id) throws CategoryNotFoundException {
		Long countById = categoryRepo.countById(id);

		if (countById == null || countById == 0) {
			throw new CategoryNotFoundException("Could not find any category with ID " + id);
		}

		categoryRepo.deleteById(id);
	}
}
