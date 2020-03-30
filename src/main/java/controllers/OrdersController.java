package controllers;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.fdmgroup.Entities.Orders;
import com.fdmgroup.dao.BankAccountDAO;
import com.fdmgroup.dao.OrdersDAO;
import com.fdmgroup.dao.ProductDAO;

@Controller
public class OrdersController {

	@Autowired
	OrdersDAO ordersDAO;
	
	@Autowired
	BankAccountDAO bankAccountDAO;
	
	@Autowired
	ProductDAO productDAO;
	
	
	
	@RequestMapping("/listOrders")
	public String listOrders(Model model, HttpSession session) {
		
		String username = (String)session.getAttribute("username");
		
		List<Orders> ordersList = ordersDAO.listOrdersByUsername(username);
		
		model.addAttribute("orderslist", ordersList);
		
		return "orders";
	}
	
	@RequestMapping("/orderRedirect")
	public String redirectOrder() {
		return "orderconfirmation";
		
	}
	


}
