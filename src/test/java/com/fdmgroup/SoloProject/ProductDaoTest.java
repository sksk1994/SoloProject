package com.fdmgroup.SoloProject;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.Entities.Product;
import com.fdmgroup.dao.ProductDAO;






public class ProductDaoTest {


	private EntityManagerFactory entityManagerFactory = 
			Persistence.createEntityManagerFactory("punit");

	private EntityManager entityManager = entityManagerFactory.createEntityManager();
	
    private ProductDAO productDAO = new ProductDAO();
	
	
    
    @Before
	public void setup() {
    	productDAO.setEm(entityManager);
		
		TypedQuery<Product> queryDeleteQuery = entityManager.createQuery("Delete from PRODUCT",Product.class);
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		queryDeleteQuery.executeUpdate();
		entityTransaction.commit();
		
		
	}
	
	@Test
	public void testThatWhenTheGetProductMethodIsCalledThenReturnsListWithSizeZero() {

	//    productDAO.setEntityManager(entityManager);
		List<Product> listProducts = productDAO.listProducts();
		int size = listProducts.size();
		assertEquals(0, size);

	}
	
	@Test
	public void testThatWhenAddProductMethodIsCalledThenReturnsListWithSizeOne() {
		//UsersDAO usersDAO = new UsersDAO();
		
		Product product = new Product();
		product.setProductID(1);
		productDAO.addProduct(product);
		
		List<Product> listProducts = productDAO.listProducts();
		int size = listProducts.size();
		assertEquals(1, size);
	}
	
	@Test
	public void testThatWhenAddAProductThenGetProductMethodIsCalledThenReturnsCorrectProductID() {
		
		Product product = new Product();
		product.setProductID(1);
		productDAO.addProduct(product);
		
		Product productInDB = productDAO.getProduct(1);
		
		assertEquals(1, productInDB.getProductID());
	}
	
	@Test
	public void testThatWhenIAddAProductAndRemoveTheSameProductThereAreNoProductsInTheTable() {
		
		Product product = new Product();
		product.setProductID(1);
		
		productDAO.addProduct(product);
		
		productDAO.removeProduct(1);
		
		List<Product> listProducts = productDAO.listProducts();
		int size = listProducts.size();
		assertEquals(0, size);
	}
	@Test
	public void testThatWhenIAddAProductAndRemoveTheSameProductMethodReturnsNull() {
		
		Product product = new Product();
		product.setProductID(1);
		productDAO.addProduct(product);
		
		productDAO.removeProduct(1);
		Product productInDB = productDAO.getProduct(1);
		assertNull(productInDB);
	}
	
	@Test
	public void testThatWhenIaddAProductThenWhenICallUpdateProductItUpdatesTheProduct(){
		
		Product product = new Product();
		product.setProductID(1);
		productDAO.addProduct(product);
		
		Product newProduct = new Product();
				
		newProduct.setProductID(1);
		newProduct.setName("Chair");
		productDAO.updateProduct(newProduct);
		
		Product productInDB = productDAO.getProduct(1);
		String name = productInDB.getName();
		assertEquals("Chair", name);
	}
	
	@Test
	public void testThatWhenTheAddProductToBasketMethodIsCalledAndZeroProductsAddedThenReturnsMapWithSizeZero() {

	
		Map<Product, Integer> basket = new HashMap<>();	
		int size = basket.size();
		assertEquals(0, size);

	}
	@Test
	public void testThatWhenTheAddProductToBasketMethodIsCalledAndOneProductIsAddedThenReturnsMapWithSizeOne() {
		
		Product product = new Product();
		product.setProductID(1);
		product.setName("Chair");
		
		productDAO.addProduct(product);
	//    productDAO.setEntityManager(entityManager);
		Map<Product, Integer> basket = new HashMap<>();
		productDAO.addProductToBasket(4, product);
		
		basket = productDAO.getBasket();
		
		int size = basket.size();
		assertEquals(1, size);
	}
	
	@Test
	public void testThatWhenTheAddProductToBasketMethodIsCalledAddingAProductAndRemoveTheSameProductFromBasMethodReturnsNull() {
		
		Product product = new Product();
		product.setProductID(1);
		product.setName("Chair");
		
		productDAO.addProduct(product);
	//    productDAO.setEntityManager(entityManager);
		Map<Product, Integer> basket = new HashMap<>();
		productDAO.addProductToBasket(4, product);
		
		productDAO.removeProductFromBasket(product);
		
		basket = productDAO.getBasket();
		
		
		assertEquals(0, basket.size());
	}
	
	@Test
	public void testThatWhenGetPriceOfBasketMethodIsCalledPriceOfBasketWillReturn51() {
		
		Map<Product, Integer> basket = new HashMap<>();
		basket = productDAO.getBasket();
		
		Product product = new Product();
		product.setProductID(1);
		product.setName("Chair");
		product.setPrice(25.50);
		
		Product product2 = new Product();
		product2.setProductID(2);
		product2.setName("table");
		product2.setPrice(25.50);
		
		productDAO.addProductToBasket(1, product);
		productDAO.addProductToBasket(1, product2);
		
		
		
		double totalPrice = productDAO.getBasketPrice();
		
		assertEquals(51.0,totalPrice, 0.000001);
	}
}
