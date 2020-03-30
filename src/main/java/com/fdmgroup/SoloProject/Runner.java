package com.fdmgroup.SoloProject;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


import com.fdmgroup.Entities.Product;
import com.fdmgroup.dao.ProductDAO;

public class Runner {

	public static void main(String[] args) {
        
		ProductDAO productDAO = new ProductDAO();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("punit");
		EntityManager em = emf.createEntityManager();
        
        productDAO.setEm(em);
        

        EntityTransaction entityTransaction = em.getTransaction();
        
        entityTransaction.begin();
  
        entityTransaction.commit();

		
		Product product1 = new Product(1,"Chair",30.00);
		Product product2 = new Product(2,"Table",50.00);
		Product product3 = new Product(3,"Rug",10.00);
		
		
		
		productDAO.addProduct(product1);
		productDAO.addProduct(product2);
		productDAO.addProduct(product3);
		
	}
}
