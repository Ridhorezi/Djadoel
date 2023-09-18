package com.djadoel.admin.user;

// Created by: Ridho Suhaebi Arrowi
// IDE: Spring Tool Suite 4
// Information: ridhosuhaebi01@gmail.com
// Fungsi: Kelas pengecualian (exception) yang digunakan ketika pengguna (user) tidak ditemukan.
// Kode:
//  - UserNotFoundException(String message): Konstruktor dengan parameter pesan pengecualian.

public class UserNotFoundException extends Exception {

	public UserNotFoundException(String message) {

		super(message);
	}

}
