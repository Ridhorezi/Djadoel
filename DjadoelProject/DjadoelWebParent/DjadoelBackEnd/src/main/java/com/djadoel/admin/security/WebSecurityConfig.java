package com.djadoel.admin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/* 
 * Created by: Ridho Suhaebi Arrowi
 * IDE: Spring Tool Suite 4
 * Information: ridhosuhaebi01@gmail.com
*/

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		return new DjadoelUserDetailsService();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests(authorize -> authorize.requestMatchers("/users/**").hasAnyAuthority("Admin")
				.requestMatchers("/categories/**").hasAnyAuthority("Admin", "Editor").requestMatchers("/brands/**")
				.hasAnyAuthority("Admin", "Editor").requestMatchers("/products/**")
				.hasAnyAuthority("Admin", "Editor", "Sales", "Shipper").requestMatchers("/customers/**")
				.hasAnyAuthority("Admin", "Sales").requestMatchers("/shipping/**").hasAnyAuthority("Admin", "Sales")
				.requestMatchers("/orders/**").hasAnyAuthority("Admin", "Sales", "Shipper")
				.requestMatchers("/report/**").hasAnyAuthority("Admin", "Sales").requestMatchers("/articles/**")
				.hasAnyAuthority("Admin", "Editor").requestMatchers("/menus/**").hasAnyAuthority("Admin", "Editor")
				.requestMatchers("/settings/**").hasAnyAuthority("Admin").requestMatchers("/reviews/**")
				.hasAnyAuthority("Admin", "Assistant").requestMatchers("/questions/**")
				.hasAnyAuthority("Admin", "Assistant").anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/login").usernameParameter("email").permitAll())
				.logout(logout -> logout.permitAll()).rememberMe(remember -> {
					remember.key("AbCdEfGhIjKlMnOpQrs_1234567890");
					remember.tokenValiditySeconds(7 * 24 * 60 * 60);
				});

		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() throws Exception {
		return (web) -> web.ignoring().requestMatchers("/libs/**", "/css/**", "/js/**", "/images/**");
	}

}