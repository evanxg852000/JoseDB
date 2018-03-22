package io.josedb.framework.web;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Route{
	private String spec;
	private Pattern pattern;
	private List<String> params;
	private List<String> supportedMethods;
	private HttpRequestHandler handler;
	
	public Route(String spec, HttpRequestHandler handler, List<String> methods) {
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
		 * ^/project_config$
		 * ^/project_config(\/(?<product>\w+))$
		 * ^/project_config(\/(?<product>\w+))(\/(?<project_id>\w+)?)$
		 */		
		List<String> parsedSpec = new ArrayList<String>();
		
		spec = spec.trim();
		spec = (spec.startsWith("/"))? spec.substring(1) : spec;
		spec = (spec.endsWith("/"))? spec.substring(0, spec.length()-1) : spec;
		String[] parts = spec.split("/");
		String pattern = "^";
		for (String part : parts) {
			if(part.startsWith("{") && part.endsWith("}")){
				part = part.substring(1);
				part = part.substring(0, part.length()-1);
				boolean optionalParam = false;
				if(part.startsWith("?")){
					part = part.substring(1);
					optionalParam = true;
				}
				parsedSpec.add(part);
				pattern +="(\\/(?<"+part+">\\w+)";
				if(optionalParam)
					pattern +="?)";
				else
					pattern +=")";
			}else{
				pattern +="\\/"+part;
			}
		}
		pattern += "$";
		
		parsedSpec.add(0, pattern);
		return parsedSpec;
	}
	 
}
