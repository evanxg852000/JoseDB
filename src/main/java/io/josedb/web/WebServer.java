package io.josedb.web;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;

public class WebServer {
	
	private Server jettyServer;
	private Config config;
	
	public WebServer(Config config) {
		this.config = config;
		this.jettyServer = new Server();
		
		//TODO configure server & connectors
		// http connector
        ServerConnector http = new ServerConnector(this.jettyServer);
        http.setHost("localhost");
        http.setPort(3000);
        
        // https connector
        ServerConnector https = new ServerConnector(this.jettyServer);
        https.setHost("localhost");
        https.setPort(4000);
        
        this.jettyServer.setConnectors(new Connector[] {http, https});
        
		// Add base handlers
		HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { new WebRequestHandler(), new DefaultHandler() });
        
        
        this.jettyServer.setHandler(handlers);
	}
	
	public void start() throws Exception{
		this.jettyServer.start();
		this.jettyServer.dumpStdErr();
		this.jettyServer.join();
	}

}
