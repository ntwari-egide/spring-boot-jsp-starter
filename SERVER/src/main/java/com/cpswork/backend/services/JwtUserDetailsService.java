package com.cpswork.backend.services;

import com.cpswork.backend.dtos.UserDto;
import com.cpswork.backend.models.User;
import com.cpswork.backend.repositories.UserRepository;
import com.cpswork.backend.serviceImpl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	public UserDetails loadUserByUsernameAndPassword(String username,String password) throws UsernameNotFoundException {
		User user = userRepository.findByUsernameAndPassword(username,password);

		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}

	public static Collection<? extends GrantedAuthority > getAuthorities(User userDao) {
		String[] userRoles = new String[0];
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);

		System.out.println(userDao);

		return authorities;
	}

	public User save(UserDto user) {
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userRepository.save(newUser);
	}

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(s);

		return UserDetailsImpl.build(user);
	}
}