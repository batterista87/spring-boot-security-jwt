package it.andrea.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.andrea.security.JwtTokenProvider;

@RestController
public class WelcomeResource {
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/")
	public String welcome() {
		return "Welcome !!!";
	}
	
	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequestDTO authRequest) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		}
		catch(Exception e) {
			throw new Exception("Invalid username or password");
		}
		
		return jwtTokenProvider.generateToken(authRequest.getUsername());
	}

}
