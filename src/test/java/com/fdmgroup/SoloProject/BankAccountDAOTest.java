package com.fdmgroup.SoloProject;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.Entities.BankAccount;
import com.fdmgroup.dao.BankAccountDAO;

import exceptions.BankAccountAlreadyExists;




public class BankAccountDAOTest {

	
	
	
	private EntityManagerFactory entityManagerFactory = 
			Persistence.createEntityManagerFactory("punit");

	private EntityManager entityManager = entityManagerFactory.createEntityManager();
	
	private BankAccountDAO bankAccountDAO = new BankAccountDAO();
	
	@Before
	public void setUp() throws Exception {
		bankAccountDAO.setEntityManager(entityManager);
		
		TypedQuery<BankAccount> queryDeleteQuery = entityManager.createQuery("Delete from BANKACCOUNT",BankAccount.class);
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		queryDeleteQuery.executeUpdate();
		entityTransaction.commit();
	}

	@Test
	public void testThatWhenTheListBankAccountMethodIsCalledThenReturnsListWithSizeZero() {
		
		List<BankAccount> listBankAccount = bankAccountDAO.listBankAccounts();
		int size = listBankAccount.size();
		assertEquals(0, size);
	}
	
	@Test
	public void testThatWhenAddOneBankAccountCallingaddBankAccountMethodThenReturnsListWithSizeOne() throws BankAccountAlreadyExists {

		
		BankAccount bankAccount1 = new BankAccount();
		bankAccount1.setBankAccountNumber(12341234);
		bankAccountDAO.addBankAccount(bankAccount1);
		
		List<BankAccount> listBankAccount = bankAccountDAO.listBankAccounts();
		int size = listBankAccount.size();
		assertEquals(1, size);
	
	}

	@Test
	public void testThatWhenAddABankAccountThenGetBankAccountMethodIsCalledThenReturnsCorrectBankAccountNumber() throws BankAccountAlreadyExists {
		
		BankAccount bankAccount1 = new BankAccount();
		bankAccount1.setBankAccountNumber(12344312);
		bankAccountDAO.addBankAccount(bankAccount1);
		
		BankAccount bankAccountInDB = bankAccountDAO.getBankAccount(12344312);
		assertEquals(12344312, bankAccountInDB.getBankAccountNumber());
	}
	
	@Test
	public void testThatWhenIAddABankAccountAndRemoveTheSameBankAccountThereAreNoBankAccountsInTheTable() throws BankAccountAlreadyExists {
		
		BankAccount bankAccount1 = new BankAccount();
		bankAccount1.setBankAccountNumber(12344312);
		bankAccountDAO.addBankAccount(bankAccount1);
		
		bankAccountDAO.removeBankAccount(12344312);
		
		List<BankAccount> listBankAccount = bankAccountDAO.listBankAccounts();
		int size = listBankAccount.size();
		assertEquals(0, size);
	}
	
	@Test
	public void testThatWhenIAddABankAccountAndRemoveTheSameBankAccountThenMethodReturnsNull() throws BankAccountAlreadyExists {
		
		BankAccount bankAccount1 = new BankAccount();
		bankAccount1.setBankAccountNumber(12344312);
		bankAccountDAO.addBankAccount(bankAccount1);
		
		
		bankAccountDAO.removeBankAccount(12344312);
		
		
		BankAccount bankAccountInDB = bankAccountDAO.getBankAccount(12344312);
		assertNull(bankAccountInDB);
	}
	
	@Test
	public void testThatWhenIaddABankAccountThenWhenICallUpdateBankAccountItUpdatesTheBankAccount() throws BankAccountAlreadyExists{
		
		BankAccount bankAccount1 = new BankAccount();
		bankAccount1.setBankAccountNumber(12344312);
		bankAccountDAO.addBankAccount(bankAccount1);
		
		BankAccount newBankAccount = new BankAccount();
				
		
		
		newBankAccount.setBankAccountNumber(12344312);
		newBankAccount.setBalance(3000);
		bankAccountDAO.updateBankAccount(newBankAccount);
		
		BankAccount bankAccountInDB = bankAccountDAO.getBankAccount(12344312);
		double balance = bankAccountInDB.getBalance();
		assertEquals(3000, balance,0.1);
	}
	
	@Test(expected=BankAccountAlreadyExists.class)
	public void testThatWhenBankAccountIsAddedAndABankAccountIsAddedWithSameBankAccountNumberAddedReturnsSizeOne() throws BankAccountAlreadyExists {
		
		BankAccount bankAccount1 = new BankAccount();
		bankAccount1.setBankAccountNumber(12344312);
		bankAccountDAO.addBankAccount(bankAccount1);
		
		BankAccount bankAccount2 = new BankAccount();
		bankAccount2.setBankAccountNumber(12344312);
		bankAccountDAO.addBankAccount(bankAccount2);
		
		
		List<BankAccount> listBankAccount = bankAccountDAO.listBankAccounts();
		int size = listBankAccount.size();
		
		assertEquals(1, size);
	}
	
	
	@Test
	public void testThatWhenBankAccountIsUpdatedWhereBankAccountDoesntExistReturnZero() throws BankAccountAlreadyExists {
		
		
		BankAccount bankAccount1 = new BankAccount();
		bankAccount1.setBankAccountNumber(12344312);
		bankAccount1.setBalance(3000);
		bankAccountDAO.addBankAccount(bankAccount1);
		
		BankAccount bankAccount2 = new BankAccount();
		bankAccount2.setBankAccountNumber(12344313);
		bankAccount2.setBalance(4000);
	
		bankAccountDAO.updateBankAccount(bankAccount2);
		List<BankAccount> listBankAccount = bankAccountDAO.listBankAccounts();
		int size = listBankAccount.size();
	
		assertEquals(1, size);
	}
	
	
	

}
