package io.josedb.web.config;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.josedb.framework.web.HttpRequestHandler;

public class ConfigurationRequestHandler extends HttpRequestHandler{

	@Override
	public void get(HttpServletRequest request, Map<String, String> params, HttpServletResponse response)
			throws IOException {
		super.get(request, params, response);
		response.setContentType("text/html; charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().println("Welcome to JoseDB config endpoint");
	}

}