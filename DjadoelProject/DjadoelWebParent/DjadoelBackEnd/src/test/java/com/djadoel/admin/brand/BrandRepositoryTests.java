package com.djadoel.admin.brand;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import com.djadoel.common.entity.Brand;
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

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class BrandRepositoryTests {

	@Autowired
	private BrandRepository repo;

	@Test
	public void testCreateBrand() {
		Category statue = new Category(11);

		Brand michelangelo = new Brand("Michelangelo");

		michelangelo.getCategories().add(statue);

		Brand savedBrand = repo.save(michelangelo);

		assertThat(savedBrand).isNotNull();

		assertThat(savedBrand.getId()).isGreaterThan(0);
	}

	@Test
	public void testCreateBrand2() {
		Category artwork = new Category(1);

		Category statue = new Category(11);

		Brand giacometti = new Brand("Giacometti");

		giacometti.getCategories().add(artwork);

		giacometti.getCategories().add(statue);

		Brand savedBrand = repo.save(giacometti);

		assertThat(savedBrand).isNotNull();

		assertThat(savedBrand.getId()).isGreaterThan(0);
	}

	@Test
	public void testFindAll() {
		Iterable<Brand> brands = repo.findAll();

		brands.forEach(System.out::println);

		assertThat(brands).isNotEmpty();
	}

	@Test
	public void testGetById() {
		Brand brand = repo.findById(1).get();

		assertThat(brand.getName()).isEqualTo("Michelangelo");
	}

	@Test
	public void testUpdateName() {
		String newName = "Michelangelo";

		Brand michelangelo = repo.findById(1).get();

		michelangelo.setName(newName);

		Brand savedBrand = repo.save(michelangelo);

		assertThat(savedBrand.getName()).isEqualTo(newName);

	}

	@Test
	public void testDelete() {
		Integer id = 2;

		repo.deleteById(id);

		Optional<Brand> result = repo.findById(id);

		assertThat(result.isEmpty());

	}

}
