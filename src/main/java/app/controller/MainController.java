package app.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.service.CompanyService;
import app.service.JobService;
import app.service.UserService;

@Controller
public class MainController {

	@Autowired
	private UserService userService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private JobService jobService;

	@GetMapping("/")
	public String sayHello(@AuthenticationPrincipal Principal principal) {

		return "index";
	}

	@GetMapping("/search")
	public String getSearch(@RequestParam(value = "q") String query, @RequestParam(value = "type") String type,
			Model model) {

		List<?> entities = null;

		switch (type) {
		case ("jobs"):
			entities = jobService.searchByQuery(query);
			break;
		case ("users"):
			entities = userService.searchByQuery(query);
			break;
		case ("companies"):
			entities = companyService.searchByQuery(query);
			break;
		}

		model.addAttribute("type", type);
		model.addAttribute("entities", entities);

		return "search";
	}

	@GetMapping("/stream")
	public String getStream() {
		return "stream";
	}
}