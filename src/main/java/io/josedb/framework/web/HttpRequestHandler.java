package io.josedb.framework.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpMethod;

public abstract class HttpRequestHandler {
	
	public void handle(HttpServletRequest request, Map<String, String> params, HttpServletResponse response) throws IOException {
		String method = request.getMethod();
				
		if (HttpMethod.GET.is(method)){
			this.get(request, params, response);
			return;
		}
		
		if (HttpMethod.POST.is(method)){
			this.post(request, params, response);
			return;
		}
		
		if (HttpMethod.PUT.is(method)){
			this.put(request, params, response);
			return;
		}
		
		if (HttpMethod.DELETE.is(method)){
			this.delete(request, params, response);
		}
		
	}
	
	public void get(HttpServletRequest request, Map<String, String> params, 
			HttpServletResponse response) throws IOException {
	}
	
	public void post(HttpServletRequest request, Map<String, String> params, 
			HttpServletResponse response) throws IOException {
	}

	public void put(HttpServletRequest request, Map<String, String> params, 
			HttpServletResponse response) throws IOException {
	}

	public void delete(HttpServletRequest request, Map<String, String> params, 
			HttpServletResponse response) throws IOException {
	}
}
