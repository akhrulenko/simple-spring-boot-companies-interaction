package app.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "jobs")
public class Job {

	@Id
	@GeneratedValue
	private long id;
	private String title;
	private String description;

	@OneToOne(cascade = CascadeType.ALL)
	private User creator;

	@ManyToOne(cascade = CascadeType.ALL)
	private Company customer;

	@OneToOne(cascade = CascadeType.ALL)
	private Company executor;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Company getCustomer() {
		return customer;
	}

	public void setCustomer(Company customer) {
		this.customer = customer;
	}

	public Company getExecutor() {
		return executor;
	}

	public void setExecutor(Company executor) {
		this.executor = executor;
	}
}