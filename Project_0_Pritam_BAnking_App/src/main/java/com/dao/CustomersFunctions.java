package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdbc.DBConnection;

public class CustomersFunctions implements CustomerMethod {

	
	Connection conection = DBConnection.getDBConnection();

	
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
			statement = conection.prepareStatement(transfer_Balance_Query);
			statement.setInt(1, amount);
			statement.setInt(2, debitId);
			statement.setInt(3, amount);
			statement.setInt(4, creditId);
			
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		return false;
	}
	
	


}
