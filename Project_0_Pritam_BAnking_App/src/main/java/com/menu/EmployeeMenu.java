package com.menu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

import com.Details.RagisterDetails;
import com.dao.CusEmpFunctionality;
import com.dao.CustomersFunctions;
import com.dao.DataStore;
import com.dao.EmployeeFunctions;
import com.model.Login;
import com.model.Register;

public class EmployeeMenu {
	static DataStore data  = new DataStore();
	static CusEmpFunctionality func = new CusEmpFunctionality();
	static EmployeeFunctions emp = new EmployeeFunctions();
	static CustomersFunctions cust = new CustomersFunctions();
	static Register register = null;
	
	public static boolean doApprovedRejectedbyid(String type) {
		Scanner sc = new Scanner(System.in);
		System.out.println("press 1 for Approved \nPress 2 for rejected\nany other key to go beack to employee menu");
		int a = sc.nextInt();
		System.out.println("Entre the Id of Customer");
		int id = sc.nextInt();
		boolean x = data.isExists("customers", id);
		
		boolean bool = false;
		if(x) {		
			if(a==1) {
				
				bool = emp.approveCustomerById(id);
				return bool;
				
			}
			else if(a==2) {
				
				bool = emp.rejectCustomerById(id);
				return bool;
			}
				
		}
		else {
			System.out.println("Informaion Incorrect....");
			System.out.println("Try Again....");
		}
		return bool;
		
			
	}
	public static boolean doApprovedRejected(String type) {
		Scanner sc = new Scanner(System.in);
		System.out.println("press 1 for Approved \nPress 2 for rejected\nany other key to go beack to employee menu");
		int a = sc.nextInt();
		
//		boolean x = data.isExists("customers", id);
		
		boolean bool = false;
				
			if(a==1) {
				
				bool = data.approveAllCustomerId();
				if(bool) {
					System.out.println("All Customers id get Approved successfully");
				}
				else {
					System.out.println("All Customers id get Approved Failed");
					System.out.println("Try Again...");
				}
				
			}
			else if(a==2) {
				bool = data.rejectAllCustomerId();
				if(bool) {
					System.out.println("All Customers id get Rejected successfully");
				}
				else {
					System.out.println("All Customers id get Approved Failed");
					System.out.println("Try Again...");
				}
			}
			
			return bool;
		}

		
	
	public static void employeeMenu(String type,Register reg) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("##########Employee Menu#########");
		int a = 0;
		try {
			System.out.println("!!!!!!!---- Welcome "+ reg.getName()+ " with id:- "+reg.getAccountId()+" to our employee app ----------!!!!!!!!");
//			System.out.println(register.toString());
			while(true) {
			System.out.println();
			System.out.println("Press 1 for show your details");
			System.out.println("Press 2 If you want to upgrade your details");
			System.out.println("Press 3 for Approved and Rejected all customer's ids");
			System.out.println("Press 4 for get the info of Customers Activity");
			System.out.println("Press 5 for Approved and Rejected customer using his id");
			System.out.println("Press 6 for Show All customer details");
			System.out.println("Press 7 for Delete your account");
			System.out.println("Press 8 for For Logout");
			System.out.println("Press 9 for Exit the app");
			
			try {
				a = sc.nextInt();
			}
			catch(Exception e) {
				System.out.println(e);
				
			}
			
			
			switch(a) {
			case 1:
				Register register = data.getAllDetails(type, reg.getAccountId());
				System.out.println("Your details Shown below......!!!");
				System.out.println();
				System.out.println(register.printSingleObject());
				System.out.println();
				break;
			case 2:
				System.out.println("Entre Details for Upgradation");
				System.out.println();
				Register ragisterForUpdate = RagisterDetails.registerDetails();
				boolean bool = data.updateDataIntoTable(type,reg.getAccountId(), ragisterForUpdate);
				if(bool) {
					System.out.println("Your Data is Updated");
				}
				else
					System.out.println("Your Data is not Update ");
					System.out.println("Try Again.... ");
				break;
			case 3:
				boolean bol = doApprovedRejected(type);
				if(bol) {
					System.out.println("Operation Perforemed Sucessfully");
				}else {
					System.out.println("Operation Failed.");
					System.out.println("Try Again...");
					
				}				
				break;
			case 4:
////				FileReader file = new FileReader("E:\\Revature\\eclipse-workspace\\Project_0_Banking_App\\bankapp.log");
					File file = new File("C:\\Users\\HP\\Downloads\\Project_0_Banking_App\\bankapp.log");
//				  
				  	BufferedReader br = new BufferedReader(new FileReader(file));
//				  
				 	 String st;
				  	while((st = br.readLine()) != null)
				    	System.out.println(st);

				break;
				
			case 5:
				boolean bl = doApprovedRejectedbyid(type);
				if(bl) {
					System.out.println("Operation Perforemed Sucessfully");
				}else {
					System.out.println("Operation Failed.");
					System.out.println("Try Again...");
					
				}				
				break;
			case 6:
				
				List<Register> list =  data.showCustomerDetails();
				System.out.println("##ALL Customers Details##");
				System.out.println("Loading data of cutomers.....");
				Thread.sleep(3000);
				System.out.println();
				System.out.println(list);
				
				break;
			case 7:
				boolean let = MainMenu.checkYesNo();
				if(let) {
					boolean b = data.deleteDataIntoTable(type, reg.getAccountId());
					if(b) {
						System.out.println("Account Delete Successfully");
						MainMenu.Menu(type);
					}else {
						System.out.println("Account Not deleted.");
						System.out.println("Try Again...");
					}
				}
				break;
			case 8:
				boolean c = MainMenu.checkYesNo();
				if(c) {
					MainMenu.Menu(type);
					System.out.println("Your Logged Out Successfully");
				}
				break;
			case 9:
				MainMenu.cheakExit();
				break;
			default:
				break;
			}
			
			
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			System.out.println(e);
		}
		
		
		
	}

}
