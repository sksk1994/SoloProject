package com.fdmgroup.dao;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fdmgroup.Entities.BankAccount;

import exceptions.BankAccountAlreadyExists;




@Repository
public class BankAccountDAO {
	
	@Autowired
	private EntityManager em;
	private List<BankAccount> bankAccounts = new ArrayList<BankAccount>();

	public EntityManager getEntityManager() {
		return em;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.em = entityManager;
	}

	public List<BankAccount> listBankAccounts() {

		TypedQuery<BankAccount> listBankAccountsQuery = em.createQuery("Select b from BANKACCOUNT b",
				BankAccount.class);
		bankAccounts = listBankAccountsQuery.getResultList();

		return bankAccounts;
	}

	public void addBankAccount(BankAccount bankAccount) throws BankAccountAlreadyExists  {

		BankAccount BankAccountNumberInDB = em.find(BankAccount.class, bankAccount.getBankAccountNumber());

		if (BankAccountNumberInDB == null) {
			
			EntityTransaction entityTransaction = em.getTransaction();
			entityTransaction.begin();
			em.persist(bankAccount);
			entityTransaction.commit();
		}else {
			throw new BankAccountAlreadyExists();
		}

	}

	public BankAccount getBankAccount(long bankAccountNumber) {

		BankAccount bankAccountInDB = em.find(BankAccount.class, bankAccountNumber);
		return bankAccountInDB;

	}

	public void removeBankAccount(long bankaccountnumber) {
		BankAccount bankAccountInDB = em.find(BankAccount.class, bankaccountnumber);
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();

		em.remove(bankAccountInDB);

		entityTransaction.commit();

	}

	public void updateBankAccount(BankAccount newBankAccount) {

		BankAccount BankAccountNumberInDB = em.find(BankAccount.class,
				newBankAccount.getBankAccountNumber());

		if (BankAccountNumberInDB != null) {
			
			EntityTransaction entityTransaction = em.getTransaction();
			entityTransaction.begin();
			em.merge(newBankAccount);

			entityTransaction.commit();
		}

	}

}
