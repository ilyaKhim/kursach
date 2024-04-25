package ru.il.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import ru.il.config.UserLoginInterceptor;
import ru.il.model.Maintenance;
import ru.il.model.User;
import ru.il.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import static ru.il.config.UserLoginInterceptor.addIsAdminToForm;

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
									   HttpServletResponse response,
									   Model model) {
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

	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("maintenance") User user) {
		byte isAdmin = 0;
		user.setIsAdmin(isAdmin);
		userService.saveUser(user);
		return "redirect:/";
	}
	@GetMapping("/registerEmployee")
	public String showFormForUpdateMaintenance(Model model) {
		addIsAdminToForm(model);
		User user = new User();

		model.addAttribute("user", user);
		return "register_employee";
	}

}
