package io.josedb.framework.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
//import org.eclipse.jetty.util.log.Log;
//import org.eclipse.jetty.util.log.Logger;



public class DefaultWebRequestHandler extends AbstractHandler{
	//TODO private static final Logger LOG = Log.getLogger(DefaultWebRequestHandler.class);
	
	@Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) {
        if (response.isCommitted() || baseRequest.isHandled())
            return;
        
        //TODO generate a better page
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        baseRequest.setHandled(true);
      }

}
