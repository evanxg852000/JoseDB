package io.josedb.web;

import java.util.Arrays;

import io.josedb.framework.web.WebRequestRouter;
import io.josedb.web.config.ConfigurationRequestHandler;

public class Routes {
	private WebRequestRouter router;
	
	public Routes() {
		this.router = new WebRequestRouter();
		this.initialize();
	}
	
	public static WebRequestRouter getRouter(){
		return (new Routes()).router;
	}
	
	private void initialize(){
		this.router.addRoute("/", new HomeRequestHandler(), Arrays.asList("get"));
		
		// _config endpoint
		this.router.addRoute("/_config", new ConfigurationRequestHandler(), Arrays.asList("get", "post", "put"));
		
		
	}
	
}
