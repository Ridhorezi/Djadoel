package com.djadoel.admin.category;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.djadoel.common.entity.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	public static final int ROOT_CATEGORIES_PER_PAGE = 1;

	@Autowired
	private CategoryRepository categoryRepo;

	public List<Category> listByPage(CategoryPageInfo pageInfo, int pageNum, String sortField, String sortDir,
			String keyword) {

		Sort sort = Sort.by(sortField);
		
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();

		Pageable pageable = PageRequest.of(pageNum - 1, ROOT_CATEGORIES_PER_PAGE, sort);

		Page<Category> pageCategories = null;

		if (keyword != null && !keyword.isEmpty()) {
			pageCategories = categoryRepo.search(keyword, pageable);
		} else {
			pageCategories = categoryRepo.findRootCategories(pageable);
		}

		List<Category> rootCategories = pageCategories.getContent();

		pageInfo.setTotalElements(pageCategories.getTotalElements());

		pageInfo.setTotalPages(pageCategories.getTotalPages());

		if (keyword != null && !keyword.isEmpty()) {
			
			List<Category> searchResult = pageCategories.getContent();

			for (Category category : searchResult) {
				category.setHasChildren(category.getChildren().size() > 0);
			}
			
			return searchResult;

		} else {
			
			return listHirarchicalCategories(rootCategories, sortDir);
			
		}
	}

	// For List View

	private List<Category> listHirarchicalCategories(List<Category> rootCategories, String sortDir) {

		List<Category> hierarchicalCategories = new ArrayList<>();

		for (Category rootCategory : rootCategories) {

			hierarchicalCategories.add(Category.copyFull(rootCategory));

			Set<Category> children = sortSubCategories(rootCategory.getChildren(), sortDir);

			for (Category subCategory : children) {

				String name = "➔➔" + subCategory.getName();

				hierarchicalCategories.add(Category.copyFull(subCategory, name));

				listSubHierarchicalCategories(hierarchicalCategories, subCategory, 1, sortDir);

			}
		}

		return hierarchicalCategories;
	}

	private void listSubHierarchicalCategories(List<Category> hierarchicalCategories, Category parent, int subLevel,
			String sortDir) {

		int newSubLevel = subLevel + 1;

		Set<Category> children = sortSubCategories(parent.getChildren(), sortDir);

		for (Category subCategory : children) {

			String name = "";

			for (int i = 0; i < newSubLevel; i++) {
				name += "➔➔";
			}

			name += subCategory.getName();

			hierarchicalCategories.add(Category.copyFull(subCategory, name));

			listSubHierarchicalCategories(hierarchicalCategories, subCategory, newSubLevel, sortDir);

		}
	}

	// For Form View

	public List<Category> listCategoriesUsedInForm() {

		List<Category> categoriesUsedInForm = new ArrayList<>();

		Iterable<Category> categoriesInDB = categoryRepo.findRootCategories(Sort.by("id").ascending());

		for (Category category : categoriesInDB) {

			if (category.getParent() == null) {

				categoriesUsedInForm.add(Category.copyIdAndName(category));

				Set<Category> children = sortSubCategories(category.getChildren());

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

		Set<Category> children = sortSubCategories(parent.getChildren());

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

	private SortedSet<Category> sortSubCategories(Set<Category> children) {
		return sortSubCategories(children, "asc");
	}

	private SortedSet<Category> sortSubCategories(Set<Category> children, String sortDir) {

		SortedSet<Category> sortedChildren = new TreeSet<>(new Comparator<Category>() {
			@Override
			public int compare(Category cat1, Category cat2) {

				if (sortDir.equals("asc")) {
					return cat1.getId().compareTo(cat2.getId());
				} else {
					return cat2.getId().compareTo(cat1.getId());
				}

			}
		});

		sortedChildren.addAll(children);

		return sortedChildren;
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
