package com.menu;

import java.util.Scanner;

import com.App;
import com.Details.LoginDetails;
import com.Details.RagisterDetails;
import com.Details.ResetDetails;
import com.dao.CusEmpFunctionality;
import com.dao.CustomersFunctions;
import com.dao.DataStore;
import com.dao.EmployeeFunctions;
import com.model.Login;
import com.model.Register;
import com.model.Reset;

public class MainMenu {
	Scanner sc = new Scanner(System.in);
	static long x = 1000;
	DataStore data = new DataStore();
	CusEmpFunctionality func = new CusEmpFunctionality();
	EmployeeFunctions emp = new EmployeeFunctions();
	CustomersFunctions cust = new CustomersFunctions();
	
	
	public static boolean checkYesNo() {
		int b = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Entre 1 for Yes and 0 for NO");
		b = sc.nextInt();
		if (b != 1) {
			return false;
		}
		else
			return true;
	}
	
	public String cheakType() throws Exception {
		System.out.println("Enter the Type of Account");
		System.out.println("Select");
		System.out.println("E for Employee account");
		System.out.println("C for Customer account");
		char c = sc.next().charAt(0); 
		char accType = Character.toUpperCase(c);
		if(accType=='E') {
			return "employees";
		}else if(accType == 'C') {
			return "customers";
		}
		else {
			throw new Exception("This is  invalid");
		}
	}
	
	public static void cheakExit() {
		int b = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Entre 0 for exit or other key for Continue");
		b = sc.nextInt();
		if (b == 0) {
			System.out.println("Thanks For Using our app");
			System.out.println("Visit Again");
			System.exit(0);
		}
	}
	


	public void firstMethod(int a, String type) throws Exception {


		switch (a) {
		case 1:
			System.out.println("For Registration Enter the Specific Details:-");
			Register ragister = RagisterDetails.registerDetails();
			System.out.println(ragister.getName());
			int accountId = func.addDataIntoTable(type, ragister);
			ragister.setAccountId(accountId);
			if(accountId==0) {
				System.out.println("Data  was Not saved");
			}
			else {
				if(type=="employees") {
					System.out.println("Your Employee Registration Successfull");
					System.out.println("#######################################");
					System.out.println("Generating an Employee Id.....Please wait...");
					Thread.sleep(3000);
					
				}	
				else {
					
				System.out.println("Your Customer account Registration Succesfull");
				System.out.println("######################################");
				System.out.println("Generating an account number.....please wait...");
				Thread.sleep(3000);
				}
				
				System.out.println("Welcome to our banking app");
				System.out.println("Your Data saved with your account Number is:- "+ ragister.getAccountId()+" and Password is:- "+ragister.getNew_Password());
				System.out.println("Note:- Please save your account Number for login");
			}
			break;
		case 2:

			Login log = LoginDetails.loginDetails();
			Register reg = func.cheakIntoTable(type, log.getAccountId(), log.getPassword());
			System.out.println("Checking id and Password...");
			Thread.sleep(3000);
			if(type=="employees")
				
				EmployeeMenu.employeeMenu(type, reg);
			else
				CustomerMenu.customerMenu(type,reg);

			break;
		case 3:

			Reset reset = ResetDetails.resetDetails();
			Login login = data.getResetDetails(type, reset.getName(), reset.getPhoneNumber(), reset.getAadharCard());
			System.out.println("This is Your Details for login");
			System.out.println("Account Number:- "+ login.getAccountId());
			System.out.println("Password is:- "+ login.getPassword());
			if(login.getAccountId() != 0)
				ResetMenu.resetMenu(type,login.getAccountId(), reset);
			else
				System.out.println("Details are incorrect");
			
			break;
		case 8:
			
			String type2 = cheakType();
			Menu(type2);
			break;
//		case 5:
//			
////			user.ResetDetails();
//			239340028839
//			
//			break;

		default:
			cheakExit();
			
			break;
		}

	}

	public static void Menu(String type) {
		Scanner sc1 = new Scanner(System.in);
		MainMenu user = new MainMenu();
		int a = 1;
		System.out.println("-------------------------------------------");
		System.out.println("Welcome to 'Pritam Banking System (India)'");
		System.out.println("-------------------------------------------");
		System.out.println("This is The Menu for "+ type);

		while (true) {			

			System.out.println("Press 1, For Creating an account .");
			System.out.println("Press 2, For Login.");
			System.out.println("Press 3, For Reset Password.");
			System.out.println("Press 8, Change account type.");
			System.out.println("Press 9  key to exit");
			System.out.println("Note:- If you don't have an any account please make an account else login.");
			try {
				a = sc1.nextInt();
			}
			catch(Exception e) {
				System.out.println(e);
				
			}
			
			
				try {
					user.firstMethod(a, type);
					System.out.println("#########################################");
				} catch (Exception e) {
					System.out.println(e);
//					user.cheakExit();
					System.out.println("Enter the details again");
				}
			
			

			
			System.out.println("###########################################################");

		}

	}

}
