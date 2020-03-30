package com.fdmgroup.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fdmgroup.Entities.Orders;

@Repository
public class OrdersDAO {


	private List<Orders> listOrders = new ArrayList<Orders>();
	
	@Autowired
	private EntityManager em;

	public EntityManager getEntityManager() {
		return em;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.em = entityManager;
	}

	public List<Orders> listOrders() {

		TypedQuery<Orders> listOrdersQuery = em.createQuery("Select o from ORDERS o", Orders.class); 
		listOrders = listOrdersQuery.getResultList();

		return listOrders;

	}
	
	public List<Orders> listOrdersByUsername(String username) {

		TypedQuery<Orders> listOrdersQuery = em.createQuery("Select o from ORDERS o where o.user.username =:username", Orders.class); 
		listOrdersQuery.setParameter("username", username);
		listOrders = listOrdersQuery.getResultList();

		return listOrders;

	}
	
	

	public void addOrder(Orders order) {
		
		Orders orderInDB = em.find(Orders.class, order.getOrderID());
		if (orderInDB == null) {
		 
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();
		em.persist(order);
		entityTransaction.commit();
		}
	}

	public Orders getOrder(int orderID) {
		// TODO Auto-generated method stub
		Orders orderInDB = em.find(Orders.class, orderID);
		return orderInDB;
	}

	public void removeOrder(int orderID) {
		// TODO Auto-generated method stub
		Orders orderInDB = em.find(Orders.class, orderID);
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();

		em.remove(orderInDB);

		entityTransaction.commit();
	}

	public void updateOrder(Orders order) {
		
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();
		em.merge(order);

		entityTransaction.commit();
	}

}
