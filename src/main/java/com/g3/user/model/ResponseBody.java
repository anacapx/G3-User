package com.g3.user.model;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseBody {

	private Object principal;
	private Object credentials;
	private Collection<Authority> authorities;

	public Object getPrincipal() {
		return principal;
	}

	public void setPrincipal(Object principal) {
		this.principal = principal;
	}

	public Object getCredentials() {
		return credentials;
	}

	public void setCredentials(Object credentials) {
		this.credentials = credentials;
	}

	public Collection<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<Authority> authorities) {
		this.authorities = authorities;
	}

}
