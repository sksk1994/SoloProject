package controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.Entities.Comments;
import com.fdmgroup.Entities.Product;
import com.fdmgroup.Entities.User;
import com.fdmgroup.dao.CommentsDAO;
import com.fdmgroup.dao.ProductDAO;
import com.fdmgroup.dao.UserDAO;

@Controller
public class CommentsController {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	CommentsDAO commentsDAO;
	
	@RequestMapping("/productReviewsDropdown")
	public String chairReviewPage(Model model) {
		
		List<Product> productList = productDAO.listProducts();
		model.addAttribute("productList", productList);
		
		return "??";
	}
	
	@RequestMapping("/productReviews")
	public String listChairComments(Model model, HttpServletRequest request) {
		
		List<Comments> commentsList = commentsDAO.listComments();
		model.addAttribute("commentsList", commentsList);
		
		return "chair";
	}
	
	
	@RequestMapping("/postComment")
	public String postComment(HttpSession session, @RequestParam String commentString,  HttpServletRequest request) {

		
		String username = (String)session.getAttribute("username");
		User user = userDAO.getUser(username);
		Product product = productDAO.getProduct(1);
		
		
		Comments chairComment = new Comments(user, product, commentString);
		
		commentsDAO.addComment(chairComment);
		
		return "chair";
		
	}
	
	
}
