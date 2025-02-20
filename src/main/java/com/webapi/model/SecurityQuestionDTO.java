package com.webapi.model;

public class SecurityQuestionDTO {
	private String username;
	private String sques;
	private String ans;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSques() {
		return sques;
	}

	public void setSques(String sques) {
		this.sques = sques;
	}

	public String getAns() {
		return ans;
	}

	public void setAns(String ans) {
		this.ans = ans;
	}
}
