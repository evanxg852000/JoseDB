package io.josedb.framework.web;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.NCSARequestLog;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerList;

import io.josedb.utils.Config;
import io.josedb.utils.JoseDbInfo;

public class WebServer {
	
	private Server jettyServer;
	private Config config;
	
	public WebServer(Config config) {
		this.config = config;
		this.jettyServer = new Server();
		
		// request logger
		if(JoseDbInfo.getConfig().getBool("debug")){
			NCSARequestLog requestLogger = new NCSARequestLog(JoseDbInfo.getConfig().get("requestLogFile"));
			requestLogger.setAppend(true);
			requestLogger.setExtended(false);
			requestLogger.setLogTimeZone("GMT");
			requestLogger.setLogLatency(true);
			requestLogger.setRetainDays(90);
			this.jettyServer.setRequestLog(requestLogger);
		}
		
		// http connector
        ServerConnector http = new ServerConnector(this.jettyServer);
        http.setHost(config.get("host"));
        http.setPort(config.getInt("httpPort"));
        
        // https connector
        ServerConnector https = new ServerConnector(this.jettyServer);
        https.setHost(config.get("host"));
        https.setPort(config.getInt("httpsPort"));
        
        this.jettyServer.setConnectors(new Connector[] {http, https});
	}

	public Config getConfig(){
		return this.config;
	}
	
	public void mountRouter(WebRequestRouter router) {
		// Add base handlers
		HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[] { new WebRequestHandler(router), new DefaultWebRequestHandler() });
		
		this.jettyServer.setHandler(handlers);
	}
	
	public void start() throws Exception{
		this.jettyServer.start();
		this.jettyServer.dumpStdErr();
		this.jettyServer.join();
	}

}
