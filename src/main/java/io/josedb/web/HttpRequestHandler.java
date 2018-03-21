package io.josedb.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HttpRequestHandler {
	public void handle(HttpServletRequest request, Map<String, String> params, HttpServletResponse response) throws IOException;
}
