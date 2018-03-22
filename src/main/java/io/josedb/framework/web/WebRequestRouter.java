package io.josedb.framework.web;

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
	
	public void addRoute(String spec, HttpRequestHandler handler, List<String> methods) {
		Route route = new Route(spec, handler, methods);
		if(!route.isOk()){
			return; // TODO log this as error
		}
		this.routes.add(route);
	}
	
	public boolean route(HttpServletRequest request, HttpServletResponse response){
		String url= request.getPathInfo();
		String method = request.getMethod();
		
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
