package app.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import app.model.User;
import app.repository.UserRepository;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
//@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(NoOpPasswordEncoder.getInstance())
				.usersByUsernameQuery("select u.name,u.password, u.enabled from users u where u.name = ?")
				.authoritiesByUsernameQuery("select u.name, 'ROLE_USER' from users u where u.name = ?");
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/signup", "/activation/*").permitAll().and().authorizeRequests()
				.antMatchers("/**").hasRole("USER").and().formLogin().permitAll().and().logout().logoutSuccessUrl("/")
				.permitAll().and().httpBasic().and().csrf().disable();
	}

	@Bean
	public PrincipalExtractor principalExtractor(UserRepository userRepos) {
		return map -> {
			String email = (String) map.get("email");

			User user = userRepos.findByEmail(email);

			if (user == null) {
				user = new User();
				user.setName((String) map.get("name"));
				user.setEmail((String) map.get("email"));
				user.setGender((String) map.get("gender"));
				user.setLocale((String) map.get("locale"));
				user.setUserpic((String) map.get("picture"));
			}

			return userRepos.save(user);
		};
	}
}
