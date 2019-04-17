package com.infy.visitor.management.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class CORSFilter extends OncePerRequestFilter {


	/**
	 *
	 * @param request
	 * @param response
	 * @param filterChain
	 * @throws ServletException
	 * @throws IOException
	 */

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		setResponseHeader(response);
		filterChain.doFilter(request, response);
	}

	private void setResponseHeader(HttpServletResponse response) {
		response.setHeader(RestTemplateConstants.ALLOW_ORIGIN.getName(), RestTemplateConstants.ALLOW_ORIGIN.getValue());
		response.setHeader(RestTemplateConstants.ALLOW_METHODS.getName(),
				RestTemplateConstants.ALLOW_METHODS.getValue());
		response.setHeader(RestTemplateConstants.MAX_AGE.getName(), RestTemplateConstants.MAX_AGE.getValue());
		response.setHeader(RestTemplateConstants.ALLOW_CREDENTIALS.getName(),
				RestTemplateConstants.ALLOW_CREDENTIALS.getValue());
		response.setHeader(RestTemplateConstants.ALLOW_HEADERS.getName(),
				RestTemplateConstants.ALLOW_HEADERS.getValue());
		response.setHeader(RestTemplateConstants.HEADER_KEY.getName(), RestTemplateConstants.HEADER_VALUE.getValue());
	}

}
