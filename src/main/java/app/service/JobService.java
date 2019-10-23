package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.Company;
import app.model.Job;
import app.model.User;
import app.repository.JobRepository;

@Service
public class JobService {

	@Autowired
	JobRepository jobRepository;

	@Autowired
	UserService userService;

	@Autowired
	CompanyService companyService;

	public boolean addJob(Job job, String customer, String creator) {

		User customerPerson = userService.loadUserByName(creator);

		Company company = customer.equals("Individual") ? null : companyService.loadCompanyByName(customer);

		job.setCreator(customerPerson);
		job.setCustomer(company);
		jobRepository.save(job);

		return true;
	}

	public Job loadJobById(long id) {
		return jobRepository.findJobById(id);
	}

	public List<Job> loadJobs() {
		return jobRepository.findAll();
	}

	public List<Job> searchByQuery(String query) {
		return jobRepository.findByTitleContaining(query);
	}
}
