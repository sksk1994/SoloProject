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

import com.fdmgroup.Entities.Comments;
import com.fdmgroup.dao.CommentsDAO;



public class CommentsDAOTest {

	private CommentsDAO commentsDAO = new CommentsDAO();

	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("punit");

	private EntityManager entityManager = entityManagerFactory.createEntityManager();

	@Before
	public void setUp() throws Exception {

		commentsDAO.setEntityManager(entityManager);

		TypedQuery<Comments> queryDeleteQuery = entityManager.createQuery("Delete from SC_COMMENTS", Comments.class);

		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		queryDeleteQuery.executeUpdate();
		entityTransaction.commit();
	}

	@Test
	public void testThatWhenTheListCommentsMethodIsCalledThenReturnsListWithSizeZero() {

		List<Comments> listComments = commentsDAO.listComments();
		int size = listComments.size();
		assertEquals(0, size);

	}

	@Test
	public void testThatWhenAddCommentMethodIsCalledThenReturnsListWithSizeOne() {
		// UsersDAO usersDAO = new UsersDAO();

		Comments comment = new Comments();
		comment.setCommentID(1);
		commentsDAO.addComment(comment);

		List<Comments> listComments = commentsDAO.listComments();
		int size = listComments.size();
		assertEquals(1, size);

	}

	@Test
	public void testThatWhenAddCommentThenGetCommentMethodIsCalledThenReturnsCorrectCommentID() {

		Comments comment = new Comments();
		comment.setCommentID(1);
		commentsDAO.addComment(comment);

		Comments commentInDB = commentsDAO.getComment(1);

		assertEquals(1, commentInDB.getCommentID());
	}

	@Test
	public void testThatWhenIAddACommentAndRemoveTheSameCommentThereAreNoCommentsInTheTable() {

		Comments comment = new Comments();
		comment.setCommentID(1);
		comment.setCommentString("This is a comment");
		commentsDAO.addComment(comment);

		commentsDAO.removeComment(1);

		List<Comments> listComments = commentsDAO.listComments();
		int size = listComments.size();
		assertEquals(0, size);
	}

	@Test
	public void testThatWhenIAddACommentAndRemoveTheSameCommentMethodReturnsNull() {

		Comments comment = new Comments();
		comment.setCommentID(1);
		comment.setCommentString("This is a comment");
		commentsDAO.addComment(comment);
		commentsDAO.removeComment(1);
		Comments commentInDB = commentsDAO.getComment(1);
		assertNull(commentInDB);
	}

	@Test
	public void testThatWhenIaddACommentThenWhenICallUpdateCommentItUpdatesTheComment() {

		Comments comment = new Comments();
		comment.setCommentID(1);
		comment.setCommentString("This is a comment");
		commentsDAO.addComment(comment);

		Comments comment2 = new Comments();
		comment2.setCommentID(1);
		comment2.setCommentString("This is a comment, comment");
		commentsDAO.updateComment(comment2);

		Comments commentInDB = commentsDAO.getComment(1);
		String commentString = commentInDB.getCommentString();
		assertEquals("This is a comment, comment", commentString);
	}

	@Test
	public void testThatWhenCommentIsAddedAndASecondCommentIsAddedWithSameCommentIDAddedReturnsSizeOne() {
		Comments comment = new Comments();
		comment.setCommentID(1);
		comment.setCommentString("This is a comment");
		commentsDAO.addComment(comment);

		Comments comment2 = new Comments();
		comment2.setCommentID(1);
		comment2.setCommentString("This is a comment");
		commentsDAO.addComment(comment2);

		List<Comments> listComments = commentsDAO.listComments();
		int size = listComments.size();
		assertEquals(1, size);
	}

	@Test
	public void testThatWhenCommentIsUpdatedWhereCommentDoesntExistReturnZero() {

		Comments comment = new Comments();
		comment.setCommentID(1);
		comment.setCommentString("This is a comment");
		commentsDAO.addComment(comment);

		Comments comment2 = new Comments();
		comment2.setCommentID(2);
		comment2.setCommentString("This is a comment.commment example update");
		commentsDAO.updateComment(comment2);

		List<Comments> listComments = commentsDAO.listComments();
		int size = listComments.size();
		assertEquals(1, size);

	}

}
