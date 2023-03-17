package com.user;

import java.sql.Date;

public class Messages {
	private String message,time,contact;
	private Date date;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}	
//	public Messages(String message,String contact,Date date) {
//		this.message=message;
//		this.contact=contact;
//		this.time=date;
//	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
//	public String getContact() {
//		return contact;
//	}
//	public void setContact(String contact) {
//		this.contact = contact;
//	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
}
