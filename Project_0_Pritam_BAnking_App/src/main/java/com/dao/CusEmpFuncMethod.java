package com.dao;

import com.model.Login;
import com.model.Register;

public interface CusEmpFuncMethod {
	
	public int addDataIntoTable(String type, Register register);
	public Register cheakIntoTable(String type, int id, String password);
	public boolean updateDataIntoTable(String type,int id, Register register);
	public boolean deleteDataIntoTable(String type, int id);
	public Register getAllDetails(String type, int id);
	public boolean reset_Password(String type, int id, String passowrd);
	public Login getResetDetails(String type, String name, long phoneNumber, long aadharNumber);
}
