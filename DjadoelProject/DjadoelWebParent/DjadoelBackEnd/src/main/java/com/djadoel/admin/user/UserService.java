package com.djadoel.admin.user;

import java.util.List;
import java.util.NoSuchElementException;

import com.djadoel.common.entity.Role;
import com.djadoel.common.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

/* 
 * Created by: Ridho Suhaebi Arrowi
 * IDE: Spring Tool Suite 4
 * Information: ridhosuhaebi01@gmail.com
*/

@Service
@Transactional
public class UserService {

	public static final int USERS_PER_PAGE = 10;

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

	public Page<User> listByPage(int pageNum, String sortField, String sortDir, String keyword) {

		Sort sort = Sort.by(sortField);

		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();

		Pageable pageable = PageRequest.of(pageNum - 1, USERS_PER_PAGE, sort);

		if (keyword != null) {
			return userRepo.findAll(keyword, pageable);
		}

		return userRepo.findAll(pageable);
	}

	public List<Role> listRoles() {
		return (List<Role>) roleRepo.findAll();
	}

	public void encodePassword(User user) {
		String rawPassword = user.getPassword();
		String encodedPassword = passwordEncoder.encode(rawPassword);
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

	public User insert(User user) {
		encodePassword(user); // Enkripsi kata sandi

		return userRepo.save(user); // Simpan pengguna ke basis data
	}

	public User update(User user) {
		// Mendapatkan pengguna yang ada di basis data berdasarkan ID
		User existingUser = userRepo.findById(user.getId()).orElse(null);

		if (existingUser == null) {
			// Handle jika pengguna tidak ditemukan
			return null;
		}

		// Memeriksa apakah ada perubahan pada password
		if (!user.getPassword().isEmpty()) {
			// Jika ada password baru, menggantinya dan mengenkripsi
			existingUser.setPassword(user.getPassword());
			encodePassword(existingUser);
		}

		// Memeriksa apakah ada perubahan pada foto profil
		if (user.getPhotos() != null) {
			existingUser.setPhotos(user.getPhotos());
		}

		// Mengupdate informasi lainnya
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		existingUser.setRoles(user.getRoles());
		existingUser.setEnabled(user.isEnabled());

		// Menyimpan perubahan ke dalam basis data
		return userRepo.save(existingUser);
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
