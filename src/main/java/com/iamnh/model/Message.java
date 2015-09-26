package com.iamnh.model;

import java.util.HashSet;
import java.util.Set;

public class Message {
	private int id ;
	private String title;
	private String content;
	
	public Message(){
		comments = new HashSet<Comment>();
	}
	private Set<Comment> comments;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	
	public void addComment(Comment comment){
		comments.add(comment);
	}
	
}
