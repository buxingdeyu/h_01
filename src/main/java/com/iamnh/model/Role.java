package com.iamnh.model;

import java.util.HashSet;
import java.util.Set;

public class Role {
	private int id;
	
	private String name;
	
	private Set<Admin> admins;

	public  void add(Admin admin){
		if(admins == null){
			admins = new HashSet<Admin>();
		}
		admins.add(admin);
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

	public Set<Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(Set<Admin> admins) {
		this.admins = admins;
	}
	
	
}
