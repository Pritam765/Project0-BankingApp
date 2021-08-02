package com.menu;

import java.util.Date;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.App;
import com.Details.RagisterDetails;
import com.dao.CusEmpFunctionality;
import com.dao.CustomersFunctions;
import com.dao.DataStore;
import com.dao.EmployeeFunctions;
import com.model.Register;

public class CustomerMenu {
	static DataStore data = new DataStore();
	static CusEmpFunctionality func = new CusEmpFunctionality();
	static EmployeeFunctions emp = new EmployeeFunctions();
	static CustomersFunctions cust = new CustomersFunctions();
	
	static Logger logger = Logger.getLogger(CustomerMenu.class);

	public static void customerMenu(String type,Register register) {
		Scanner sc = new Scanner(System.in);
		System.out.println("-----------------------------------------");
		System.out.println("Customer Menu");
		
		logger.info("Customer "+register.getName()+" With Id- "+register.getAccountId()+" Enter in customer Account "+new Date());
		
		if(data.checkStatus(register.getAccountId())) {
			try {
				logger.info("Customer "+register.getName()+" With Id "+register.getAccountId()+" is Approved Account "+new Date());
				System.out.println("Welcome "+ register.getName()+" to our Customer app");
	//			System.out.println(register.printSingleObject());
				int a = 0;
				
				while(true) {
					
				System.out.println();
				System.out.println("Press 1 for Upgrade your details");
				System.out.println("Press 2 for Show Details");
				System.out.println("Press 3 for View Balance");
				System.out.println("Press 4 for Doing Banking Operations");
				System.out.println("Press 7 for delete your account");
				System.out.println("Press 8 for LogOut");
				System.out.println("Press 9 for Exit from app");
				a = sc.nextInt();
				
				
				switch(a) {
					case 2:
					register = data.getAllDetails(type, register.getAccountId());
					System.out.println(" ##### Your details Shown below  ######");
					System.out.println();
					System.out.println(register.printSingleObject());
					System.out.println();
					break;
				case 1:
					System.out.println("Entre Details for Upgradation process");
					Register ragisterForUpdate = RagisterDetails.registerDetails();
					boolean bool = data.updateDataIntoTable(type,register.getAccountId(), ragisterForUpdate);
					if(bool) {
						System.out.println("Your Data is successfully Updated");
					}
					else {
						System.out.println("Your Data is not Updated ");
						System.out.println("Try Again after some time ####### ");}
					break;
				case 3:
					int bal = data.viewBalance(register.getAccountId());
					logger.info("Customer "+register.getName()+" With Id "+register.getAccountId()+" is viewed his Account Balance at "+new Date());
					System.out.println("Your Account Balance is:-" + bal);
					System.out.println();
					break;
				case 4:
					CustomerMenu.bankingOperation(register.getAccountId(), type, register);					
					break;
				case 7:
					boolean let = MainMenu.checkYesNo();
					if(let) {
						boolean b = data.deleteDataIntoTable(type, register.getAccountId());
						if(b) {
							System.out.println("Account was Deleted Successfully");
							MainMenu.Menu(type);
						}else {
							System.out.println("Account Was Not deleted.");
							System.out.println("Try Again After Some Time #########");
						}
					}
					break;
				case 8:

					boolean c = MainMenu.checkYesNo();
					if(c) {
						logger.info("Customer "+register.getName()+" With Id "+register.getAccountId()+" is Logged Out Scuccesfuly Account "+new Date());
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
		else {
			logger.info("Customer "+register.getName()+" With Id "+register.getAccountId()+" is Rejected Account "+new Date());
			System.out.println("Your account is going to be rejected");
			System.out.println("!!!!!!Please contact with our employee!!!!!!!");
		}
		
	}

	public static void bankingOperation(int id, String type, Register register) {
		Scanner sc = new Scanner(System.in);
		
		
		while(true) {
			System.out.println();
			System.out.println(" For Banking Operations");
			System.out.println("Press 1 for Transfering amount");
			System.out.println("Press 2 for adding amount");
			System.out.println("Press 3 for withdrawing Amount");
			System.out.println("Press 9 for Go to customer menu");
			int x = sc.nextInt(), a = 1, amount = 0;
			
			switch(x) {
			case 2:
				System.out.println();
				System.out.println("Enter the amount to add in your Account");
				amount = sc.nextInt();
				boolean bool = cust.addAmount(id, amount);
				if(bool) {
					logger.info("Customer "+register.getName()+" With Id "+register.getAccountId()+" is Added Amount "+amount+" in his bank account at "+new Date());
					System.out.println("Your amount is added Successfully"+data.viewBalance(id));
				}
				else {
					System.out.println("Your amount is not added");
					System.out.println("Try Again after some time.... ");}
				break;
			case 3:
				System.out.println();
				System.out.println("Enter the amount that you want to withdraw");
				amount = sc.nextInt();
				boolean bool2 = data.withdrawAmount(id, amount);
				if(bool2) {
					logger.info("Customer "+register.getName()+"With Id "+register.getAccountId()+" is Withdraw Amount "+amount+" in his bank account at "+new Date());
					System.out.println("Your amount is withdraw Successfully");
					System.out.println("your Account Balance is"+ data.viewBalance(id));
				}
				else {
				System.out.println("Your amount is not withdrawan");
				System.out.println("Try Again after some time.... ");}
				break;
			case 1:
				System.out.println();
				System.out.println("Your Id :-"+ id);
				System.out.println("Enter the account id of that person whom you want to sent money:-");
				int creditedAccount = sc.nextInt();
				boolean check = data.isExists(type, creditedAccount);
				if(check){
					System.out.println("Enter the amount ");
					amount = sc.nextInt();
					boolean bool3 = data.transferAmount(id, creditedAccount, amount);
					if(bool3) {
						logger.info("Customer "+register.getName()+"With Id "+register.getAccountId()+" is transfer Amount"+amount+" to  "+creditedAccount+" at "+new Date());
						System.out.println("Your amount is Transfered Successfully");
						System.out.println("Your remaining Balance is "+data.viewBalance(id));
					}
					else {
					System.out.println("Your amount is not Transfered");
					System.out.println("Try Again after some time ");}
				}else {
					
					System.out.println("Credit Account Doesn't Exist");
				}
				break;
			case 9:
				a = 0;
				break;
			}
			
			if(a==0) {
				break;
			}
			
		}
		
	}
	
}
