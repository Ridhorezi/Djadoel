package com.djadoel.admin.category;

import static org.assertj.core.api.Assertions.assertThat;

import com.djadoel.common.entity.Category;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CategoryServiceTests {

	@MockBean
	private CategoryRepository categoryRepo;

	@InjectMocks
	private CategoryService service;

	@Test
	public void testCheckUniqueInNewModeReturnDuplicateName() {

		Integer id = null;

		String name = "ArtWork";

		String alias = "abc";

		Category category = new Category(id, name, alias);

		Mockito.when(categoryRepo.findByName(name)).thenReturn(category);

		Mockito.when(categoryRepo.findByAlias(alias)).thenReturn(null);

		String result = service.checkUniqueCategories(id, name, alias);

		assertThat(result).isEqualTo("DuplicateName");

	}

	@Test
	public void testCheckUniqueInNewModeReturnDuplicateAlias() {

		Integer id = null;

		String name = "abc";

		String alias = "ArtWork";

		Category category = new Category(id, name, alias);

		Mockito.when(categoryRepo.findByAlias(alias)).thenReturn(category);

		Mockito.when(categoryRepo.findByName(name)).thenReturn(null);

		String result = service.checkUniqueCategories(id, name, alias);

		assertThat(result).isEqualTo("DuplicateAlias");

	}

	@Test
	public void testCheckUniqueInNewModeReturnOK() {

		Integer id = null;

		String name = "NoDuplicateName";

		String alias = "NoDuplicateAlias";

		Mockito.when(categoryRepo.findByAlias(alias)).thenReturn(null);

		Mockito.when(categoryRepo.findByName(name)).thenReturn(null);

		String result = service.checkUniqueCategories(id, name, alias);

		assertThat(result).isEqualTo("OK");

	}

	@Test
	public void testCheckUniqueInEditModeReturnDuplicateName() {

		Integer id = 1;

		String name = "ArtWork";

		String alias = "abc";

		Category category = new Category(2, name, alias);

		Mockito.when(categoryRepo.findByName(name)).thenReturn(category);

		Mockito.when(categoryRepo.findByAlias(alias)).thenReturn(null);

		String result = service.checkUniqueCategories(id, name, alias);

		assertThat(result).isEqualTo("DuplicateName");

	}

	@Test
	public void testCheckUniqueInEditModeReturnDuplicateAlias() {

		Integer id = 1;

		String name = "abc";

		String alias = "ArtWork";

		Category category = new Category(2, name, alias);

		Mockito.when(categoryRepo.findByAlias(alias)).thenReturn(category);

		Mockito.when(categoryRepo.findByName(name)).thenReturn(null);

		String result = service.checkUniqueCategories(id, name, alias);

		assertThat(result).isEqualTo("DuplicateAlias");

	}

	@Test
	public void testCheckUniqueInEditModeReturnOK() {

		Integer id = 1;

		String name = "NoDuplicateName";

		String alias = "NoDuplicateAlias";

		Category category = new Category(id, name, alias);

		Mockito.when(categoryRepo.findByAlias(alias)).thenReturn(category);

		Mockito.when(categoryRepo.findByName(name)).thenReturn(null);

		String result = service.checkUniqueCategories(id, name, alias);

		assertThat(result).isEqualTo("OK");

	}

}
