package com.work.model;

import java.util.*;
import com.work.model.Student;

/**
 * 
 * @author Johns Sebastian
 * @version 1.0
 */
public class Book {

    public Book() {
        // TODO Auto-generated constructor stub
    }

    public int bookId = 0;
    public String bookName = null;
    public int ISBN = 0;
    public String author = null;
    public String publisher = null;
    public Date checkout = null;
    public Date checkin = null;
    public Date duedate = null;
    public Student student = new Student();

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookID(int bookId) {
        this.bookId = bookId;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getCheckOutDate() {
        return checkout;
    }

    public void setCheckOutDate(Date date) {
        this.checkout = date;
    }

    public Date getCheckInDate() {
        return checkin;
    }

    public void setCheckInDate(Date date) {
        this.checkin = date;
    }

    public Date getDueDate() {
        return duedate;
    }

    public void setDueDate(Date date) {
        this.duedate = date;
    }

    public Student getStudent() {
        return student;
    }
}
