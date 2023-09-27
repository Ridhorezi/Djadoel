package com.djadoel.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.djadoel.common.entity.Role;
import com.djadoel.common.entity.User;

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
public class UserRepositoryTests {

	@Autowired
	private UserRepository repo;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testCreateUserWithOneRole() {

		Role roleAdmin = entityManager.find(Role.class, 1);

		User userNew = new User("admgbvrj@gmail.com", "password", "Admin", "Djadoel");

		userNew.addRole(roleAdmin);

		User savedUser = repo.save(userNew);

		assertThat(savedUser.getId()).isGreaterThan(0);

	}

	@Test
	public void testCreateUserWithTwoRoles() {

		User userNew = new User("19211090@gmail.com", "password", "Ridho", "Suhaebi Arrowi");

		Role roleEditor = new Role(3);

		Role roleAssitant = new Role(5);

		userNew.addRole(roleEditor);

		userNew.addRole(roleAssitant);

		User savedUser = repo.save(userNew);

		assertThat(savedUser.getId()).isGreaterThan(0);

	}

	@Test
	public void testListAllUsers() {

		Iterable<User> listUsers = repo.findAll();

		listUsers.forEach(user -> System.out.println(user));
	}

	@Test
	public void testGetUserById() {

		User userAdmin = repo.findById(1).get();

		System.out.println(userAdmin);

		assertThat(userAdmin).isNotNull();
	}

	@Test
	public void testUpdateUserDetails() {

		User userAdmin = repo.findById(2).get();

		userAdmin.setEnabled(false);

		repo.save(userAdmin);
	}

	@Test
	public void testUpdateUserRoles() {

		User userRidho = repo.findById(2).get();

		Role roleEditor = new Role(3);

		Role roleSales = new Role(2);

		userRidho.getRoles().remove(roleEditor);

		userRidho.addRole(roleSales);

		repo.save(userRidho);
	}

	@Test
	public void testDeleteUser() {

		Integer userId = 6;

		repo.deleteById(userId);
	}

	@Test
	public void testGetUserByEmail() {

		String email = "19211090@bsi.ac.id";

		User user = repo.getUserByEmail(email);

		assertThat(user).isNotNull();
	}

	@Test
	public void testCountById() {

		Integer id = 1;

		Long countByid = repo.countById(id);

		assertThat(countByid).isNotNull().isGreaterThan(0);
	}

	@Test
	public void testDisabledUser() {

		Integer id = 1;

		repo.updateEnabledStatus(id, false);
	}

	@Test
	public void testEnabledUser() {

		Integer id = 2;

		repo.updateEnabledStatus(id, true);
	}
}
