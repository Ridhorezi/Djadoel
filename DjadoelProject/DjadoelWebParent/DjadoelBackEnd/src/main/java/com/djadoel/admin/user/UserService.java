package com.djadoel.admin.user;

import java.util.List;
import java.util.NoSuchElementException;

import com.djadoel.common.entity.Role;
import com.djadoel.common.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

// Created by: Ridho Suhaebi Arrowi
// IDE: Spring Tool Suite 4
// Information: ridhosuhaebi01@gmail.com
// Fungsi: Kelas Service untuk mengelola entitas User.
// Kode:
//  - listAll(): Metode untuk mendapatkan daftar semua pengguna.
//  - listRoles(): Metode untuk mendapatkan daftar semua peran.
//  - encodePassword(User user): Metode untuk mengenkripsi kata sandi pengguna.
//  - isEmailUnique(Integer id, String email): Metode untuk memeriksa apakah alamat email unik.
//  - save(User user): Metode untuk menyimpan atau memperbarui pengguna.
//  - get(Integer id): Metode untuk mendapatkan pengguna berdasarkan ID.
//  - delete(Integer id): Metode untuk menghapus pengguna berdasarkan ID.
//  - updateUserEnabledStatus(Integer id, boolean enabled): Metode untuk memperbarui status pengguna (Aktif/Tidak Aktif) berdasarkan ID.

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User getByEmail(String email) {
		return userRepo.getUserByEmail(email);
	}

	public List<User> listAll() {
		return (List<User>) userRepo.findAll();
	}

	public List<Role> listRoles() {
		return (List<Role>) roleRepo.findAll();
	}

	public void encodePassword(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
	}

	public boolean isEmailUnique(Integer id, String email) {

		User userByEmail = userRepo.getUserByEmail(email);

		if (userByEmail == null)
			return true;

		boolean isCreatingNew = (id == null);

		if (isCreatingNew) {
			if (userByEmail != null)
				return false;
		} else {
			if (userByEmail.getId() != id)
				return false;
		}

		return true;
	}

	public User save(User user) {

		boolean isUpdatingUser = (user.getId() != null);

		encodePassword(user);

		if (isUpdatingUser) {

			User existingUser = userRepo.findById(user.getId()).get();

			if (user.getPassword().isEmpty())
				user.setPassword(existingUser.getPassword());
		}

		return userRepo.save(user);
	}

	public User get(Integer id) throws UserNotFoundException {
		try {
			return userRepo.findById(id).get();
		} catch (NoSuchElementException exception) {
			throw new UserNotFoundException("Could not find any user with ID " + id);
		}
	}

	public void delete(Integer id) throws UserNotFoundException {
		Long countById = userRepo.countById(id);

		if (countById == null || countById == 0) {
			throw new UserNotFoundException("Could not find any user with ID " + id);
		}

		userRepo.deleteById(id);
	}

	public void updateUserEnabledStatus(Integer id, boolean enabled) {
		userRepo.updateEnabledStatus(id, enabled);
	}

	public User updateAccount(User userInForm) {
		User userInDB = userRepo.findById(userInForm.getId()).get();

		if (!userInForm.getPassword().isEmpty()) {
			userInDB.setPassword(userInForm.getPassword());
			encodePassword(userInDB);
		}

		if (userInForm.getPhotos() != null) {
			userInDB.setPhotos(userInForm.getPhotos());
		}

		userInDB.setFirstName(userInForm.getFirstName());

		userInDB.setLastName(userInForm.getLastName());

		return userRepo.save(userInDB);
	}

}
