package com.djadoel.admin.security;

import com.djadoel.admin.user.UserRepository;
import com.djadoel.common.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/* 
 * Created by: Ridho Suhaebi Arrowi
 * IDE: Spring Tool Suite 4
 * Information: ridhosuhaebi01@gmail.com
*/


public class DjadoelUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userRepository.getUserByEmail(email);

//		System.out.println(user.getEmail());
//		System.out.println(user.getPassword());

		if (user != null) {
			return new DjadoelUserDetails(user);
		}

		throw new UsernameNotFoundException("Could not find user with email: " + email);

	}

}
