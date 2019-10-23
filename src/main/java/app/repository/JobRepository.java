package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.model.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
	public Job findJobById(long id);
	
	List<Job> findByTitleContaining(String title);
}
