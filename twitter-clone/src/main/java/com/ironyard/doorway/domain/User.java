package com.ironyard.doorway.domain;

public class User {
	private String userId;
	private String password;
	private String firstName;
	private String lastName;	

	public User() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{ ");
		sb.append("\"userId\": \"" + userId + "\", ");
		sb.append("\"password\": \"" + password + "\", ");
		sb.append("\"lastName\": \"" + lastName + "\", ");
		sb.append("\"firstName\": \"" + firstName + "\"");
		sb.append(" }");
		
		return(sb.toString());
	}
}
