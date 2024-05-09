package com.djadoel.admin.product;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Optional;

import com.djadoel.common.entity.Brand;
import com.djadoel.common.entity.Category;
import com.djadoel.common.entity.Product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

/* 
 * Created by: Ridho Suhaebi Arrowi
 * IDE: Spring Tool Suite 4
 * Information: ridhosuhaebi01@gmail.com
*/

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ProductRepositoryTests {

	@Autowired
	private ProductRepository repo;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testCreateProduct() {

		Brand brand = entityManager.find(Brand.class, 1);

		Category category = entityManager.find(Category.class, 1);

		Product product = new Product();

		product.setName("David");

		product.setAlias("david");

		product.setShortDescription(
				"David adalah sebuah patung marmer yang menggambarkan sosok pahlawan Alkitab, Daud, sebelum pertempuran dengan raksasa Goliath.");

		product.setFullDescription(
				"David  adalah sebuah patung marmer putih yang dibuat oleh Michelangelo antara tahun 1501 dan 1504. "
						+ "Patung ini menggambarkan tokoh Alkitab, Daud, yang legendaris dalam aksi mempertahankan diri melawan raksasa Goliath. "
						+ "Michelangelo memilih untuk menggambarkan Daud dalam pose yang tenang namun siap tempur, dengan pandangan tajam yang mengarah ke arah lawan yang akan datang.");

		product.setPrice(2000000);

		product.setCreatedDtm(new Date());

		product.setUpdatedDtm(new Date());

		product.setBrand(brand);

		product.setCategory(category);

		Product savedProduct = repo.save(product);

		assertThat(savedProduct).isNotNull();

		assertThat(savedProduct.getId()).isGreaterThan(0);
	}

	@Test
	public void testListAllProducts() {

		Iterable<Product> iterableProducts = repo.findAll();

		iterableProducts.forEach(System.out::println);
	}

	@Test
	public void testGetProductsById() {

		Integer id = 1;

		Product product = repo.findById(id).get();

		System.out.println(product);

		assertThat(product).isNotNull();
	}

	@Test
	public void testUpdateProduct() {

		Integer id = 1;

		Product product = repo.findById(id).get();

		product.setPrice(1500000);

		repo.save(product);

		Product updatedProduct = entityManager.find(Product.class, id);

		assertThat(updatedProduct.getPrice()).isEqualTo(1500000);
	}

	@Test
	public void testDeleteProduct() {

		Integer id = 2;

		repo.deleteById(id);

		Optional<Product> result = repo.findById(id);

		assertThat(!result.isPresent());
	}

}
