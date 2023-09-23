package com.djadoel.admin.user.controller;

import com.djadoel.admin.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

// Created by: Ridho Suhaebi Arrowi
// IDE: Spring Tool Suite 4
// Information: ridhosuhaebi01@gmail.com
// Fungsi: Kelas utilitas untuk mengelola entitas User dalam basis data.
// Kode:
//  - checkDuplicateEmail(@Param("id") Integer id, @Param("email") String email): Metode untuk memeriksa apakah alamat email yang diberikan sudah digunakan oleh pengguna lain atau tidak.

@RestController
public class UserRestController {

	@Autowired
	private UserService service;

	@PostMapping("/users/check_email")
	public String checkDuplicateEmail(@Param("id") Integer id, @Param("email") String email) {

		return service.isEmailUnique(id, email) ? "OK" : "Duplicated";
	}
}
