package com.ironyard.doorway.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.ResourceHandler;

public class MyResourceHandler extends ResourceHandler {
	
	public MyResourceHandler() {
		super();
	}

	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// Get cookie if present
		String userId = Utils.getUserId(request);
		if(userId.length() == 0) {
			userId = "User";
		}
		super.handle(target, baseRequest, request, response);
		// Now tack on Cookie
		Utils.setUserId(response, userId);
	}
}
