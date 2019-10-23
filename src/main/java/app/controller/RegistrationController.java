package app.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import app.model.User;
import app.service.UserService;

@Controller
public class RegistrationController {

	@Autowired
	private UserService userService;

	@GetMapping("/signup")
	public String getSignup() {
		return "signup";
	}

	@PostMapping("/signup")
	public String signup(User user, Map<String, Object> model) {

		if (!userService.addUser(user)) {
			model.put("message", "User exists!");
			return "signup";
		}

		return "redirect:/";
	}

	@GetMapping("/activation/{code}")
	public String activateUser(@PathVariable String code, Map<String, Object> model) {

		if (!userService.activateUser(code)) {
			model.put("message", "Activation code is not valid!");
			return "signup";
		}

		return "redirect:/";
	}
}