package com.djadoel.admin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Created by: Ridho Suhaebi Arrowi
// IDE: Spring Tool Suite 4
// Information: ridhosuhaebi01@gmail.com
// Fungsi: Kelas konfigurasi untuk mengatur keamanan web dengan Spring Security.
// Kode:
//  - @EnableWebSecurity: Anotasi yang mengaktifkan konfigurasi keamanan web.
//  - PasswordEncoder(): Metode yang mengembalikan bean PasswordEncoder untuk mengenkripsi kata sandi.
//  - securityFilterChain(HttpSecurity http): Metode untuk mengatur konfigurasi akses ke URL.

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public PasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().anyRequest().permitAll();

		return http.build();
	}
}
