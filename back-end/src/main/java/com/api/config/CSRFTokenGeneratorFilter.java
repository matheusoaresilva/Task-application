package com.api.config;

import java.io.IOException;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CSRFTokenGeneratorFilter extends OncePerRequestFilter {

	 private static final String RESPONSE_HEADER_NAME = "TESTE";
	private static final String RESPONSE_PARAM_NAME = "TESTE";
	private static final String RESPONSE_TOKEN_NAME = "TESTE";
	private static final String REQUEST_ATTRIBUTE_NAME = "TESTE";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	    CsrfToken token = (CsrfToken) request.getAttribute(REQUEST_ATTRIBUTE_NAME);

	    if (token != null) {
	      response.setHeader(RESPONSE_HEADER_NAME, token.getHeaderName());
	      response.setHeader(RESPONSE_PARAM_NAME, token.getParameterName());
	      response.setHeader(RESPONSE_TOKEN_NAME , token.getToken());
	    }

	    filterChain.doFilter(request, response);
	
	}
}

