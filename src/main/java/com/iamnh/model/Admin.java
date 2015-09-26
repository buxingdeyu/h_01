package com.iamnh.model;

import java.util.HashSet;
import java.util.Set;

public class Admin {
	private int id;
	private String name;
	private Set<Role> roles;
	
	public void add(Role role){
		if(null == roles){
			roles = new HashSet<Role>();
		}
		roles.add(role);
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
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
}
