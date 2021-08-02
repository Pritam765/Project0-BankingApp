package com.menu;

import java.util.Scanner;

import com.dao.CusEmpFunctionality;

import com.dao.DataStore;
import com.model.Reset;

public class ResetMenu {
	static CusEmpFunctionality func = new CusEmpFunctionality();
	
	
	public static boolean cheakPassword(String new_Password, String re_Password) {
		
		if(new_Password.equals(re_Password)) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public static void resetMenu(String type, int id,Reset reset) throws Exception {
		Scanner sc = new Scanner(System.in);
		DataStore data = new DataStore();
		boolean check = false;
		
		
		
		System.out.println("If you want to change your password:--");
		System.out.println("Press 1 for changing passowrd");
		System.out.println("Press any other key for go to menu");
		int x = sc.nextInt();
		
		switch(x) {
		case 1:
			System.out.println("Enter Unique Password:-");
			sc.nextLine();
			String new_Password = sc.nextLine();

			System.out.println("Enter Unique Password again:-");
			System.out.println("Note:- password must be match with New password");
			String re_Password = sc.nextLine();
			System.out.println("Checking Password.....");
			Thread.sleep(2000);
			if (cheakPassword(new_Password, re_Password)) {
				check = func.reset_Password(type, id, new_Password);
								
			} else {
							
				throw new Exception("Password must be same");
			}
			
			
			if(check) {
				System.out.println("Your password is changed Successfully");
				MainMenu.Menu(type);
				
			}
			else
				System.out.println("Your password is not changed Successfully");
				System.out.println("Try again after some time...");
				
			break;
		default:
			MainMenu.Menu(type);
			break;
		}
		
		
	}

}
