package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Equipment;
import net.javaguides.springboot.model.Supplier;
import net.javaguides.springboot.service.EquipmentService;
import net.javaguides.springboot.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static net.javaguides.springboot.config.UserLoginInterceptor.addIsAdminToForm;
import static net.javaguides.springboot.config.UserLoginInterceptor.isCurrentUserIsAdmin;

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

}
