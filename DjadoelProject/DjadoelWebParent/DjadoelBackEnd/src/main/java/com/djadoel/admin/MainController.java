package com.djadoel.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Created by: Ridho Suhaebi Arrowi
// IDE: Spring Tool Suite 4
// Information: ridhosuhaebi01@gmail.com
// Fungsi: Kelas controller utama yang mengelola permintaan HTTP.
// Kode:
//  - @Controller: Anotasi yang menandakan bahwa kelas ini adalah komponen Spring MVC.
//  - @GetMapping: Anotasi yang menunjukkan bahwa metode ini akan menangani permintaan HTTP GET

@Controller
public class MainController {

	@GetMapping("/")
	public String viewHomePage() {
		return "dashboard";
	}
	
	@GetMapping("/login")
	public String viewLoginPage() {
		return "login";
	}

}
