package com.djadoel.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/* 
 * Created by: Ridho Suhaebi Arrowi
 * IDE: Spring Tool Suite 4
 * Information: ridhosuhaebi01@gmail.com
*/

public class PasswordEncoderTest {

	@Test
	public void testEncodePassword() {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		String rawPassword = "password";

		String encodedPassword = passwordEncoder.encode(rawPassword);

		System.out.println(encodedPassword);

		boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);

		assertThat(matches).isTrue();
	}
}
