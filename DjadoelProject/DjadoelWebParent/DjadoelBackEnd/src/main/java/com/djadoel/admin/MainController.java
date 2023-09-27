package com.djadoel.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/* 
 * Created by: Ridho Suhaebi Arrowi
 * IDE: Spring Tool Suite 4
 * Information: ridhosuhaebi01@gmail.com
*/

@Controller
public class MainController {

	@GetMapping("/")
	public String viewHomePage() {
		return "dashboard";
	}

	@GetMapping("/login")
	public String viewLoginPage() {
		return "auth/login";
	}

}
