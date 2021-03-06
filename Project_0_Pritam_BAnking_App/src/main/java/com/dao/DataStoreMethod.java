package com.dao;

import java.util.List;
import java.util.Map;

import com.model.Register;

public interface DataStoreMethod {
	//return id which is generated by database
	public int addDataIntoTable(String type, Register register);
	public Register cheakIntoTable(String type, int id, String password);
	public boolean updateDataIntoTable(String type,int id, Register register);
	public boolean deleteDataIntoTable(String type, int id);
	public Register getAllDetails(String type, int id);
//	employee functions
	public boolean approveAllCustomerId();
	public boolean rejectAllCustomerId();
	public boolean approveCustomerById(int id);
	public boolean rejectCustomerById(int id);
	public List<Register> showCustomerDetails();
	
//	customer function
	public boolean checkStatus(int id);
	public int viewBalance(int id);
	public boolean transferAmount(int debitId, int creditId, int amount);
	public boolean addAmount(int id, int amount);
	public boolean withdrawAmount(int id, int amount);
	
	
	
	
}
