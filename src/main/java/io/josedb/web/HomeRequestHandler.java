package io.josedb.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import io.josedb.framework.web.HttpRequestHandler;
import io.josedb.utils.JoseDbInfo;

public class HomeRequestHandler extends HttpRequestHandler{

	@Override
	public void get(HttpServletRequest request, Map<String, String> params, HttpServletResponse response)
			throws IOException {
		super.get(request, params, response);
		response.setContentType("application/json; charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		
		//Gson response = new G
		JsonObject rspObj = new JsonObject();
		rspObj.addProperty("product", JoseDbInfo.getProduct());
		rspObj.addProperty("version", JoseDbInfo.getVersion());
		rspObj.addProperty("vendor", JoseDbInfo.getVendor());
		rspObj.addProperty("message", JoseDbInfo.getMessage());
		rspObj.addProperty("tagline", JoseDbInfo.getTagLine());
		
		 //Gson rspObj = new GsonBuilder().create();
		 //GSon
        //gson.toJson("Hello", response.getWriter());
		
		response.getWriter().println(rspObj.toString());
	}

}