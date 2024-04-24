package net.javaguides.springboot.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import net.javaguides.springboot.config.UserLoginInterceptor;
import net.javaguides.springboot.model.Department;
import net.javaguides.springboot.model.User;
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
		User user = userService.authenticateUser(username, password);

		if (user != null) {
			String userHash = UserLoginInterceptor.addAuthenticatedUser(user.getId(), user.getIsAdmin());
			Cookie userIdCookie = new Cookie("userHash", userHash);
			userIdCookie.setHttpOnly(true);
			userIdCookie.setPath("/");
			response.addCookie(userIdCookie);

			return "redirect:/";
		} else {
			return "login";
		}
	}

}
