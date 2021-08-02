package com.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.model.Login;
import com.model.Register;

public class DataStoreTest {
	
	DataStore data;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		data = new DataStore();
		
	}

	@After
	public void tearDown() throws Exception {
		
		data = null;
	}

	@Test
	public void testAddDataIntoTable() {
		
		List<Register> customerDetails1 = data.showCustomerDetails();
		Register reister = new Register("Dummy", "Dummy2", 21, 1234567897, 1234567891, "dummy address", "dummy Password", "dummy Password");
		int dummyId = data.addDataIntoTable("customers", reister);
		List<Register> customerDetails2 = data.showCustomerDetails();
		assertEquals(customerDetails2.size(), customerDetails1.size()+1);
		
		data.deleteDataIntoTable("customers", dummyId);
		
	}

	@Test
	public void testCheakIntoTable() {
		
		Register register = new Register("Dummy", "Dummy2", 21, 1234567897, 1234567891, "dummy address", "dummy Password", "dummy Password");
		int dummyId = data.addDataIntoTable("customers", register);
		Register dummy = data.cheakIntoTable("customers", dummyId, register.getNew_Password());
		assertEquals(dummyId, dummy.getAccountId());
		assertEquals(register.getName(), dummy.getName());
		assertEquals(register.getFatherName(), dummy.getFatherName());
		assertEquals(register.getAge(), dummy.getAge());
		assertEquals(register.getPhoneNumber(), dummy.getPhoneNumber());
		assertEquals(register.getAadharCard(), dummy.getAadharCard());
		assertEquals(register.getAddress(), dummy.getAddress());
		assertEquals(register.getNew_Password(), dummy.getNew_Password());
		assertEquals(register.getName(), dummy.getName());
		
		data.deleteDataIntoTable("customers", dummyId);
	}

	@Test
	public void testUpdateDataIntoTable() {
		
		Register demoregister = new Register("Dummy", "Dummy2", 21, 1234567897, 1234567891, "dummy address", "dummy Password", "dummy Password");
		int dummyId = data.addDataIntoTable("customers", demoregister);
		Register register = new Register("Dummy1", "Dummy3", 22, 1894561231, 1567891234, "dummy address2", "dummy Password2", "dummy Password");
		data.updateDataIntoTable("customers", dummyId, register);
		Register dummy = data.cheakIntoTable("customers", dummyId, register.getNew_Password());
		assertEquals(dummyId, dummy.getAccountId());
		assertEquals(register.getName(), dummy.getName());
		assertEquals(register.getFatherName(), dummy.getFatherName());
		assertEquals(register.getAge(), dummy.getAge());
		assertEquals(register.getPhoneNumber(), dummy.getPhoneNumber());
		assertEquals(register.getAadharCard(), dummy.getAadharCard());
		assertEquals(register.getAddress(), dummy.getAddress());
		assertEquals(register.getNew_Password(), dummy.getNew_Password());
		assertEquals(register.getName(), dummy.getName());
		
		data.deleteDataIntoTable("customers", dummyId);
		
		
	}

	@Test
	public void testReset_Password() {
//		
		Register register = new Register("Dummy", "Dummy2", 21, 1234567897, 1234567891, "dummy address", "dummy Password", "dummy Password");
		int dummyId = data.addDataIntoTable("customers", register);
		String str = "New Password";
		boolean bool = data.reset_Password("customers", dummyId, str);
		
		Register reg = data.getAllDetails("customers", dummyId);
		assertEquals(str, reg.getNew_Password());
		
		data.deleteDataIntoTable("customers", dummyId);
		
		
	}

	@Test
	public void testGetResetDetails() {
		
		String getName = "Dummy";
		long getPhone = 798885718;
		long getaadhar = 1234567845;
		String str = "New Password";
		Register register = new Register(getName, "Dummy2", 21, getPhone, getaadhar, "dummy address", str, "dummy Password");
		int dummyId = data.addDataIntoTable("customers", register);
////		okay
//		System.out.println(dummyId);
		Login login = data.getResetDetails("customers", getName, getPhone, getaadhar);
//		System.out.println(login.getAccountId()+" " +login.getPassword());
//		
		assertEquals(login.getAccountId(), dummyId);
//		assertEquals(str, login.getPassword());
//		
		data.deleteDataIntoTable("customers", dummyId);
//		
	}

	@Test
	public void testDeleteDataIntoTable() {
		
		Register register = new Register("Dummy", "Dummy2", 21, 1234567897, 1234567891, "dummy address", "dummy Password", "dummy Password");
		int dummyId = data.addDataIntoTable("customers", register);
		
		boolean bool = data.deleteDataIntoTable("customers", dummyId);
		assertEquals(true, bool);
		
	}

	@Test
	public void testGetAllDetails() {
//		fail("Not yet implemented");
	}

	@Test
	public void testApproveAllCustomerId() {
//		fail("Not yet implemented");
	}

	@Test
	public void testRejectAllCustomerId() {
//		fail("Not yet implemented");
	}

	@Test
	public void testApproveAllCustomerById() {
//		fail("Not yet implemented");
	}

	@Test
	public void testRejectAllCustomerById() {
//		fail("Not yet implemented");
	}

	@Test
	public void testShowCustomerDetails() {
//		fail("Not yet implemented");
	}

	@Test
	public void testCheckStatus() {
//		fail("Not yet implemented");
	}

	@Test
	public void testViewBalance() {
//		fail("Not yet implemented");
	}

	@Test
	public void testTransferAmount() {
//		fail("Not yet implemented");
	}

	@Test
	public void testAddAmount() {
//		fail("Not yet implemented");
	}

	@Test
	public void testWithdrawAmount() {
//		fail("Not yet implemented");
	}

}
