package com.DXC.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class User {
	
	@Id
	private String username;
	private String name;
	private String password;
    private String role;
    
    
	public User(String username, String password, String role) {
		// TODO Auto-generated constructor stub
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", name=" + name + ", role=" + role + "]";
	}
	
}
