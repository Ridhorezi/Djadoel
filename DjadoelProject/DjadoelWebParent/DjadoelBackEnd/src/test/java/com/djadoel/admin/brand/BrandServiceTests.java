package com.djadoel.admin.brand;

import static org.assertj.core.api.Assertions.assertThat;

import com.djadoel.common.entity.Brand;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/* 
 * Created by: Ridho Suhaebi Arrowi
 * IDE: Spring Tool Suite 4
 * Information: ridhosuhaebi01@gmail.com
*/

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class BrandServiceTests {

	@MockBean
	private BrandRepository brandRepo;

	@InjectMocks
	private BrandService service;

	@Test
	public void testCheckUniqueInNewModeReturnDuplicate() {

		Integer id = null;

		String name = "Test Duplicate New";

		Brand brand = new Brand(name);

		Mockito.when(brandRepo.findByName(name)).thenReturn(brand);

		String result = service.checkUniqueBrands(id, name);

		assertThat(result).isEqualTo("Duplicate");

	}

	@Test
	public void testCheckUniqueInNewModeReturnOK() {

		Integer id = null;

		String name = "Test No Duplicate New";

		Mockito.when(brandRepo.findByName(name)).thenReturn(null);

		String result = service.checkUniqueBrands(id, name);

		assertThat(result).isEqualTo("OK");

	}

	@Test
	public void testCheckUniqueInEditModeReturnDuplicate() {

		Integer id = 6;

		String name = "Giacometti";

		Brand brand = new Brand(id, name);

		Mockito.when(brandRepo.findByName(name)).thenReturn(brand);

		String result = service.checkUniqueBrands(3, name);

		assertThat(result).isEqualTo("Duplicate");

	}

	@Test
	public void testCheckUniqueInEditModeReturnOK() {

		Integer id = 6;

		String name = "Test No Duplicate Edit";

		Brand brand = new Brand(id, name);

		Mockito.when(brandRepo.findByName(name)).thenReturn(brand);

		String result = service.checkUniqueBrands(id, name);

		assertThat(result).isEqualTo("OK");

	}

}
