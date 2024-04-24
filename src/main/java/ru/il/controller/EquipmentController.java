package ru.il.controller;

import ru.il.config.UserLoginInterceptor;
import ru.il.model.Equipment;
import ru.il.model.dao.EquipmentDao;
import ru.il.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EquipmentController {

	private final EquipmentService equipmentService;

	@Autowired
	public EquipmentController(EquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}

	@GetMapping("/")
	public String viewHomePage(Model model) {
		UserLoginInterceptor.addIsAdminToForm(model);
		return findPaginated(1, "eqName", "asc", model);
	}
	
	@GetMapping("/showNewEquipmentForm")
	public String showNewEquipmentForm(Model model) {
		UserLoginInterceptor.addIsAdminToForm(model);
		Equipment equipment = new Equipment();
		model.addAttribute("equipment", equipment);
		return "new_equipment";
	}
	
	@PostMapping("/saveEquipment")
	public String saveEmployee(@ModelAttribute("equipment") Equipment equipment) {
		if (UserLoginInterceptor.isCurrentUserIsAdmin()) {
			equipmentService.saveEquipment(equipment);
		}
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		UserLoginInterceptor.addIsAdminToForm(model);
		Equipment equipment = equipmentService.getEquipmentById(id);
		
		model.addAttribute("equipment", equipment);
		return "update_equipment";
	}
	
	@GetMapping("/deleteEquipment/{id}")
	public String deleteEmployee(@PathVariable (value = "id") long id) {
		if (UserLoginInterceptor.isCurrentUserIsAdmin()) {
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
		
		Page<EquipmentDao> page = equipmentService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<EquipmentDao> listEquipments = page.getContent();
		
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
