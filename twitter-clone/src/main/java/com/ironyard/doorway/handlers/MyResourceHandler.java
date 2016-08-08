package com.ironyard.doorway.handlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class MyResourceHandler extends AbstractHandler {
	String path = ".";
	
	public MyResourceHandler(String path) {
		super();
		this.path = path;
	}

	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		if(target != null) {
			if(! (target.endsWith(".html") ||
					target.endsWith("htm") || 
					target.endsWith(".css") ||
					target.endsWith(".js") ||
					target.endsWith(".jpg") ||
					target.endsWith(".png"))) {
				return;
			}
		}
		System.out.println("target=" + target);
		// Get cookie if present
		String userId = Utils.getUserId(request);
		if(userId.length() == 0) {
			userId = "User";
		}
		File file = new File(path + target);
		if(file.exists()) {
			response.setContentType("text/html;charset=utf-8");
			if(target.endsWith(".css")) {
				response.setContentType("text/css;charset=utf-8");
			}
			else if(target.endsWith(".jpg")) {
				response.setContentType("image/jpeg");
			}
			else if(target.endsWith(".png")) {
				response.setContentType("image/png");
			}
			else if(target.endsWith(".js")) {
				response.setContentType("application/javascript");
			}
			FileReader fr = new FileReader(file);
			try {
				BufferedReader br = new BufferedReader(fr);
				String line;
				while((line = br.readLine()) != null) {
					response.getWriter().println(line);
				}
				response.setStatus(HttpServletResponse.SC_OK);
				baseRequest.setHandled(true);
			}
			catch(IOException e) {
				e.printStackTrace(System.err);
				return;
			}
			finally {
				if(fr != null) {
					fr.close();
				}
			}
		}
		// Now tack on Cookie
		Utils.setUserId(request, response, userId);
	}
}
