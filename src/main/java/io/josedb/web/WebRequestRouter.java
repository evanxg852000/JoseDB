package io.josedb.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class WebRequestRouter {
	private List<Route> routes;
	
	public WebRequestRouter() {
		this.routes = new ArrayList<Route>();
	}
	
	public void addRoute(String spec, HttpRequestHandler handler, String[] methods) {
		Route route = new Route(spec, handler, methods);
		if(!route.isOk()){
			return; // TODO log this as error
		}
		this.routes.add(route);
	}
	
	public boolean route(HttpServletRequest request, HttpServletResponse response){
		// TODO extract request info
		String url="";
		String method="";
		System.out.println("URL: "+ request.getPathInfo());
		System.out.println("METHOD: "+ request.getMethod());
		System.out.println("PARAMS: "+ request.getParameterMap());
		
		Matcher matcher;
		for (Route route : this.routes) {
			matcher = route.getPattern().matcher(url);
			if(matcher.matches() && route.getSupportedMethods().contains(method)) {
				Map<String, String> params = new HashMap<String, String>();
				for (String paramKey : route.getParams()) {
					params.put(paramKey, matcher.group(paramKey));
				}
				try {
					route.getHandler().handle(request, params, response);
				} catch (Exception e) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}
				return true;
			}
		}
		return false;
	}
	 
}
