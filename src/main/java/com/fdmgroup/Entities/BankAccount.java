package com.fdmgroup.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity(name="BANKACCOUNT")
public class BankAccount {
	@Id
	private long bankAccountNumber;
	private double balance;
	private User user;
	
	
	

	public BankAccount(long bankAccountNumber, double balance, User user) {
		super();
		this.bankAccountNumber = bankAccountNumber;
		this.balance = balance;
		this.user = user;
	}
	
	

	public BankAccount(long bankAccountNumber, double balance) {
		super();
		this.bankAccountNumber = bankAccountNumber;
		this.balance = balance;
	}



	public BankAccount() {
		super();
	}



	public long getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(long bankAccountNumber) {
		// TODO Auto-generated method stub
		this.bankAccountNumber = bankAccountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	


}
