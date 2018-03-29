package io.josedb.framework.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class DefaultWebRequestHandler extends AbstractHandler{
	
	@Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) {
        if (response.isCommitted() || baseRequest.isHandled())
            return;
        
        //TODO generate a better page
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        baseRequest.setHandled(true);
      }

}
