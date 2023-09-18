package com.djadoel.admin.user;

import com.djadoel.common.entity.User;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

// Created by: Ridho Suhaebi Arrowi
// IDE: Spring Tool Suite 4
// Information: ridhosuhaebi01@gmail.com
// Fungsi: Kelas utilitas untuk mengelola entitas User dalam basis data.
// Kode:
//  - getUserByEmail(String email): Metode untuk mengambil pengguna berdasarkan alamat email.
//  - countById(Integer id): Metode untuk menghitung jumlah pengguna berdasarkan ID.
//  - updateEnabledStatus(Integer id, boolean enabled): Metode untuk memperbarui status pengguna (Aktif/Tidak Aktif) berdasarkan ID.

public interface UserRepository extends CrudRepository<User, Integer> {

	@Query("SELECT u FROM User u WHERE u.email = :email")
	public User getUserByEmail(@Param("email") String email);

	public Long countById(Integer id);

	@Query("UPDATE User u SET u.enabled = ?2 WHERE u.id = ?1")
	@Modifying
	public void updateEnabledStatus(Integer id, boolean enabled);
}
