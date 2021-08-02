package com.model;

public class Register {
	
	private String name;
	private String fatherName;
	private int age;
	private long phoneNumber;
	private long aadharCard;
	private String address;
	private String new_Password;
	private String re_Password;
	private int accountId;
	private String status;
	private int balance;
	
	
	public Register() {
		// TODO Auto-generated constructor stub
//		accountNumber = 
		
	}
	
	
	
	
	public Register(String name, String fatherName, int age, long phoneNumber, long aadharCard, String address, String new_Password, String re_Password) {
		super();
		this.name = name;
		this.fatherName = fatherName;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.aadharCard = aadharCard;
		this.address = address;
		this.new_Password = new_Password;
		this.re_Password = re_Password;
	}




	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNew_Password() {
		return new_Password;
	}
	public void setNew_Password(String new_Password) {
		this.new_Password = new_Password;
	}
	public String getRe_Password() {
		return re_Password;
	}
	public void setRe_Password(String re_Password) {
		this.re_Password = re_Password;
	}
	
	public long getAadharCard() {
		return aadharCard;
	}
	public void setAadharCard(long aadharCard) {
		this.aadharCard = aadharCard;
	}
	
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	




	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	public boolean cheakPassword() {
		
		if(new_Password.equals(re_Password)) {
			return true;
		}
		else {
			return false;
		}
		
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
	
	public int cheakPhoneNumber() {
		long num = phoneNumber;
		int count = 0;
	      while(num!=0){
	         num = num/10;
	         count++;
	      }
	      return count;
	}
	
	public boolean saveData()
	{
		System.out.println("data save");
		return true;
	}
	public String printSingleObject() {
		
		return "AccountNumber:-" +accountId+"\nName=" + name + "\nFather Name=" + fatherName + "\nAge="
				+ age + "\nPhone Number=" + phoneNumber + "\nAadhar NUmber=" + aadharCard+"\nAddress:- " +address+"\nPassword:-" +new_Password;
		
	}
	@Override
	public String toString() {
		return "\n[AccountNumber:-" +accountId+", Name=" + name + ", Father Name=" + fatherName + ", Age="
				+ age + ", Phone Number=" + phoneNumber + ", Aadhar NUmber=" + aadharCard +", Address:- " +address+", Status= "+status+", Balance = "+balance+"]";
	}
	

}
