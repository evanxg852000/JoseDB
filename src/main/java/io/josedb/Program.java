package io.josedb;


import io.josedb.utils.Config;
import io.josedb.web.WebServer;

public class Program {
	public static final String VERSION="0.0.1";
	
	public static void main(String[] args) {
		System.out.println("Hello from JoseDB");
		

		try {
			Config config = Config.load("config.properties");
			new WebServer(config).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
}
