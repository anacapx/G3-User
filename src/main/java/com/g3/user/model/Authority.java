package com.g3.user.model;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Authority implements GrantedAuthority {
	
private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String authority;
	
	@Override
	public String getAuthority() {
		return this.name;
	}

}
