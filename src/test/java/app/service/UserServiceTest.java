package app.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import app.model.User;
import app.repository.UserRepository;

//@ExtendWith(SpringExtension.class)
//@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepo;

	@MockBean
	private MailService mailService;

	@Test
	public void addUserTest() {
		User user = new User();
		user.setEmail("some@mail.com");

		assertTrue(userService.addUser(user));

		assertFalse(user.isEnabled());
		assertNotNull(user.getActivationCode());

		verify(userRepo, times(1)).save(user);
		verify(mailService, times(1))
			.send(
				eq(user.getEmail()), 
				anyString(), 
				anyString()
			);
	}
	
	@Test
	public void addUserFailTest() {
		User user = new User();
		user.setName("username");
		
		doReturn(new User()).when(userRepo).findByName("username");

		assertFalse(userService.addUser(user));
		
		verify(userRepo, times(0)).save(any(User.class));
		verify(mailService, times(0))
			.send(
				anyString(),
				anyString(), 
				anyString()
			);
	}
	
	@Test
	public void activateUserTest() {
		User user = new User();
		user.setActivationCode("code");
		
		doReturn(user).when(userRepo).findByActivationCode("code");
		
		assertTrue(userService.activateUser("code"));
		assertTrue(user.isEnabled());
		assertNull(user.getActivationCode());
		
		verify(userRepo, times(1)).save(user);
	}
	
	@Test
	public void activateUserFailTest() {
		assertFalse(userService.activateUser("code"));
		
		verify(userRepo, times(0)).save(any(User.class));
	}
}
