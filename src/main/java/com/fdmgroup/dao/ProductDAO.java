package com.fdmgroup.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;

import javax.persistence.EntityTransaction;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fdmgroup.Entities.Product;

@Repository
public class ProductDAO {

	private List<Product> listProduct = new ArrayList<Product>();

	@Autowired
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	private Map<Product, Integer> basket = new HashMap<>();

	public List<Product> listProducts() {

		TypedQuery<Product> listProductQuery = em.createQuery("Select p from PRODUCT p", Product.class);
		listProduct = listProductQuery.getResultList();

		return listProduct;
	}

	public void addProduct(Product product) {

		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();
		em.persist(product);
		entityTransaction.commit();
	}

	public Product getProduct(int productID) {

		Product productInDB = em.find(Product.class, productID);
		return productInDB;
	}

	public void removeProduct(int productID) {

		Product productInDB = em.find(Product.class, productID);
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();

		em.remove(productInDB);

		entityTransaction.commit();

	}

	public void updateProduct(Product newProduct) {

		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();
		em.merge(newProduct);

		entityTransaction.commit();
	}

	public void addProductToBasket(Integer quantity, Product product) {

		basket.put(product, quantity);

	}

	public void removeProductFromBasket(Product product) {

		basket.remove(product);

	}

	public Map<Product, Integer> getBasket() {

		return basket;
	}

	public double getBasketPrice(HashMap<Product, Integer> basket2) {

		double result = 0.0;
		for (Entry<Product, Integer> item : basket2.entrySet()) {

			result += item.getKey().getPrice() * item.getValue();
		}
		return result;
	}

}
