package ru.il.controller;

import ru.il.model.Department;
import ru.il.model.Supplier;
import ru.il.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.il.service.SupplierServiceImpl;

import java.util.List;

import static ru.il.config.UserLoginInterceptor.addIsAdminToForm;
import static ru.il.config.UserLoginInterceptor.isCurrentUserIsAdmin;

@Controller
public class SupplierController {

	private final SupplierService supplierService;

	@Autowired
	public SupplierController(SupplierService supplierService) {
		this.supplierService = supplierService;
	}
	
	@GetMapping("/showNewSupplierForm")
	public String showNewEquipmentForm(Model model) {
		addIsAdminToForm(model);
		Supplier supplier = new Supplier();
		model.addAttribute("supplier", supplier);
		return "new_supplier";
	}
	
	@PostMapping("/saveSupplier")
	public String saveEmployee(@ModelAttribute("supplier") Supplier supplier) {
		if (isCurrentUserIsAdmin()) {
			supplierService.saveSupplier(supplier);
		}
		return "redirect:/";
	}

	@GetMapping("/showAllSuppliers")
	public String showAllDepartments(Model model) {
		if (isCurrentUserIsAdmin()) {
			addIsAdminToForm(model);
			List<Supplier> suppliers = supplierService.findAll();
			model.addAttribute("suppliers", suppliers);
			return "view_suppliers";
		} else return "redirect:/";
	}

}
