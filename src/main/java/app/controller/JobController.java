package app.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.model.Job;
import app.model.User;
import app.service.JobService;
import app.service.UserService;

@Controller
public class JobController {

	@Autowired
	private UserService userService;

	@Autowired
	private JobService jobService;

	@GetMapping("/add-job")
	public String getJobForm(Principal principal, Model model) {

		User customer = userService.loadUserByName(principal.getName());

		model.addAttribute("companies", customer.getCompanies());

		return "add_job";
	}

	@PostMapping("/add-job")
	public String addJob(Job job, @RequestParam(value = "company_name") String companyName, Principal principal) {

		String customerName = principal.getName();

		jobService.addJob(job, companyName, customerName);

		return "redirect:/";
	}

	@GetMapping("/jobs/{id}")
	public String getJob(@PathVariable long id, Model model) {

		Job job = jobService.loadJobById(id);

		model.addAttribute("job", job);

		return "job";
	}

	@GetMapping("/jobs")
	public String getJob(Model model) {

		List<Job> jobs = jobService.loadJobs();

		model.addAttribute("jobs", jobs);

		return "jobs";
	}
}
