package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.InsufficientResourcesException;
import javax.naming.spi.DirStateFactory.Result;

import com.exception.InsufficientAmountException;
import com.jdbc.DBConnection;
import com.model.Login;
import com.model.Register;

public class DataStore implements DataStoreMethod {
	
	Connection conection = DBConnection.getDBConnection();
	
//	for employee
//	1.done
	private final String ADD_Register_Query_Employee = "insert into app.employees(newName, fatherName, newAge, phoneNumber, aadharCard, address, newPassword ) values(?,?,?,?,?,?,?);";
//	1.1 done
	private final String get_Id_Register_Query_Employee = "select accountid from app.employees where newName = ? and fatherName = ? and newAge = ? and phoneNumber = ? and aadharCard = ? and address = ? and newPassword = ?;";
//	2. done
	private final String cheak_Id_Query_Employee = "select * from app.employees where accountid = ? and newPassword = ?;";
//	3. done
	private final String UPDATE_Query_Employee = "update app.employees set newName = ?, fatherName = ?, newAge = ?, phoneNumber = ?, aadharCard = ?, address = ?, newPassword = ? where accountid = ?;";
//	4. done
	private final String DELETE_Query_Employee = "delete from app.employees where accountid = ?;";
//	5. done
	private final String Get_ALL_Details_Employee = "select * from app.employees where accountid = ?;";
	
	private final String Reset_Details_Employee = "update app.employees set newPassword = ? where accountid = ?;";
	
	private final String get_Reset_Details_Employee = "select accountid, newPassword from app.employees where newName = ? and phoneNumber = ? and aadharCard = ?;";
	
//	for customer
	
//	1.done
	private final String ADD_Register_Query_Customer = "insert into app.customers(newName, fatherName, newAge, phoneNumber, aadharCard, address, newPassword ) values(?,?,?,?,?,?,?);";
//	1.1 done
	private final String get_Id_Register_Query_Customer = "select accountid from app.customers where newName = ? and fatherName = ? and newAge = ? and phoneNumber = ? and aadharCard = ? and address = ? and newPassword = ?;";
//	1.2 only for customer///////////////////
	private final String ADD_account_Query = "insert into app.customeraccount values(?);";	
//	2. done
	private final String cheak_Id_Query_Customer = "select * from app.customers where accountid = ? and newPassword = ?;";
//	3. done
	private final String UPDATE_Query_Customer = "update app.customers set newName = ?, fatherName = ?, newAge = ?, phoneNumber = ?, aadharCard = ?, address = ?, newPassword = ? where accountid = ?;";
//	4. done
	private final String DELETE_Query_Customer = "delete from app.customers where accountid = ?;";
//	4.1/////////////////////
	private final String DELETE_Acc_Query = "delete from app.customeraccount where accountid = ?;";
//	5. done
	private final String Get_ALL_Details_Customer = "select * from app.customers where accountid = ?;";
	
	private final String Reset_Details_Customer = "update app.customers set newPassword = ? where accountid =  ?;";
	
	private final String get_Reset_Details_Customer = "select accountid, newPassword from app.customers where newName = ? and phoneNumber = ? and aadharCard = ?;";

	
	
	
//	employee function
//	6. done
	private final String set_Approve_All_Query = "update app.customers set status = 'Approved';";
//	7. done
	private final String set_reject_All_Query = "update app.customers set status = 'Rejected';";
//	8. done
	private final String set_Approve_Query_by_Id = "update app.customers set status = 'Approved' where accountid  = ?;";
//	9. done
	private final String set_Reject_Query_by_Id = "update app.customers set status = 'Rejected' where accountid  = ?;";
//	10. 
	private final String show_Customer_Details_Query = "select * from app.customers natural join app.customeraccount;";

	
//	customer's function on accounts
	
//	cheak Status
	
	private final String check_status_Query = "select status from app.customers c where accountid  = ?;";
	
//	11. done
	private final String view_Balance_Query = "select balance from app.customeraccount where accountId = ?";
//	12.done 
	private final String add_Balance_Query = "update app.customeraccount set balance = balance + ? where accountId = ?";
//	13.done 
	private final String Withdraw_Balance_Query = "update app.customeraccount set balance = balance - ? where accountId = ?";
//	14.done
	private final String transfer_Balance_Query = "update app.customeraccount set balance = balance - ? where accountId = ?; update app.customeraccount set balance = balance + ? where accountId = ?;";

	private final String FIND_ID_QUERY_Customer = "select * from app.customers where accountid = ?";
	private final String FIND_ID_QUERY_Employee = "select * from app.employees where accountid = ?";
	
	PreparedStatement statement;

	
//	1. add done
	public int addDataIntoTable(String type, Register register) {
		
		ResultSet result = null;
		int res = 0;
		int id = 0;
		int res2 = 0;		
		try {
			if(type.equals("employees")){
			statement = conection.prepareStatement(ADD_Register_Query_Employee);
			
			}else {	
			statement = conection.prepareStatement(ADD_Register_Query_Customer);
				
			}
			

			statement.setString(1, register.getName());
			statement.setString(2, register.getFatherName());
			statement.setInt(3, register.getAge());
			statement.setLong(4, register.getPhoneNumber());
			statement.setLong(5, register.getAadharCard());
			statement.setString(6, register.getAddress());
			statement.setString(7, register.getNew_Password());
			
			res = statement.executeUpdate();
			statement.close();
			
			if(res != 0) {
				
				if(type.equals("employees")){
					statement = conection.prepareStatement(get_Id_Register_Query_Employee);
					}else {
						statement = conection.prepareStatement(get_Id_Register_Query_Customer);
						
					}
				
				statement.setString(1, register.getName());
				statement.setString(2, register.getFatherName());
				statement.setInt(3, register.getAge());
				statement.setLong(4, register.getPhoneNumber());
				statement.setLong(5, register.getAadharCard());
				statement.setString(6, register.getAddress());
				statement.setString(7, register.getNew_Password());
				
				
				result = statement.executeQuery();
				
				result.next();
				id =  result.getInt(1);
				
			}
			
			if(type.equals("customers")) {
				
				statement = conection.prepareStatement(ADD_account_Query);
				statement.setInt(1, id);
				res2 = statement.executeUpdate();	
			}
			
			return id;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Element is already in the database");
		}
		
		
		return 0;
	}

//	2. cheak done
	public Register cheakIntoTable(String type, int id, String password) {
		Register register = new Register();
		ResultSet res = null;
		
		try {
			
			if(type.equals("employees")){
				statement = conection.prepareStatement(cheak_Id_Query_Employee);
			}else {
				statement = conection.prepareStatement(cheak_Id_Query_Customer);
					
			}
			
			statement.setInt(1, id);
			statement.setString(2, password);
			res = statement.executeQuery();
			res.next();
			register.setAccountId(res.getInt(1));
			register.setName(res.getString(2));
			register.setFatherName(res.getString(3));
			register.setAge(res.getInt(4));
			register.setPhoneNumber(res.getLong(5));
			register.setAadharCard(res.getLong(6));
			register.setAddress(res.getString(7));
			register.setNew_Password(res.getString(8));
			
			
			return register;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		
		return null;
	}
	
//	3. update done
	public boolean updateDataIntoTable(String type,int id, Register register) {
		
		int res = 0;
		try {
			if(type.equals("employees")){
				statement = conection.prepareStatement(UPDATE_Query_Employee);
			}else {
				statement = conection.prepareStatement(UPDATE_Query_Customer);
					
			}
		statement.setString(1, register.getName());
		statement.setString(2, register.getFatherName());
		statement.setInt(3, register.getAge());
		statement.setLong(4, register.getPhoneNumber());
		statement.setLong(5, register.getAadharCard());
		statement.setString(6, register.getAddress());
		statement.setString(7, register.getNew_Password());
		statement.setInt(8, id);
		
		res = statement.executeUpdate();
		if(res==0) {
			return false;
		}else {
			return true;
		}
				
		
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		
		
		return false;
	}
	
	
	public boolean reset_Password(String type, int id, String passowrd) {
		
		int res = 0;
		
		try {
			if(type.equals("employees")){
				statement = conection.prepareStatement(Reset_Details_Employee);
			}else {
				statement = conection.prepareStatement(Reset_Details_Customer);	
			}
			statement.setString(1, passowrd);
			statement.setInt(2, id);
			
			res = statement.executeUpdate();
						
			
			if(res==0) {
				return false;
			}else {
				return true;
			}
					
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		
		
		return false;
		
		
	}
	
	public Login getResetDetails(String type, String name, long phoneNumber, long aadharNumber) {
		Login log = new Login();
		ResultSet res = null;
		try {
			if(type.equals("employees")){
				
				statement = conection.prepareStatement(get_Reset_Details_Employee);
				
			}else {
				statement = conection.prepareStatement(get_Reset_Details_Customer);
					
			}	
			
			statement.setString(1, name);
			statement.setLong(2, phoneNumber);
			statement.setLong(3, aadharNumber);
			
			res = statement.executeQuery();
			res.next();
			log.setAccountId(res.getInt(1));
			log.setPassword(res.getString(2));
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return log;
		
	}
	
	
	

//	4. delete done
	public boolean deleteDataIntoTable(String type, int id) {
		int res = 0;
		int res2 = 0;
		try {
			
			
			
			if(type.equals("customers")) {
				statement = conection.prepareStatement(DELETE_Acc_Query);
				statement.setInt(1, id);
				res = statement.executeUpdate();
			}
			
			
			if(type.equals("employees")){
				statement = conection.prepareStatement(DELETE_Query_Employee);
			}else {
				statement = conection.prepareStatement(DELETE_Query_Customer);
					
			}
			
			
			statement.setInt(1, id);
			res2 = statement.executeUpdate();
			
			if(res2==0) {
				return false;
			}else {
				return true;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}			
			
		
		return false;
	}

//	5.
	public Register getAllDetails(String type, int id) {
		
		Register register = new Register();
		ResultSet res = null;
		
		try {
			
			if(type.equals("employees")){
				statement = conection.prepareStatement(Get_ALL_Details_Employee);
			}else {
				statement = conection.prepareStatement(Get_ALL_Details_Customer);
					
			}
			
			statement.setInt(1, id);
			res = statement.executeQuery();
			res.next();
			register.setAccountId(res.getInt(1));
			register.setName(res.getString(2));
			register.setFatherName(res.getString(3));
			register.setAge(res.getInt(4));
			register.setPhoneNumber(res.getLong(5));
			register.setAadharCard(res.getLong(6));
			register.setAddress(res.getString(7));
			register.setNew_Password(res.getString(8));
			
			return register;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		
		return null;
	}
	
	//	Employee Operations
	
//	6.
	public boolean approveAllCustomerId() {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			statement = conection.prepareStatement(set_Approve_All_Query);
			res = statement.executeUpdate();
			
			if(res==0) {
				return false;
			}else {
				return true;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		return false;
	}

//	7.
	public boolean rejectAllCustomerId() {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			statement = conection.prepareStatement(set_reject_All_Query);
			res = statement.executeUpdate();
			
			if(res==0) {
				return false;
			}else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		return false;
	}

//	8.
	public boolean approveCustomerById(int id) {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			statement = conection.prepareStatement(set_Approve_Query_by_Id);
			statement.setInt(1, id);
			res = statement.executeUpdate();
			
			if(res==0) {
				return false;
			}else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		return false;
	}

//	9.
	public boolean rejectCustomerById(int id) {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			statement = conection.prepareStatement(set_Reject_Query_by_Id);
			statement.setInt(1, id);
			res = statement.executeUpdate();
			
			if(res==0) {
				return false;
			}else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		return false;
	}
	
//	10.//balance included
	public List<Register> showCustomerDetails() {
		ResultSet res = null;
		List<Register> registers = new ArrayList<Register>();
		try {
			Statement statement = conection.createStatement();
			
			res = statement.executeQuery(show_Customer_Details_Query);
			
			while(res.next()) {
				Register register = new Register();
				register.setAccountId(res.getInt(1));
				register.setName(res.getString(2));
				register.setFatherName(res.getString(3));
				register.setAge(res.getInt(4));
				register.setPhoneNumber(res.getLong(5));
				register.setAadharCard(res.getLong(6));
				register.setAddress(res.getString(7));
				register.setStatus(res.getString(9));
				register.setBalance(res.getInt(10));
				registers.add(register);		
				
				
			}
			return registers;
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		
		return null;
	}
	
	
//	Customer Operations
	
//	cheakstatus
	public boolean checkStatus(int id) {
		// TODO Auto-generated method stub
		ResultSet res;
		try {
			statement = conection.prepareStatement(check_status_Query);
			statement.setInt(1, id);
			
			res = statement.executeQuery();
			
			res.next();
			String status = res.getString(1);
			
			if(status.equals("Approved")) {
				return true;
			}
			else
				return false;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		
		return false;
	}
	
//	11
	public int viewBalance(int id) {
		// TODO Auto-generated method stub
		
		ResultSet res;
		try {
			statement = conection.prepareStatement(view_Balance_Query);
			statement.setInt(1, id);
			
			res = statement.executeQuery();
			
			res.next();
			int bal = res.getInt(1);
			return bal;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		return 0;
		
	}

//	12.
	public boolean transferAmount(int debitId, int creditId, int amount) {
		int res = 0;
		try {
//			CallableStatement statement = conection.prepareStatement("call app.transferAmount(?, ?,?);");
			statement = conection.prepareStatement("transfer_Balance_Query");
			statement.setInt(1, debitId);
			statement.setInt(3, amount);
			statement.setInt(2, creditId);
			
			res = statement.executeUpdate();
			
			if(res==0) {
				return false;
			}else {
				return true;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		
		return false;
	}

//	13.
	public boolean addAmount(int id, int amount) {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			statement = conection.prepareStatement(add_Balance_Query);
			statement.setInt(1, amount);
			statement.setInt(2, id);
			
			res = statement.executeUpdate();
			
			if(res==0) {
				return false;
			}else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Amount Should not be less than zero");
		}
		
		return false;
	}

//	14.
	public boolean withdrawAmount(int id, int amount) {
		int res = 0;
		try {
			statement = conection.prepareStatement(Withdraw_Balance_Query);
			statement.setInt(1, amount);
			statement.setInt(2, id);
			
			res = statement.executeUpdate();
			
			if(res==0) {
				return false;
			}else {
				return true;
			}
		} catch (InsufficientAmountException e) {
			// TODO Auto-generated catch block
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	public boolean isExists(String type,int id)  {

		boolean result = false;

		try {

			if(type.equals("employees")){
				statement = conection.prepareStatement(FIND_ID_QUERY_Employee);
			}else {
				statement = conection.prepareStatement(FIND_ID_QUERY_Customer);
					
			}
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();

			if (res.next()) {
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("This Id Doesn't Exist");
		}

		return result;
	}

	

}
