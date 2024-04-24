package net.javaguides.springboot.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import net.javaguides.springboot.model.Department;
import net.javaguides.springboot.service.DepartmentService;
import net.javaguides.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/login")
	public String showLoginForm() {
		return "login";
	}
	
	@PostMapping("/login")
	public String showNewEquipmentForm(@RequestParam("username") String username,
									   @RequestParam("password") String password,
									   HttpServletResponse response) {
		// Dummy example for user authentication
		boolean isAuthenticated = authenticateUser(username, password);

		if (isAuthenticated) {
			// If authentication is successful, set a cookie
			Cookie userIdCookie = new Cookie("userId", "your_user_id_here"); // Replace with actual user ID
			userIdCookie.setHttpOnly(true); // Make the cookie inaccessible to client-side scripts
			userIdCookie.setPath("/"); // Cookie available for entire application
			response.addCookie(userIdCookie);

			return "redirect:/"; // Redirect to a secure page
		} else {
			return "login"; // Return to login page (possibly add an error message)
		}
	}

	private boolean authenticateUser(String username, String password) {
		// Here, implement your authentication logic
		// This is just a dummy implementation
		return "admin".equals(username) && "pass123".equals(password);
	}

}
