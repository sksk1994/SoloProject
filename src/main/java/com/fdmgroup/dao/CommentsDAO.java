package com.fdmgroup.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.fdmgroup.Entities.Comments;

@Repository
public class CommentsDAO {
	
	private EntityManager em;
	private List<Comments> comments = new ArrayList<Comments>();
	
	public void setEntityManager(EntityManager entityManager) {
		this.em = entityManager;
	}

	public List<Comments> listComments() {
		// TODO Auto-generated method stub
		TypedQuery<Comments> listCommentsQuery = em.createQuery("Select c from SC_COMMENTS c",
				Comments.class);
		comments = listCommentsQuery.getResultList();

		return comments;
	}

	public void addComment(Comments comment) {
		Comments commentInDB = em.find(Comments.class, comment.getCommentID());
		
		if (commentInDB == null) {
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();
		em.persist(comment);
		entityTransaction.commit();
		}
	}


	public Comments getComment(int comment) {
		// TODO Auto-generated method stub
		Comments commentsInDB = em.find(Comments.class, comment);
		return commentsInDB;
	}

	public void removeComment(int commentID) {
		// TODO Auto-generated method stub
		Comments commentsInDB = em.find(Comments.class, commentID);
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();

		em.remove(commentsInDB);

		entityTransaction.commit();
	}

	public void updateComment(Comments updatedComment) {
		Comments commentsInDB = em.find(Comments.class, updatedComment.getCommentID());
		if (commentsInDB != null) {
		EntityTransaction entityTransaction = em.getTransaction();
		entityTransaction.begin();
		em.merge(updatedComment);

		entityTransaction.commit();
		}
	}
}
