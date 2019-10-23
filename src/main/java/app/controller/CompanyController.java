package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.model.Company;
import app.service.CompanyService;

@Controller
public class CompanyController {

	@Autowired
	CompanyService compService;

	@GetMapping("/add-company")
	public String getCompanyForm() {
		return "add_company";
	}

	@PostMapping("/add-company")
	public String addCompany(Company company, @RequestParam(value = "director_name") String directorName) {

		compService.addCompany(company, directorName);

		return "redirect:/";
	}

	@GetMapping("/companies")
	public String getCompanies(Model model) {

		model.addAttribute("companies", compService.getCompanies());

		return "companies";
	}
}
