package com.ironyard.doorway.handlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ironyard.doorway.TwitterClone;
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
		} else if(target.contains("sendtweet")) {
			sendTweet(target, request, response);
		} else {
			System.err.println("UNHANDLED CALL!");
			return;
		}
		baseRequest.setHandled(true);
	}

	private void getFollowers(String target, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String userId = Utils.getUserId(request);
		if(userId.length() == 0) {
			userId = (TwitterClone.user != null) ? TwitterClone.user.getUserId() : "";
		}
		
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
		

		// Generate JSON
		response.getWriter().println(gson.toJson(TwitterClone.followersList.toArray(new User[0])));

		Utils.setUserId(request, response, userId);
	}

	private void getTweets(String target, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String userId = Utils.getUserId(request);
		if(userId.length() == 0) {
			userId = (TwitterClone.user != null) ? TwitterClone.user.getUserId() : "";
		}
		// Get User ID
		String[] fields = target.split("/");
		String fieldUserId = fields[3];
		String fieldScope = fields[4];
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);

		// TODO get User Info from tweetsDAO.
		
		List<Tweet> rtnTweets = new ArrayList<Tweet>();
		for(Tweet tweet : TwitterClone.tweetsList) {
			if(fieldScope.equalsIgnoreCase("personal")) {
				if(tweet.getFromUserId().equals(fieldUserId)) {
					rtnTweets.add(tweet);
				}
			}
			else { // assume ALL
				rtnTweets.add(tweet);
			}
		}
		// Generate JSON
		response.getWriter().println(gson.toJson(rtnTweets.toArray(new Tweet[0])));
		
		Utils.setUserId(request, response, userId);
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

		User user = TwitterClone.user;
		if(user == null) {
			return;
		}
		else {
			// Generate JSON
			response.getWriter().println(gson.toJson(user));
		}	

		Utils.setUserId(request, response, user.getUserId());
	}
	
	private void sendTweet(String target, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// Get User ID
		String[] fields = target.split("/");
		String userId = fields[3];
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);

		// TODO get User Info from tweetsDAO.

		Tweet tweet = new Tweet();
		tweet.setSeq(TwitterClone.tweetsList.size());
		tweet.setFromUserId(userId);
		tweet.setToUserId("");
		tweet.setDate(new Date());
		tweet.setMsg(request.getParameter("tweetmsg"));
		
		System.out.println("Sending tweet " + tweet.getMsg());
		
		TwitterClone.tweetsList.add(tweet);

		Utils.setUserId(request, response, userId);		
	}
	
}
