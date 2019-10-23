package app.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import app.model.User;
import app.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepos;

	@Autowired
	private MailService mailService;

	@Value("${hostname}")
	private String hostname;

	public User loadUserByName(String name) {
		User user = userRepos.findByName(name);

		return user;
	}

	public void sendEmail(User user, String title, String message) {
		if (user.getEmail() != null) {

			mailService.send(user.getEmail(), title, message);
		}
	}

	public boolean addUser(User user) {
		if (userRepos.findByName(user.getName()) != null) {
			return false;
		}

		user.setEnabled(false);
		user.setActivationCode(UUID.randomUUID().toString());
		userRepos.save(user);

		sendEmail(user, "Activation", String.format(
				"Welcome to CompInter, %s!\nPlease, activate your account by this link: http://%s/activation/%s",
				user.getName(), hostname, user.getActivationCode()));

		return true;
	}

	public boolean activateUser(String code) {
		User user = userRepos.findByActivationCode(code);

		if (user == null) {
			return false;
		}

		user.setEnabled(true);
		user.setActivationCode(null);

		userRepos.save(user);

		return true;
	}
	
	public List<User> getUsers() {
		return userRepos.findAll();
	}

	public List<User> searchByQuery(String query) {
		return userRepos.findByNameContaining(query);
	}
}