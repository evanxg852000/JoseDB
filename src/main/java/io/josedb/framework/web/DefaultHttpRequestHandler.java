package io.josedb.framework.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultHttpRequestHandler extends HttpRequestHandler{
	private int statusCode;
	private String responseBody;
	
	public DefaultHttpRequestHandler(int statusCode, String responseBody) {
		this.statusCode = statusCode;
		this.responseBody = responseBody;
	}

	@Override
	public void handle(HttpServletRequest request, Map<String, String> params, HttpServletResponse response) throws IOException {
		response.setStatus(this.statusCode);
		response.getWriter().println(this.responseBody);
	}

}
