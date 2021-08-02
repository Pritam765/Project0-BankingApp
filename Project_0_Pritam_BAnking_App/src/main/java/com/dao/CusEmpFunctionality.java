package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdbc.DBConnection;
import com.model.Login;
import com.model.Register;

public class CusEmpFunctionality implements CusEmpFuncMethod {
	Connection conection = DBConnection.getDBConnection();
	
	PreparedStatement statement;

	public int addDataIntoTable(String type, Register register) {
		ResultSet result = null;
		int res = 0;
		int id = 0;
		int res2 = 0;		
//		1.done
		 final String ADD_Register_Query = "insert into app."+type+"(newName, fatherName, newAge, phoneNumber, aadharCard, address, newPassword ) values(?,?,?,?,?,?,?);";
//		1.1 done
		 final String get_Id_Register_Query = "select accountid from app."+type+" where newName = ? and fatherName = ? and newAge = ? and phoneNumber = ? and aadharCard = ? and address = ? and newPassword = ?;";
//		//	1.2 only for customer///////////////////
		 final String ADD_account_Query = "insert into app.customeraccount values(?);";	

		try {
			
			statement = conection.prepareStatement(ADD_Register_Query);
			

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
				
				
				statement = conection.prepareStatement(get_Id_Register_Query);
					
				
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
			System.out.println(e);
		}
		return 0;
	}

	public Register cheakIntoTable(String type, int id, String password) {
		Register register = new Register();
		ResultSet res = null;
//		2. done
		final String cheak_Id_Query = "select * from app."+type+" where accountid = ? and newPassword = ?;";
		
		try {
			
			if(type.equals("employees")){
				statement = conection.prepareStatement(cheak_Id_Query);
			}else {
				statement = conection.prepareStatement(cheak_Id_Query);
					
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

	public boolean updateDataIntoTable(String type, int id, Register register) {
		int res = 0;
		
//		3. done
		final String UPDATE_Query = "update app."+type+" set newName = ?, fatherName = ?, newAge = ?, phoneNumber = ?, aadharCard = ?, address = ?, newPassword = ? where accountid = ?;";

		
		try {
			statement = conection.prepareStatement(UPDATE_Query);
			
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

	public boolean deleteDataIntoTable(String type, int id) {
		final String DELETE_Query = "delete from app."+type+" where accountid = ?;";
		final String DELETE_Acc_Query = "delete from app.customeraccount where accountid = ?;";

		int res = 0;
		int res2 = 0;
		try {
			
			
			
			if(type.equals("customers")) {
				statement = conection.prepareStatement(DELETE_Acc_Query);
				statement.setInt(1, id);
				res = statement.executeUpdate();
			}
			
		
			statement = conection.prepareStatement(DELETE_Query);
			
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

	public Register getAllDetails(String type, int id) {
		Register register = new Register();
		ResultSet res = null;
		final String Get_ALL_Details = "select * from app."+type+" where accountid = ?;";
		
		
		try {
			
			statement = conection.prepareStatement(Get_ALL_Details);
			
			
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

	public boolean reset_Password(String type, int id, String passowrd) {
		int res = 0;
		
		 final String Reset_Details = "update app."+type+" set newPassword = ? where accountid =  ?;";

		
		try {
			statement = conection.prepareStatement(Reset_Details);
			
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
		final String get_Reset_Details = "select accountid, newPassword from app."+type+" where newName = ? and phoneNumber = ? and aadharCard = ?;";
		Login log = new Login();
		ResultSet res = null;
		try {
			
				
			statement = conection.prepareStatement(get_Reset_Details);
					
				
			
			statement.setString(1, name);
			statement.setLong(2, phoneNumber);
			statement.setLong(3, aadharNumber);
			
			res = statement.executeQuery();
			res.next();
			log.setAccountId(res.getInt(1));
			log.setPassword(res.getString(2));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		return log;
	}


	public boolean isExists(String type,int id)  {

		boolean result = false;

		final String FIND_ID_QUERY_Customer = "select * from app.customers where accountid = ?";
		final String FIND_ID_QUERY_Employee = "select * from app.employees where accountid = ?";

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
