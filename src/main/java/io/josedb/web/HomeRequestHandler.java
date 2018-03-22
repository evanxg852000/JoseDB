package io.josedb.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import io.josedb.JoseDBInfo;
import io.josedb.framework.web.HttpRequestHandler;

public class HomeRequestHandler extends HttpRequestHandler{

	@Override
	public void get(HttpServletRequest request, Map<String, String> params, HttpServletResponse response)
			throws IOException {
		super.get(request, params, response);
		response.setContentType("application/json; charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		
		//Gson response = new G
		JsonObject rspObj = new JsonObject();
		rspObj.addProperty("version", JoseDBInfo.getVersion());
		rspObj.addProperty("message", JoseDBInfo.getMessage());
		rspObj.addProperty("tagline", JoseDBInfo.getTagLine());
		
		 //Gson rspObj = new GsonBuilder().create();
		 //GSon
        //gson.toJson("Hello", response.getWriter());
		
		response.getWriter().println(rspObj.toString());
	}

}