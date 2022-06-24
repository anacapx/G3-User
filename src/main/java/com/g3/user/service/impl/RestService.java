package com.g3.user.service.impl;

import java.io.IOException;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.g3.user.model.ResponseBody;
import com.google.gson.Gson;

public class RestService {

	public static UsernamePasswordAuthenticationToken validateToken(String token) throws IOException {

		HttpClient client = HttpClients.custom().build();
		HttpUriRequest request = RequestBuilder.get().setUri(System.getenv("API_AUTH_URL"))
				.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();
		HttpResponse response = client.execute(request);

		String bodyAsString = EntityUtils.toString(response.getEntity());
		ResponseBody responseBody = new Gson().fromJson(bodyAsString, ResponseBody.class);

//		UsernamePasswordAuthenticationToken constructor MUST get the authorities, otherwise it'll set authenticated = false.
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				responseBody.getPrincipal(), 
				responseBody.getCredentials(), 
				responseBody.getAuthorities());

		return usernamePasswordAuthenticationToken;
	}
}
