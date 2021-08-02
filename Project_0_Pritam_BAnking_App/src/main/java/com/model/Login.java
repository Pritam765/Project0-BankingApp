package com.model;

public class Login {
	
	private int accountId;
	private String password;
	
	public Login() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Login( int accountId, String password) {
		super();
		this.accountId = accountId;
		this.password = password;
	}



	
	
	public int getAccountId() {
		return accountId;
	}



	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}



	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public boolean matchData() {
//		System.out.println("data matched");
		return true;
	}

}
