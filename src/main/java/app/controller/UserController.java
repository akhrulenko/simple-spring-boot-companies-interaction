package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import app.model.User;
import app.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("{user}")
	public String getProfile(@PathVariable User user, Model model) {

		model.addAttribute("username", user.getName());
		model.addAttribute("email", user.getEmail());
		model.addAttribute("userpic", user.getUserpic());
		model.addAttribute("companies", user.getCompanies());

		return "user";
	}

	@GetMapping("")
	public String getCompanies(Model model) {

		model.addAttribute("users", userService.getUsers());

		return "users";
	}
}
