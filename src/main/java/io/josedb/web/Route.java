package io.josedb.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Route{
	private String spec;
	private Pattern pattern;
	private List<String> params;
	private List<String> supportedMethods;
	private HttpRequestHandler handler;
	
	public Route(String spec, HttpRequestHandler handler, String[] methods) {
		this.spec = spec;
		this.supportedMethods = new ArrayList<String>();
		for (String item : methods) {
			this.supportedMethods.add(item.toUpperCase());
		}
		this.handler = handler;
				
		List<String> parsedSpec = Route.parseSpec(spec);
		if(parsedSpec.isEmpty())
			return;
			
		this.pattern = Pattern.compile(parsedSpec.get(0));
		if(pattern == null) 
			return;
		
		parsedSpec.remove(0);
		this.params = parsedSpec;
	}

	public boolean isOk(){	
		return (this.handler != null && this.pattern != null && this.params != null);
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
	
	public List<String> getSupportedMethods() {
		return this.supportedMethods;
	}
	
	public HttpRequestHandler getHandler() {
		return this.handler;
	}
	
	public static List<String> parseSpec(String spec){
		/*
		 * /test
		 * /test/{name?}
		 * ^custom_name\\.(?P<format>[a-z0-9-_]+)/?$"
		 * ^project_config/$
		 * ^project_config/(?P<product>\w+)/$
		 * ^project_config/(?P<product>\w+)/(?P<project_id>\w+)/$
		 */		
		List<String> parsedSpec = new ArrayList<String>();
		
		spec = spec.trim();
		spec = (spec.startsWith("/"))? spec.substring(1) : spec;
		spec = (spec.endsWith("/"))? spec.substring(0, spec.length()) : spec;
		String[] parts = spec.split("/");
		String pattern = "^";
		for (String part : parts) {
			if(part.startsWith("{") && part.endsWith("}")){
				part = part.substring(1);
				part = part.substring(0, part.length());
				parsedSpec.add(part);
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
		
		parsedSpec.add(0, pattern);
		return parsedSpec;
	}
	 
}
