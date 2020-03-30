package com.fdmgroup.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity(name="SC_COMMENTS")
public class Comments {
	
	@Id
	@SequenceGenerator(name = "commentIDseq", sequenceName = "comment_ID_seq", initialValue = 1000, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "commentIDseq")
	private int commentID;
	@ManyToOne
	private User user;
	@ManyToOne
	private Product product;
	private String commentString;
	
	

	public Comments() {
		super();
	}

	public Comments(User user, Product product, String commentString) {
		super();
		this.user = user;
		this.product = product;
		this.commentString = commentString;
	}

	public int getCommentID() {
		return commentID;
	}

	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}

	public String getCommentString() {
		return commentString;
	}

	public void setCommentString(String commentString) {
		this.commentString = commentString;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}





	
}
