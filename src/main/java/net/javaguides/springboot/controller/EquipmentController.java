package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Equipment;
import net.javaguides.springboot.service.EquipmentService;
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

	// display list of employees
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "eqName", "asc", model);
	}
	
	@GetMapping("/showNewEquipmentForm")
	public String showNewEquipmentForm(Model model) {
		// create model attribute to bind form data
		Equipment equipment = new Equipment();
		model.addAttribute("equipment", equipment);
		return "new_equipment";
	}
	
	@PostMapping("/saveEquipment")
	public String saveEmployee(@ModelAttribute("equipment") Equipment equipment) {
		// save equipment to database
		equipmentService.saveEquipment(equipment);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get equipment from the service
		Equipment equipment = equipmentService.getEquipmentById(id);
		
		// set equipment as a model attribute to pre-populate the form
		model.addAttribute("equipment", equipment);
		return "update_equipment";
	}
	
	@GetMapping("/deleteEquipment/{id}")
	public String deleteEmployee(@PathVariable (value = "id") long id) {
		
		// call delete equipment method
		this.equipmentService.deleteEquipmentById(id);
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
