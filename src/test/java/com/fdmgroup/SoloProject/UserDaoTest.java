package com.fdmgroup.SoloProject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.Entities.User;
import com.fdmgroup.dao.UserDAO;

import exceptions.BankAccountAlreadyExists;
import exceptions.UserAlreadyExists;
import exceptions.UserEmailAlreadyExists;



public class UserDaoTest {

	
	
	private UserDAO usersDAO = new UserDAO();
	
	private EntityManagerFactory entityManagerFactory = 
			Persistence.createEntityManagerFactory("punit");

	private EntityManager entityManager = entityManagerFactory.createEntityManager();
	
	
	
	@Before
	public void setup() {

		usersDAO.setEm(entityManager);
		
		TypedQuery<User> queryDeleteQuery = entityManager.createQuery("Delete from SC_USER",User.class);
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		queryDeleteQuery.executeUpdate();
		entityTransaction.commit();
	
	}
	@Test
	public void testThatWhenTheListUsersMethodIsCalledThenReturnsListWithSizeZero() {

		List<User> listUsers = usersDAO.listUsers();
		int size = listUsers.size();
		assertEquals(0, size);

	}

	@Test(expected=UserAlreadyExists.class)
	public void testThatWhenAddUserMethodIsCalledThenReturnsListWithSizeOne() throws UserAlreadyExists, UserEmailAlreadyExists {
		//UsersDAO usersDAO = new UsersDAO();
		
		User user = new User();
		user.setUsername("Adrian");
		usersDAO.addUser(user);
		
		List<User> listUsers = usersDAO.listUsers();
		int size = listUsers.size();
		assertEquals(1, size);
	}
	
	@Test(expected=UserAlreadyExists.class)
	public void testThatWhenAddAUserThenGetUserMethodIsCalledThenReturnsCorrectUsername() throws UserAlreadyExists, UserEmailAlreadyExists {
		//UsersDAO usersDAO = new UsersDAO();
		
		User user = new User();
		user.setUsername("Adrian");
		usersDAO.addUser(user);
		
		User userInDB = usersDAO.getUser("Adrian");
		
		assertEquals("Adrian", userInDB.getUsername());
	}
	
	
	
	@Test
	public void testThatWhenIAddAUserAndRemoveTheSameUserThereAreNoUsersInTheTable() throws UserAlreadyExists, UserEmailAlreadyExists {
		
		User user = new User();
		user.setUsername("Adrian");
		usersDAO.addUser(user);
		
		usersDAO.removeUser("Adrian");
		
		List<User> listUsers = usersDAO.listUsers();
		int size = listUsers.size();
		assertEquals(0, size);
	}
	
	@Test
	public void testThatWhenIAddAUserAndRemoveTheSameUserMethodReturnsNull() throws UserAlreadyExists, UserEmailAlreadyExists {
		
		User user = new User();
		user.setUsername("Adrian");
		usersDAO.addUser(user);
		usersDAO.removeUser("Adrian");
		User userInDB = usersDAO.getUser("Adrian");
		assertNull(userInDB);
	}
	
	@Test
	public void testThatWhenIaddAUserThenWhenICallUpdateUserItUpdatesTheUser() throws UserAlreadyExists, UserEmailAlreadyExists{
		
		User user = new User();
		user.setUsername("Adrian");
		usersDAO.addUser(user);
		
		User newUser = new User();
				
		newUser.setUsername("Adrian");
		newUser.setPassword("Password");
		usersDAO.updateUser(newUser);
		
		User userInDB = usersDAO.getUser("Adrian");
		String password = userInDB.getPassword();
		assertEquals("Password", password);
	}
	
	@Test
	public void testThatWhenUserIsAddedAndASecondUserIsAddedWithSameUsernameAddedReturnsSizeOne() throws UserAlreadyExists, UserEmailAlreadyExists {
		User user = new User();
		user.setUsername("Adrian");
		usersDAO.addUser(user);
		
		User user2 = new User();
		user2.setUsername("Adrian");
		usersDAO.addUser(user2);
		
		
		List<User> listUsers = usersDAO.listUsers();
		int size = listUsers.size();
		assertEquals(1, size);
	}
	
	@Test
	public void testThatWhenUserIsAddedAndASecondUserIsAddedWithSameEmailAddedReturnsSizeOne() throws UserAlreadyExists, UserEmailAlreadyExists {
		User user = new User();
		user.setUsername("Adrian");
		user.setEmail("sohail.khan@fdmgroup.com");
		usersDAO.addUser(user);
		
		User user2 = new User();
		user2.setUsername("sohail");
		user2.setEmail("sohail.khan@fdmgroup.com");
		usersDAO.addUser(user2);
		
		List<User> listUsers = usersDAO.listUsers();
		int size = listUsers.size();
	
		assertEquals(1, size);
	}
		
	@Test
	public void testThatWhenUserStatusIsOneUserReturnsstatus1IsLoggedIn() throws UserAlreadyExists, UserEmailAlreadyExists {
		
		User user = new User();
		user.setUsername("Adrian");
		user.setStatus(1);
		usersDAO.addUser(user);
		
		User userInDB = usersDAO.getUser("Adrian");
		int status = userInDB.getStatus();
		assertEquals(1, status);
	}
	
	@Test
	public void testThatWhenUserIsUpdatedWhereUserDoesntExistReturnZero() throws UserAlreadyExists, UserEmailAlreadyExists {
		
		User user = new User();
		user.setUsername("Adrian");
		user.setStatus(1);
		usersDAO.addUser(user);
		
		User user2 = new User();
		user2.setUsername("Adrian2");
		user2.setStatus(1);
		

		usersDAO.updateUser(user2);
		List<User> listUsers = usersDAO.listUsers();
		int size = listUsers.size();
	
		assertEquals(1, size);
	}
	
	
}
