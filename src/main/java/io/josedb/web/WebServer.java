package io.josedb.web;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerList;

import io.josedb.utils.Config;

public class WebServer {
	
	private Server jettyServer;
	private Config config;
	
	public WebServer(Config config) {
		this.config = config;
		this.jettyServer = new Server();
		
		//TODO configure server & connectors
		// http connector
        ServerConnector http = new ServerConnector(this.jettyServer);
        http.setHost(config.get("host"));
        http.setPort(config.getInt("httpPort"));
        
        // https connector
        ServerConnector https = new ServerConnector(this.jettyServer);
        https.setHost(config.get("host"));
        https.setPort(config.getInt("httpsPort"));
        
        this.jettyServer.setConnectors(new Connector[] {http, https});
        
		// Add base handlers
		HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { new WebRequestHandler(), new DefaultWebRequestHandler() });
        
        
        this.jettyServer.setHandler(handlers);
	}
	
	public void start() throws Exception{
		this.jettyServer.start();
		this.jettyServer.dumpStdErr();
		this.jettyServer.join();
	}

}
