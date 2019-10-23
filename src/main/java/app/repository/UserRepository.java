package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByName(String username);

	User findByEmail(String email);

	User findByActivationCode(String code);

	List<User> findByNameContaining(String name);
}
