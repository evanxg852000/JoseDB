package io.josedb.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class WebRequestRouter {
	private List<Route> routes;
	
	public WebRequestRouter() {
		this.routes = new ArrayList<Route>();
	}
	
	public void addRoute(String spec, HttpRequestHandler handler, String[] methods) {
		
		
		
	}
	
	public boolean route(HttpServletRequest request, HttpServletResponse response){
		//TODO extract request info
		String url="";
		String method="";
		
		Matcher matcher;
		for (Route route : this.routes) {
			matcher = route.getPattern().matcher(url);
			if(matcher.matches() && route.getMethods().contains(method)) {
				Map<String, String> params = new HashMap<String, String>();
				for (String paramKey : route.getParams()) {
					params.put(paramKey, matcher.group(paramKey));
				}
				route.getHandler().handle(request, params, response);;
				return true;
			}
		}
		return false;
	}
	
	private Pattern parsePattern(String spec){
		/*
		 * /test
		 * /test/{name?}
		 * ^custom_name\\.(?P<format>[a-z0-9-_]+)/?$"
		 * ^project_config/$
		 * ^project_config/(?P<product>\w+)/$
		 * ^project_config/(?P<product>\w+)/(?P<project_id>\w+)/$
		 */		
		spec = spec.trim();
		spec = (spec.startsWith("/"))? spec.substring(1) : spec;
		spec = (spec.endsWith("/"))? spec.substring(0, spec.length()) : spec;
		String[] parts = spec.split("/");
		String pattern = "^";
		for (String part : parts) {
			if(part.startsWith("{") && part.endsWith("}")){
				part = part.substring(1);
				part = part.substring(0, part.length());
				boolean optionalParam = false;
				if(part.startsWith("?")){
					part = part.substring(1);
					optionalParam = true;
				}
				pattern +="/(?P<"+part+">\\w+)";
				if(optionalParam)
					pattern +="?";
			}else{
				pattern +="/"+part;
			}
		}
		pattern += "$";
		return Pattern.compile(pattern);
	}
	
	private class Route{
		private String spec;
		private Pattern pattern;
		private List<String> params;
		private List<String> methods;
		private HttpRequestHandler handler;
		
		public Route(String spec, Pattern pattern, List<String> params, List<String> methods, HttpRequestHandler handler) {
			this.spec = spec;
			this.pattern = pattern;
			this.params = params;
			this.methods = methods;
			this.handler = handler;
		}
		
		public String getSpec() {
			return this.spec;
		}
		
		public Pattern getPattern() {
			return this.pattern;
		}
		
		public List<String> getParams() {
			return this.params;
		}
		
		public List<String> getMethods() {
			return this.methods;
		}
		
		public HttpRequestHandler getHandler() {
			return this.handler;
		}
		
	}

}
