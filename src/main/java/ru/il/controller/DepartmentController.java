package ru.il.controller;

import ru.il.model.Department;
import ru.il.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.il.config.UserLoginInterceptor;

@Controller
public class DepartmentController {

	private final DepartmentService departmentService;

	@Autowired
	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	@GetMapping("/showNewDepartmentForm")
	public String showNewEquipmentForm(Model model) {
		UserLoginInterceptor.addIsAdminToForm(model);
		Department department = new Department();
		model.addAttribute("department", department);
		return "new_department";
	}
	
	@PostMapping("/saveDepartment")
	public String saveEmployee(@ModelAttribute("department") Department department) {
		if (UserLoginInterceptor.isCurrentUserIsAdmin()) {
			departmentService.saveDepartment(department);
		}
		return "redirect:/";
	}

}
