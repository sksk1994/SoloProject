package controllers;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fdmgroup.Entities.User;
import com.fdmgroup.dao.UserDAO;

import exceptions.UserAlreadyExists;
import exceptions.UserEmailAlreadyExists;

@Controller
@SessionAttributes("theuser")
public class UserController {

	@Autowired
	private UserDAO userDAO;

	@ModelAttribute("theuser")
	public User createUser() {
		return new User();
	}

	@RequestMapping("/register")
	public String doRegistration() {

		System.out.println("start of controller method");

		return "register";
	}

	@RequestMapping("/processUser")
	public String processUser(Model model, @RequestParam String username, @RequestParam String password,
			@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email) {

		User user = new User(username, password, firstName, lastName, email);

		try {
			userDAO.addUser(user);
		} catch (UserAlreadyExists e) {

			model.addAttribute("regError", "Username already exists");
			return "register";
		} catch (UserEmailAlreadyExists e) {

			model.addAttribute("regError", "Email already exists");
			return "register";
		}

		model.addAttribute("uName", username);
		model.addAttribute("pWord", password);
		return "signinpage";
	}

	@RequestMapping("/signIn")
	public String signIn(Model model, @RequestParam String username, @RequestParam String password,
			HttpServletRequest request) {

		User user = userDAO.getUser(username);
		String passWord = user.getPassword();

		if (user == null || !passWord.equals(password)) {
			model.addAttribute("loginError", "Username or password incorrect.");
			return "signinpage";
		}

		HttpSession session = request.getSession();
		session.setAttribute("username", username);

		
		return "signedIn";

	}

	@RequestMapping("/signOut")
	public String signOut(HttpSession session) {
		session.invalidate();
		return "signinpage";
	}

	@RequestMapping("/accountDetails")
	public String accountDetails(HttpSession session, Model model) {
		
		String usernameStored = (String)session.getAttribute("username");
		model.addAttribute("username", usernameStored);
		
		return "accountdetails";
	}

	@RequestMapping("/updateUser")
	public String updateUser(HttpSession session, Model model, @RequestParam String username,
			@RequestParam String password, @RequestParam String firstName, @RequestParam String lastName,
			@RequestParam String email) {

		User newUser = new User(username, password, firstName, lastName, email);
		userDAO.updateUser(newUser);
		model.addAttribute("uName", username);

		return "signedIn";

	}

	@RequestMapping("/homeScreen")
	public String goHome(HttpSession session, Model model) {

		return "signedIn";
	}

}
