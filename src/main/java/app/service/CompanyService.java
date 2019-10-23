package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.Company;
import app.model.User;
import app.repository.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	CompanyRepository compRepos;

	@Autowired
	UserService userService;
	
	public Company loadCompanyByName(String name) {
		Company company = compRepos.findByName(name);

		return company;
	}

	public boolean addCompany(Company company, String directorName) {

		User director = userService.loadUserByName(directorName);

		if (compRepos.findByName(company.getName()) != null) {
			return false;
		}

		company.setDirector(director);
		compRepos.save(company);

		return true;
	}
	
	public List<Company> getCompanies() {
		return compRepos.findAll();
	}
	
	public List<Company> searchByQuery(String query) {
		return compRepos.findByNameContaining(query);
	}
}
