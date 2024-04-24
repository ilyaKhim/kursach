package net.javaguides.springboot.controller;

import net.javaguides.springboot.config.UserLoginInterceptor;
import net.javaguides.springboot.model.Equipment;
import net.javaguides.springboot.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static net.javaguides.springboot.config.UserLoginInterceptor.addIsAdminToForm;
import static net.javaguides.springboot.config.UserLoginInterceptor.isCurrentUserIsAdmin;

@Controller
public class EquipmentController {

	private final EquipmentService equipmentService;

	@Autowired
	public EquipmentController(EquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}

	@GetMapping("/")
	public String viewHomePage(Model model) {
		addIsAdminToForm(model);
		return findPaginated(1, "eqName", "asc", model);
	}
	
	@GetMapping("/showNewEquipmentForm")
	public String showNewEquipmentForm(Model model) {
		addIsAdminToForm(model);
		Equipment equipment = new Equipment();
		model.addAttribute("equipment", equipment);
		return "new_equipment";
	}
	
	@PostMapping("/saveEquipment")
	public String saveEmployee(@ModelAttribute("equipment") Equipment equipment) {
		if (isCurrentUserIsAdmin()) {
			equipmentService.saveEquipment(equipment);
		}
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		addIsAdminToForm(model);
		Equipment equipment = equipmentService.getEquipmentById(id);
		
		model.addAttribute("equipment", equipment);
		return "update_equipment";
	}
	
	@GetMapping("/deleteEquipment/{id}")
	public String deleteEmployee(@PathVariable (value = "id") long id) {
		if (isCurrentUserIsAdmin()) {
			this.equipmentService.deleteEquipmentById(id);
		}
		return "redirect:/";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Equipment> page = equipmentService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Equipment> listEquipments = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listEquipments", listEquipments);
		return "index";
	}
}
