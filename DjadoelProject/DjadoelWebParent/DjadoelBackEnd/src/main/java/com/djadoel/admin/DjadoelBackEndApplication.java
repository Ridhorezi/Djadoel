package com.djadoel.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

// Created by: Ridho Suhaebi Arrowi
// IDE: Spring Tool Suite 4
// Information: ridhosuhaebi01@gmail.com
// Fungsi: Kelas utama yang berfungsi sebagai titik masuk aplikasi Spring Boot.
// Kode:
// - @SpringBootApplication: Anotasi yang menandakan kelas ini adalah kelas konfigurasi Spring Boot.
// - main(): Metode utama yang akan dieksekusi saat aplikasi dimulai.

@SpringBootApplication
@EntityScan({ "com.djadoel.common.entity", "com.djadoel.admin.user" })
public class DjadoelBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(DjadoelBackEndApplication.class, args);
	}
}
