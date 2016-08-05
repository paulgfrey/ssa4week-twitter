package com.ironyard.doorway.handlers;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.ironyard.doorway.domain.Tweet;
import com.ironyard.doorway.domain.User;

public class RestAPIHandler extends AbstractHandler {

	public RestAPIHandler() {
	}

	@Override
	public void handle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		if (target != null && !target.toLowerCase().contains("rest")) {
			return;
		}
		System.out.println("target=" + target);

		if (target.contains("userinfo")) {
			getUserInfo(target, request, response);
		} else if (target.contains("tweets")) {
			getTweets(target, request, response);
		} else if (target.contains("followers")) {
			getFollowers(target, request, response);
		} else {
			System.err.println("UNHANDLED CALL!");
			return;
		}
		baseRequest.setHandled(true);
	}

	private void getFollowers(String target, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		// Get User ID
		int ndx = target.lastIndexOf("/");
		if (ndx == -1) {
			System.err
					.println("getUserInfo missing userId [usage: /rest/userinfo/<userId>");
			return;
		}
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);

		// TODO get User Info from followerDAO.

		User user = new User();
		user.setUserId("Follower");
		user.setPassword("password");
		user.setFirstName("Jane");
		user.setLastName("Doe");

		// Generate JSON
		response.getWriter().println("[ " + user + " ]");

		Utils.setUserId(response, user.getUserId());
	}

	private void getTweets(String target, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		// Get User ID
		String[] fields = target.split("/");
		String userId = fields[2];
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);

		// TODO get User Info from tweetsDAO.

		Tweet tweet1 = new Tweet();
		tweet1.setToUserId(userId);
		tweet1.setFromUserId("Follower");
		tweet1.setMsg("This is a test tweet!");
		tweet1.setDate(new Date(2016, 8, 5));
		
		Tweet tweet2 = new Tweet();
		tweet2.setToUserId(userId);
		tweet2.setFromUserId("Follower");
		tweet2.setMsg("This is a test tweet!");
		tweet2.setDate(new Date(2016, 8, 1));

		// Generate JSON
		response.getWriter().println("[ " + tweet1  + ", " + tweet2 + " ]");

		Utils.setUserId(response, userId);
	}

	private void getUserInfo(String target, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		// Get User ID
		int ndx = target.lastIndexOf("/");
		if (ndx == -1) {
			System.err
					.println("getUserInfo missing userId [usage: /rest/userinfo/<userId>");
			return;
		}
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);

		// TODO get User Info from userDAO.

		User user = new User();
		user.setUserId("User");
		user.setPassword("password");
		user.setFirstName("John");
		user.setLastName("Doe");

		// Generate JSON
		response.getWriter().println("[ " + user + " ]");

		Utils.setUserId(response, user.getUserId());
	}
}
