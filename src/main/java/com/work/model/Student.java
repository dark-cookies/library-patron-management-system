package com.work.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 
 * @author Johns Sebastian
 * @version 1.0
 */
public class Student {

	public String name = null;
	public int rollNo = 0;
	public String course = null;
	public String email = null;
	public int bookCount = 0;
	public int overdueDays = 0;
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getRollNumber() {
		return rollNo;
	}
	public void setRollNumber(int rollNo) {
		this.rollNo = rollNo;
	}

	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getBookCount() {
		return bookCount;
	}
	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}
	
	public int getOverdueDays() {
		return overdueDays;
	}
	public void setOverdueDays(int overdueDays) {
		this.overdueDays = overdueDays;
	}

}