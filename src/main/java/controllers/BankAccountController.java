package controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.Entities.BankAccount;
 
import com.fdmgroup.dao.BankAccountDAO;
import com.fdmgroup.dao.UserDAO;

import exceptions.BankAccountAlreadyExists;

@Controller
public class BankAccountController {

	
	@Autowired
	private BankAccountDAO bankAccountDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping("/listBankAccounts")
	public String listBankAccount(Model model) {
		
		List<BankAccount> bankAccountList = bankAccountDAO.listBankAccounts();
		model.addAttribute("bankAccountList", bankAccountList);
		
		return "signedIn";
	}
	
	@RequestMapping("/addBankAccount")
	public String addBankAccount(Model model, @RequestParam long bankaccountnumber,@RequestParam double balance , HttpSession session) {
	
		String username = (String)session.getAttribute("username");
		
		
		BankAccount bankAccount = new BankAccount(bankaccountnumber, balance, userDAO.getUser(username));
		
		try {
			bankAccountDAO.addBankAccount(bankAccount);
		} catch (BankAccountAlreadyExists e) {
			 
			model.addAttribute("bankexists", "BankAccount already exists");
			return "accountdetails";
		}
		
		return "signedIn";	
}
	
	@RequestMapping("removeBankAccount")
	public String removeBankAccount(@RequestParam long bankaccountnumber) {
		
		bankAccountDAO.removeBankAccount(bankaccountnumber);
		
		return "signedIn";
		
	}
	
}
