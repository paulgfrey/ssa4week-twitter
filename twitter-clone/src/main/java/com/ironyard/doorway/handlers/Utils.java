package com.ironyard.doorway.handlers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Utils {

	public static String getUserId(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("userid")) {
					return (cookie.getValue());
				}
			}
		}
		return ("");
	}
	
	public static void setUserId(HttpServletResponse response, String userId) {
		Cookie cookie = new Cookie("userid", userId);
		cookie.setHttpOnly(false); // THIS SHOULD BE FALSE TO ACCESS THE COOKIE IN JAVASCRIPT!!
		cookie.setMaxAge(7*24*60*60);
		response.addCookie(cookie);
		System.out.println("Wrote cookie userid=" + userId);	
	}
}
