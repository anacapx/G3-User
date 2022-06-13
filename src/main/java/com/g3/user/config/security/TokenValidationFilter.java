package com.g3.user.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.g3.user.service.impl.RestService;

public class TokenValidationFilter extends OncePerRequestFilter {
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

			String token = request.getHeader("Authorization");
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = RestService.validateToken(token);
			if (usernamePasswordAuthenticationToken != null) {			
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			
			filterChain.doFilter(request, response);			

	}

}
