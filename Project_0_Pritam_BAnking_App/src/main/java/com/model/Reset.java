package com.model;

public class Reset {
	
	private String name;
	private long phoneNumber;
	private long aadharCard;
	
	
	public Reset() {
		// TODO Auto-generated constructor stub
		
	}
	
	
	public Reset(String name, long phoneNumber, long aadharCard) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.aadharCard = aadharCard;
	}


	public long getAadharCard() {
		return aadharCard;
	}


	public void setAadharCard(long aadharCard) {
		this.aadharCard = aadharCard;
	}


	public Reset(String name,long phoneNumber) {
		
		this.name = name;
		this.phoneNumber = phoneNumber;
		
	}
	
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public long getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	
	public int cheakPhoneNumber() {
		long num = getPhoneNumber();
		int count = 0;
	      while(num!=0){
	         num = num/10;
	         count++;
	      }
	      return count;
	}
	public int cheakAadharNumber() {
		long num = aadharCard;
		int count = 0;
	      while(num!=0){
	         num = num/10;
	         count++;
	      }
	      return count;
	}
	
	public boolean changePassword(String s) {
		System.out.println("password changed");
		return true;
	}

}
