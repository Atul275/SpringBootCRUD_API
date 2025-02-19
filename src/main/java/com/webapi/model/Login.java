package com.webapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="login")
public class Login {
	@Id
	@GeneratedValue
	private int id;
	@Column(nullable = false, unique = true)
	private String username;
	@Column(nullable = false)
	private String password;
	private String email;
	private String ans;
	private String sques;
	
	public Login() {
		super();
	}

	public Login(int id, String username, String password, String email, String country, String ans, String sques) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.ans = ans;
		this.sques = sques;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAns() {
		return ans;
	}

	public void setAns(String ans) {
		this.ans = ans;
	}

	public String getSques() {
		return sques;
	}

	public void setSques(String sques) {
		this.sques = sques;
	}

	@Override
	public String toString() {
		return "Login [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", ans=" + ans + ", sques=" + sques + "]";
	}
}
