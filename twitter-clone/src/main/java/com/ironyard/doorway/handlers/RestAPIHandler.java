package com.ironyard.doorway.handlers;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ironyard.doorway.domain.Tweet;
import com.ironyard.doorway.domain.User;

public class RestAPIHandler extends AbstractHandler {
	private Gson gson = null;
	
	public RestAPIHandler() {
		gson = new GsonBuilder().create();
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

		String userId = Utils.getUserId(request);
		
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

		User[] users = new User[1];
		users[0] = new User();
		users[0].setUserId("Follower");
		users[0].setPassword("password");
		users[0].setFirstName("Jane");
		users[0].setLastName("Doe");
		

		// Generate JSON
		response.getWriter().println(gson.toJson(users));

		Utils.setUserId(response, userId);
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
		tweet1.setDate(new Date());
		
		Tweet tweet2 = new Tweet();
		tweet2.setToUserId(userId);
		tweet2.setFromUserId("Follower");
		tweet2.setMsg("This is a test tweet!");
		tweet2.setDate(new Date());
		
		Tweet[] tweets = new Tweet[2];
		tweets[0] = tweet1;
		tweets[1] = tweet2;

		// Generate JSON
		response.getWriter().println(gson.toJson(tweets));

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
		response.getWriter().println(gson.toJson(user));

		Utils.setUserId(response, user.getUserId());
	}
}
