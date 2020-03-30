package com.fdmgroup.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="PRODUCT")
public class Product {
			
		@Id 
		public int productID;
		@Column
		public String name;
		@Column
		private double price;

		
		public int getProductID() {
			return productID;
		}

		public void setProductID(int productID) {
			this.productID = productID;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public Product(int productID, String name, double price) {
			super();
			this.productID = productID;
			this.name = name;
			this.price = price;
		}

		public Product() {
			super();
		}

		@Override
		public String toString() {
			return "Product [productID=" + productID + ", name=" + name + ", price=" + price + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			long temp;
			temp = Double.doubleToLongBits(price);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			result = prime * result + productID;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Product other = (Product) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
				return false;
			if (productID != other.productID)
				return false;
			return true;
		}
		
		
		
		
		
}
