package com.cpswork.backend.utils;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class JwtResponse implements Serializable {

	private static final Long serialVersionUID = -8091879091924046844L;

	private String token;
	private UUID userId;
	private String username;
	private String email;
	private List roles;

	public JwtResponse() {
	}

	public JwtResponse(String token, UUID userId, String username, String email, List roles) {
		this.token = token;
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List getRoles() {
		return roles;
	}

	public void setRoles(List roles) {
		this.roles = roles;
	}
}