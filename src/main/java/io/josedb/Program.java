package io.josedb;

import io.josedb.framework.web.WebServer;
import io.josedb.utils.JoseDbInfo;
import io.josedb.utils.JoseDbLogger;
import io.josedb.web.Routes;

public class Program {
	public static void main(String[] args) {
		// Parse command line args
		if(!JoseDbInfo.init(args)){
			//Log
			System.exit(0);
		}
		
		// Initialize & configure logger
	    JoseDbLogger.init();	

		try {
			WebServer server = new WebServer(JoseDbInfo.getConfig());
			server.mountRouter(Routes.getRouter());
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
}
