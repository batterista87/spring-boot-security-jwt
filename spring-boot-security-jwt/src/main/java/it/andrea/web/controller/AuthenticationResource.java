package it.andrea.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationResource {
	
	@PostMapping("/authentication")
	public ResponseEntity<String> authenticate(@RequestParam String username, @RequestParam String password) {
		
		return ResponseEntity.ok("");
	}

}
