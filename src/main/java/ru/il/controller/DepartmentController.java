package ru.il.controller;

import org.springframework.web.bind.annotation.PathVariable;
import ru.il.model.Department;
import ru.il.model.Equipment;
import ru.il.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.il.config.UserLoginInterceptor;

import java.util.List;

import static ru.il.config.UserLoginInterceptor.addIsAdminToForm;
import static ru.il.config.UserLoginInterceptor.isCurrentUserIsAdmin;

@Controller
public class DepartmentController {

	private final DepartmentService departmentService;

	@Autowired
	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	@GetMapping("/showNewDepartmentForm")
	public String showNewEquipmentForm(Model model) {
		addIsAdminToForm(model);
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

	@GetMapping("/showAllDepartments")
	public String showAllDepartments(Model model) {
		if (isCurrentUserIsAdmin()) {
			addIsAdminToForm(model);
			List<Department> departments = departmentService.findAll();
			model.addAttribute("departments", departments);
			return "view_departments";
		} else return "redirect:/";
	}

}
