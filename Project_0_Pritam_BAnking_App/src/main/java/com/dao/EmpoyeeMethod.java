package com.dao;

import java.util.List;

import com.model.Register;

public interface EmpoyeeMethod {
	public boolean approveAllCustomerId();
	public boolean rejectAllCustomerId();
	public boolean approveCustomerById(int id);
	public boolean rejectCustomerById(int id);
	public List<Register> showCustomerDetails();
}
