package com.ironyard.doorway;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;

import com.ironyard.doorway.handlers.MyResourceHandler;
import com.ironyard.doorway.handlers.RestAPIHandler;
import com.ironyard.doorway.handlers.ServletHandler;

public class TwitterClone {

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
