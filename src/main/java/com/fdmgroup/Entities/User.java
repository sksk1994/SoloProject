package com.fdmgroup.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="SCU_USER")
public class User {

	@Id
	private String username;
	private String password;
	private int status;
	private String firstName;
	private String lastName;
	private String email;
	
	
	

	public User(String username, String password, String firstName, String lastName, String email) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	

	public User() {
		super();
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
		return "User [username=" + username + ", password=" + password + ", status=" + status + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
	

	

	


}
