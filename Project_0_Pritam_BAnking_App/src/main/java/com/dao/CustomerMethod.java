package com.dao;

public interface CustomerMethod {
//	customer function
	public boolean checkStatus(int id);
	public int viewBalance(int id);
	public boolean transferAmount(int debitId, int creditId, int amount);
	public boolean addAmount(int id, int amount);
	public boolean withdrawAmount(int id, int amount);
}
