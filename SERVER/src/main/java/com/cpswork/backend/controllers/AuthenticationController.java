/**
 * @author: ntwari egide
 * @description: authentication end points controller
 */

package com.cpswork.backend.controllers;

import com.cpswork.backend.config.JwtRequestFilter;
import com.cpswork.backend.config.JwtTokenUtil;
import com.cpswork.backend.dtos.UserDto;
import com.cpswork.backend.exceptions.UserNotFoundException;
import com.cpswork.backend.models.User;
import com.cpswork.backend.serviceImpl.UserDetailsImpl;
import com.cpswork.backend.serviceImpl.UserServiceImpl;
import com.cpswork.backend.services.JwtUserDetailsService;
import com.cpswork.backend.utils.JwtRequest;
import com.cpswork.backend.utils.JwtResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/auths")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private UserServiceImpl userService;

	private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

	@RequestMapping(value = "/login", method = RequestMethod.POST)

	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest loginRequest) throws Exception {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtTokenUtil.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt,
				userDetails.getId(),
				userDetails.getUsername(),
				userDetails.getEmail(),
				roles));
	}

	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody UserDto user) throws Exception {
		User savedUser = userService.signupUser(user);

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(savedUser.getUsername(), user.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtTokenUtil.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt,
				userDetails.getId(),
				userDetails.getUsername(),
				userDetails.getEmail(),
				roles));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			System.out.println("Reached here: "+username);

		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new UserNotFoundException("User name or password is incorrect");
		}
	}
}
