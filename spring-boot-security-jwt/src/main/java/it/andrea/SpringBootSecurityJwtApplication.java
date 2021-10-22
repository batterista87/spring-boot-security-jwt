package it.andrea;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.andrea.domain.User;
import it.andrea.repository.UserRepository;
import it.andrea.service.UserDetailsServiceCustom;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SpringBootSecurityJwtApplication implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private final Logger log = LoggerFactory.getLogger(UserDetailsServiceCustom.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityJwtApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.debug("Executing CommandLineRunner");
		
		List<User> users = List.of(
				new User("admin", passwordEncoder.encode("admin")),
				new User("user", passwordEncoder.encode("user"))
				);

		userRepository.saveAll(users);
	}

}
