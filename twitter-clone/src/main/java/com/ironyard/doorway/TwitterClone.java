package com.ironyard.doorway;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;

import com.ironyard.doorway.domain.Tweet;
import com.ironyard.doorway.domain.User;
import com.ironyard.doorway.handlers.MyResourceHandler;
import com.ironyard.doorway.handlers.RestAPIHandler;
import com.ironyard.doorway.handlers.ServletHandler;

public class TwitterClone {
	public static List<Tweet> tweetsList = new ArrayList<Tweet>();
	public static User user = null;
	public static List<User> followersList = new ArrayList<User>();
	
	static {
		Tweet tweet1 = new Tweet();
		tweet1.setSeq(0);
		tweet1.setToUserId("User");
		tweet1.setFromUserId("Follower");
		tweet1.setMsg("This is a test tweet!");
		tweet1.setDate(new Date());
		
		Tweet tweet2 = new Tweet();
		tweet2.setSeq(1);
		tweet2.setToUserId("User");
		tweet2.setFromUserId("Follower");
		tweet2.setMsg("This is a test tweet!");
		tweet2.setDate(new Date());
		
		tweetsList.add(tweet1);
		tweetsList.add(tweet2);
	};
	
	public TwitterClone(int port) throws Exception {
        Server server = new Server(port);
        
        HandlerList handlers = new HandlerList();
        MyResourceHandler resourceHandler = new MyResourceHandler("./src/main/webapp");
        handlers.setHandlers(new Handler[]
        { resourceHandler, new RestAPIHandler(), 
        		new ServletHandler(), new DefaultHandler() });
        
        server.setHandler(handlers);
        server.start();
        server.join();		
	}

	public static void main(String[] args) {
		int port = 8080;
		if(args.length > 0) {
			port = Integer.parseInt(args[0]);
		}
		try {
			TwitterClone twitterClone = new TwitterClone(port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
