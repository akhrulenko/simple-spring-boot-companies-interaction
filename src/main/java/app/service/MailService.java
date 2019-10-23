package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String username;

	public void send(String to, String subject, String message) {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom(username);
		mail.setTo(to);
		mail.setSubject(subject);
		mail.setText(message);
		mailSender.send(mail);
	}
}
