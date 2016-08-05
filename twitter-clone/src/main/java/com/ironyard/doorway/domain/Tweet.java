package com.ironyard.doorway.domain;

import java.util.Date;

public class Tweet {
	private int seq;
	private String fromUserId;
	private String toUserId;
	private String msg;
	private Date date;

	public Tweet() {
		// TODO Auto-generated constructor stub
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{ ");
		sb.append("\"seq\": \"" + seq + "\", ");
		sb.append("\"fromUserId\": \"" + fromUserId + "\", ");
		sb.append("\"toUserId\": \"" + toUserId + "\", ");
		sb.append("\"msg\": \"" + msg + "\"");
		sb.append("\"date\": \"" + date + "\"");
		sb.append(" }");
		
		return(sb.toString());
	}
}
