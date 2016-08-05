package com.ironyard.doorway.handlers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Utils {

	public static String getUserId(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("userId")) {
					return (cookie.getValue());
				}
			}
		}
		return ("ERROR");
	}
	
	public static void setUserId(HttpServletResponse response, String userId) {
		Cookie cookie = new Cookie("userId", userId);
		cookie.setPath("/");
		cookie.setSecure(true);
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
		System.out.println("Wrote cookie userId=" + userId);	
	}
}
