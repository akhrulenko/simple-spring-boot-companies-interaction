package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

	public Company findByName(String name);

	List<Company> findByNameContaining(String name);
}
