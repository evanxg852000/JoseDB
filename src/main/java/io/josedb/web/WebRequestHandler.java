package io.josedb.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class WebRequestHandler extends AbstractHandler{
	private WebRequestRouter router;
	
	public WebRequestHandler() {
		// TODO create and configure routing system here
		this.router = new WebRequestRouter();
	}
	
	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		super.handle(target, baseRequest, request, response);
		
		response.setContentType("text/html; charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().println("Hello From JoseDB!");	
		baseRequest.setHandled(true);
		
		//match route and dispatch
		boolean handled = this.router.route(request, response);
        //baseRequest.setHandled(handled);
	}

}
