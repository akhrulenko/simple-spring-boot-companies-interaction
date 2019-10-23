package app.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "companies")
public class Company {

	@Id
	@GeneratedValue
	private long id;

	private String name;
	private Address address;
	private String site;

	@OneToMany(mappedBy = "employerCompany", cascade = CascadeType.ALL)
	@Column(name = "jobs")
	private Set<User> employees;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private Set<Job> jobs;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "director_id")
	private User director;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Set<User> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<User> employees) {
		this.employees = employees;
	}

	public Set<Job> getJobs() {
		return jobs;
	}

	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}

	public User getDirector() {
		return director;
	}

	public void setDirector(User director) {
		this.director = director;
	}
}