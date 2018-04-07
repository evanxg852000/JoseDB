package io.josedb.framework.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class WebRequestHandler extends AbstractHandler{
	private WebRequestRouter router;
	
	public WebRequestHandler(WebRequestRouter router) {
		this.router = router;
	}
	
	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		//super.handle(target, baseRequest, request, response);
		
		// Match route and dispatch request to handler
		boolean handled = this.router.route(request, response);
        baseRequest.setHandled(handled);
	}

}
