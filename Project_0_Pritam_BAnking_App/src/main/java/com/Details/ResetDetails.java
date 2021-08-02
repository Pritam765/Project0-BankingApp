package com.Details;

import java.util.Scanner;

import com.dao.DataStore;
import com.model.Reset;

public class ResetDetails {
	
	public static Reset resetDetails() throws Exception {
		DataStore data = new DataStore();
		Scanner sc = new Scanner(System.in);
		
		Reset reset = new Reset();
		String name = null, change_Password = null;
		long phoneNumber = 0;
		
		System.out.println("Entre Name:-");
		name = sc.nextLine();
		reset.setName(name);
		
		System.out.println("Entre Your Phone Number:-");
		phoneNumber = sc.nextLong();
		reset.setPhoneNumber(phoneNumber);
		
		if(reset.cheakPhoneNumber()<10) {
			throw new Exception("Phone number is not valid");
		}

		
		
		System.out.println("Entre Your Aadhar Number:-");
		long aadharNumber = sc.nextLong();
		reset.setAadharCard(aadharNumber);
		
		if(reset.cheakAadharNumber()!= 12) {
			throw new Exception("AAdhar number is not valid"+ reset.cheakAadharNumber());
		}

		
		
		
		
		return reset;
	}

}
