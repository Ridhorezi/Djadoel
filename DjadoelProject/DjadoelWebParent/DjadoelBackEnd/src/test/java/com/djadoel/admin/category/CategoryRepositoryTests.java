package com.djadoel.admin.category;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;

import com.djadoel.common.entity.Category;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

/* 
 * Created by: Ridho Suhaebi Arrowi
 * IDE: Spring Tool Suite 4
 * Information: ridhosuhaebi01@gmail.com
*/

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CategoryRepositoryTests {

	@Autowired
	private CategoryRepository repo;

	@Test
	public void testCreateRootCategory() {

		Category category = new Category("ArtWork");

		Category savedCategory = repo.save(category);

		assertThat(savedCategory.getId()).isGreaterThan(0);
	}

	@Test
	public void testCreateSubCategory() {

		Category parent = new Category(17);

		Category category1 = new Category("Rock Tape Cassette", parent);

		Category category2 = new Category("Pop Tape Cassette", parent);

		Category category3 = new Category("Jazz Tape Cassette", parent);

		Category category4 = new Category("Classic Tape Cassette", parent);

		Category category5 = new Category("Blues Tape Cassette", parent);

		Category category6 = new Category("Electronic Tape Cassette", parent);

		repo.saveAll(List.of(category1, category2, category3, category4, category5, category6));
	}

	@Test
	public void testGetCategory() {

		Category category = repo.findById(1).get();

		System.out.println(category.getName());

		Set<Category> children = category.getChildren();

		for (Category subCategory : children) {

			System.out.println(subCategory.getName());
		}

		assertThat(children.size()).isGreaterThan(0);
	}

	@Test
	public void testPrintHierarchicalCategories() {

		Iterable<Category> categories = repo.findAll();

		for (Category category : categories) {

			if (category.getParent() == null) {

				System.out.println(category.getName());

				Set<Category> children = category.getChildren();

				for (Category subCategory : children) {

					System.out.println("--" + subCategory.getName());

					printChildren(subCategory, 1);
				}
			}
		}
	}

	private void printChildren(Category parent, int subLevel) {

		int newSubLevel = subLevel + 1;

		Set<Category> children = parent.getChildren();

		for (Category subCategory : children) {

			for (int i = 0; i < newSubLevel; i++) {
				System.out.println("--");
			}

			System.out.println(subCategory.getName());

			printChildren(subCategory, newSubLevel);
		}
	}

	@Test
	public void testDeleteCategory() {

		Integer categoryId = 96;

		repo.deleteById(categoryId);
	}

	@Test
	public void testListRootCategories() {

		List<Category> rootCategories = repo.findRootCategories();

		rootCategories.forEach(category -> System.out.println(category.getName()));
	}

	@Test
	public void testFindByName() {

		String name = "ArtWork";

		Category category = repo.findByName(name);

		assertThat(category).isNotNull();

		assertThat(category.getName()).isEqualTo(name);
	}

	@Test
	public void testFindByAlias() {
		
		String alias = "ArtWork";

		Category category = repo.findByAlias(alias);

		assertThat(category).isNotNull();

		assertThat(category.getAlias()).isEqualTo(alias);
	}
}
