package com.djadoel.admin.user;

import com.djadoel.common.entity.Role;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Created by: Ridho Suhaebi Arrowi
// IDE: Spring Tool Suite 4
// Information: ridhosuhaebi01@gmail.com
// Fungsi: Interface untuk mengakses data role dalam database menggunakan Spring Data JPA.
// Kode:
//  - @Repository: Anotasi Spring yang menandakan bahwa ini adalah komponen repositori.
//  - extends CrudRepository<Role, Integer>: Mengindikasikan bahwa ini adalah repositori yang mengelola entitas Role dengan kunci utama bertipe Integer.

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

}
