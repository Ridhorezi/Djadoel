package com.djadoel.admin.user;

import java.util.List;

import com.djadoel.common.entity.Role;
import com.djadoel.common.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/users")
	public String index(Model model) {
		List<User> listUsers = service.listAll();
		model.addAttribute("listUsers", listUsers);

		return "users/index";
	}

	@GetMapping("/users/create")
	public String insert(Model model) {
		List<Role> listRoles = service.listRoles();
		User user = new User();
		user.setEnabled(true);
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);

		return "users/create";
	}

	@PostMapping("/users/save")
	public String save(User user, RedirectAttributes redirectAttributes) {
		try {
			service.save(user);
			// Jika penyimpanan berhasil, "success"
			redirectAttributes.addFlashAttribute("swalMessage", "success");
		} catch (Exception e) {
			// Jika ada kesalahan, "error"
			redirectAttributes.addFlashAttribute("swalMessage", "error");
		}
		return "redirect:/users";
	}
}
