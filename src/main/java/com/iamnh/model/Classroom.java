package com.iamnh.model;

import java.util.HashSet;
import java.util.Set;

public class Classroom {
	private int id ;
	private String name;
	private String grade;
	private Set<Student> stus;
	
	public void addStu(Student stu){
		if(stus==null){
			stus = new HashSet<Student>();
		}
		stus.add(stu);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Set<Student> getStus() {
		return stus;
	}
	public void setStus(Set<Student> stus) {
		this.stus = stus;
	}
	
}
