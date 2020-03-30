package controllers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.Entities.BankAccount;
import com.fdmgroup.Entities.Orders;
import com.fdmgroup.Entities.Product;
import com.fdmgroup.Entities.User;
import com.fdmgroup.dao.BankAccountDAO;
import com.fdmgroup.dao.OrdersDAO;
import com.fdmgroup.dao.ProductDAO;
import com.fdmgroup.dao.UserDAO;

@Controller
public class ProductController {

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private BankAccountDAO bankAccountDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private OrdersDAO ordersDAO;

	private HashMap<Product, Integer> basket = new HashMap<Product, Integer>();

	@RequestMapping("/listProducts")
	public String listProducts(Model model) {

		List<Product> productList = productDAO.listProducts();
		model.addAttribute("productList", productList);

		return "signedIn";
	}

	@RequestMapping("/addToBasket/{productID}")
	public String addToBasket(Model model, @PathVariable int productID, @RequestParam Integer quantity) {

		Product product = productDAO.getProduct(productID);

		if (basket.containsKey(product)) {
			int basketQuantity = basket.get(product);
			basket.put(product, basketQuantity + quantity);
		} else {
			basket.put(product, quantity);
		}

		return "signedIn";
	}

	@RequestMapping("/removeFromBasket/{productID}")
	public String removeBasketItem(Model model, @RequestParam int productID) {

		Product product = productDAO.getProduct(productID);
		basket.remove(product);

		return "signedIn";

	}

	@RequestMapping("/displayBasketPage")
	public String displayBasketPage(Model model) {
		model.addAttribute("basket", basket);
		return "basket";
	}
	

	@RequestMapping("/displayBasketItems")
	public String displayBasketContents(Model model) {
		double basketPrice = productDAO.getBasketPrice(basket);
		model.addAttribute("price", basketPrice);
		model.addAttribute("basket", basket);
		return "basket";
	}

	@RequestMapping("/purchaseBasket")
	public String purchaseBasket(Model model, @RequestParam long bankaccountnumber, HttpSession session) {
		double cost = productDAO.getBasketPrice(basket);
		double currentBalance = bankAccountDAO.getBankAccount(bankaccountnumber).getBalance();
		double newBalance = currentBalance - cost;

		String username = (String)session.getAttribute("username");
		User user = userDAO.getUser(username);

		BankAccount bankAccount = new BankAccount(bankaccountnumber, newBalance, user);

		bankAccountDAO.updateBankAccount(bankAccount);




		Orders order = new Orders();
		order.setCost(cost);
		order.setUser(user);
		order.setOrderDate(LocalDate.now());
		ordersDAO.addOrder(order);
		basket.clear();

		List<Orders> ordersList = ordersDAO.listOrders();

		model.addAttribute("ordersList", ordersList);

		return "orders";

	}

	@RequestMapping("/basketCost")
	public String displayBasketCost(Model model) {

		double basketPrice = productDAO.getBasketPrice(basket);
		model.addAttribute("price", basketPrice);

		return "basket";

	}

}
