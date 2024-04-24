package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Department;
import net.javaguides.springboot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import static net.javaguides.springboot.config.UserLoginInterceptor.addIsAdminToForm;
import static net.javaguides.springboot.config.UserLoginInterceptor.isCurrentUserIsAdmin;

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
		if (isCurrentUserIsAdmin()) {
			departmentService.saveDepartment(department);
		}
		return "redirect:/";
	}

}
