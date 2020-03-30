package com.fdmgroup.SoloProject;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.Order;

import org.junit.Before;
import org.junit.Test;


import com.fdmgroup.Entities.Comments;
import com.fdmgroup.Entities.Orders;
import com.fdmgroup.Entities.User;
import com.fdmgroup.dao.OrdersDAO;
import com.fdmgroup.dao.UserDAO;

import exceptions.BankAccountAlreadyExists;
import exceptions.UserAlreadyExists;
import exceptions.UserEmailAlreadyExists;


public class OrderDAOTest {

	private OrdersDAO ordersDAO = new OrdersDAO();
	private UserDAO usersDAO = new UserDAO();

	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("punit");

	private EntityManager entityManager = entityManagerFactory.createEntityManager();
	private EntityManager entityManagerUser = entityManagerFactory.createEntityManager();

	@Before
	public void setUp() throws Exception {
		
		ordersDAO.setEntityManager(entityManager);
		usersDAO.setEm(entityManagerUser);
		TypedQuery<Orders> queryDeleteQuery = entityManager.createQuery("Delete from ORDERS", Orders.class);

		EntityTransaction entityTransaction = entityManager.getTransaction();
		
		entityTransaction.begin();

		queryDeleteQuery.executeUpdate();
		entityTransaction.commit();
		
	}

	@Test
	public void testThatWhenTheListOrdersMethodIsCalledThenReturnsListWithSizeZero() {

		List<Orders> listOrders = ordersDAO.listOrders();
		int size = listOrders.size();
		assertEquals(0, size);
	}

	@Test
	public void testThatWhenAddOrderMethodIsCalledThenReturnsListWithSizeOne() {
		// UsersDAO usersDAO = new UsersDAO();

		Orders order = new Orders();
		order.setOrderID(1);

		ordersDAO.addOrder(order);

		List<Orders> listOrders = ordersDAO.listOrders();
		int size = listOrders.size();
		assertEquals(1, size);

	}

	@Test
	public void testThatWhenAddAOrderThenGetOrderMethodIsCalledThenReturnsCorrectOrderID() {
		// UsersDAO usersDAO = new UsersDAO();

		Orders order = new Orders();
		order.setOrderID(1);

		ordersDAO.addOrder(order);

		Orders orderInDB = ordersDAO.getOrder(1);

		assertEquals(1, orderInDB.getOrderID());
	}
	
	@Test
	public void testThatWhenIAddAOrderAndRemoveTheSameOrderThereAreNoOrdersInTheTable() {
		
		Orders order = new Orders();
		order.setOrderID(1);
		ordersDAO.addOrder(order);
		
		ordersDAO.removeOrder(1);
		
		List<Orders> listOrders = ordersDAO.listOrders();
		int size = listOrders.size();
		assertEquals(0, size);
	}
	

	
	@Test
	public void testThatWhenIAddAUserAndRemoveTheSameUserMethodReturnsNull() {
		
		Orders order = new Orders();
		order.setOrderID(1);
		ordersDAO.addOrder(order);
		
		ordersDAO.removeOrder(1);
		Orders orderInDB = ordersDAO.getOrder(1);
		assertNull(orderInDB);
	}
	
	@Test(expected= UserAlreadyExists.class)
	public void testThatWhenIaddAOrderThenWhenICallUpdateOrderItUpdatesTheOrder() throws UserAlreadyExists, UserEmailAlreadyExists{
		
		Orders order = new Orders();
		order.setOrderID(1);
		ordersDAO.addOrder(order);
		
		
		User user = new User();
		user.setUsername("Adrian");
		usersDAO.addUser(user);
		
		Orders newOrder = new Orders();
		newOrder.setOrderID(1);
		newOrder.setUser(user);
		
		ordersDAO.updateOrder(newOrder);
		
		Orders orderInDB = ordersDAO.getOrder(1);
		String username = orderInDB.getUser().getUsername();
		assertEquals("Adrian", username);
	
	}
	
	@Test(expected =UserAlreadyExists.class)
	public void testThatWhenUserIsAddedAndASecondUserIsAddedWithSameUsernameAddedReturnsSizeOne() throws UserAlreadyExists, UserEmailAlreadyExists {
		Orders order = new Orders();
		order.setOrderID(1);
		ordersDAO.addOrder(order);
		
		
		User user = new User();
		user.setUsername("Adrian");
		usersDAO.addUser(user);
		
		Orders newOrder = new Orders();
		newOrder.setOrderID(1);
		newOrder.setUser(user);
		ordersDAO.addOrder(newOrder);
		
		List<User> listUsers = usersDAO.listUsers();
		int size = listUsers.size();
		assertEquals(1, size);
	}

}
