package io.josedb;


import io.josedb.framework.web.WebServer;
import io.josedb.utils.Config;
import io.josedb.web.Routes;

public class Program {
	
	public static void main(String[] args) {
		System.out.println("Hello from JoseDB");
		try {
			Config config = Config.load("config.properties");
			WebServer server = new WebServer(config);
			server.mountRouter(Routes.getRouter());
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
}
