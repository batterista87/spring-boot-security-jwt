package it.andrea.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.andrea.domain.User;
import it.andrea.repository.UserRepository;

@Service //("userDetailsService")
public class UserDetailsServiceCustom implements UserDetailsService {
	
	private final Logger log = LoggerFactory.getLogger(UserDetailsServiceCustom.class);
	
	private final UserRepository userRepository;
	
	public UserDetailsServiceCustom(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		log.debug("Entering into UserDetailsServiceCustom");
		
        return userRepository.findByUsername(username)
                			 .map(user -> createSpringSecurityUser(user))
                			 .orElseThrow(() -> new UsernameNotFoundException("User " + username + " was not found in the database"));
	}
	
    private org.springframework.security.core.userdetails.User createSpringSecurityUser(User user) {
//        if (!user.isActivated()) {
//            throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
//        }
//        List<GrantedAuthority> grantedAuthorities = user
//            .getAuthorities()
//            .stream()
//            .map(authority -> new SimpleGrantedAuthority(authority.getName()))
//            .collect(Collectors.toList());
    	List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

}
