package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.DBConnection;
import com.model.Register;

public class EmployeeFunctions implements EmpoyeeMethod {
	
	Connection conection = DBConnection.getDBConnection();
	PreparedStatement statement;

	
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
	
	
	

}
