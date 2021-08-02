package com.Details;

import java.util.Scanner;

import com.model.Register;

public class RagisterDetails {

	public static Register registerDetails() throws Exception {
		Scanner sc = new Scanner(System.in);

		Register ragister = new Register();
		String name = null;
		String fatherName = null;
		int age = 0;
		long phoneNumber = 0;
		long aadharNumber = 0;
		String address = null;
		String new_Password = null;
		String re_Password = null;

		System.out.println("Entre Name:- ");

		name = sc.nextLine();
		ragister.setName(name);

		System.out.println("Entre Your Father Name:-");
		fatherName = sc.nextLine();
		ragister.setFatherName(fatherName);

		System.out.println("Entre Your Address:-");
		address = sc.nextLine();
		ragister.setAddress(address);

		System.out.println("Entre Your Age:-");
		age = sc.nextInt();
		ragister.setAge(age);

		System.out.println("Entre Your Phone Number:-");
		phoneNumber = sc.nextLong();
		ragister.setPhoneNumber(phoneNumber);

		if (ragister.cheakPhoneNumber() < 10) {
			throw new Exception("Phone number is not valid");
		}

		System.out.println("Entre Your Aadhar Number:-");
		aadharNumber = sc.nextLong();
		ragister.setAadharCard(aadharNumber);

		if (ragister.cheakAadharNumber() != 12) {
			throw new Exception(ragister.cheakAadharNumber() + " Digits AAdhar number is not valid ");
		}

		System.out.println("Entre Unique Password:-");
		sc.nextLine();
		new_Password = sc.nextLine();
		ragister.setNew_Password(new_Password);

		System.out.println("Entre Unique Password again:-");
		System.out.println("Note:- password should be match with New password");
		re_Password = sc.nextLine();
		ragister.setRe_Password(re_Password);

		if (ragister.cheakPassword()) {
			System.out.println("Cheaking Password.....");
			Thread.sleep(2000);
			return ragister;

		} else {

			throw new Exception("Password must be same");
		}

	}

}
