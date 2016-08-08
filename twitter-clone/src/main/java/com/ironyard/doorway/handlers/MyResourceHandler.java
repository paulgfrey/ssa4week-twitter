package com.ironyard.doorway.handlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

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
	public void handle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		if (target != null) {
			if (!(target.endsWith(".html") || target.endsWith("htm")
					|| target.endsWith(".css") || target.endsWith(".js")
					|| target.endsWith(".jpg") || target.endsWith(".png"))) {
				return;
			}
		}
		System.out.println("target=" + target);
		// Get cookie if present
		String userId = Utils.getUserId(request);
		File file = new File(path + target);
		if (file.exists()) {
			boolean binary = false;
			response.setContentType("text/html;charset=utf-8");
			if (target.endsWith(".css")) {
				response.setContentType("text/css;charset=utf-8");
			} else if (target.endsWith(".jpg")) {
				response.setContentType("image/jpeg");
				binary = true;
			} else if (target.endsWith(".png")) {
				response.setContentType("image/png");
				binary = true;
			} else if (target.endsWith(".js")) {
				response.setContentType("application/javascript");
			}
			FileReader fr = new FileReader(file);
			try {
				if (binary) {
					response.setContentLength((int) file.length());

					FileInputStream in = new FileInputStream(file);
					OutputStream out = response.getOutputStream();

					// Copy the contents of the file to the output stream
					byte[] buf = new byte[1024];
					int count = 0;
					while ((count = in.read(buf)) >= 0) {
						out.write(buf, 0, count);
					}
				} else {
					BufferedReader br = new BufferedReader(fr);
					String line;
					while ((line = br.readLine()) != null) {
						response.getWriter().println(line);
					}
				}
				response.setStatus(HttpServletResponse.SC_OK);
				baseRequest.setHandled(true);
			} catch (IOException e) {
				e.printStackTrace(System.err);
				return;
			} finally {
				if (fr != null) {
					fr.close();
				}
			}
		}
		// Now tack on Cookie
		if(userId.length() > 0) {
			Utils.setUserId(request, response, userId);
		}
	}
}
