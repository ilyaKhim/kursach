package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Maintenance;
import net.javaguides.springboot.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MaintenanceController {

	private final MaintenanceService maintenanceService;

	@Autowired
	public MaintenanceController(MaintenanceService maintenanceService) {
		this.maintenanceService = maintenanceService;
	}
	
	@GetMapping("/showNewMaintenanceForm")
	public String showNewMaintenanceForm(Model model) {
		Maintenance maintenance = new Maintenance();
		model.addAttribute("maintenance", maintenance);
		return "new_maintenance";
	}
	
	@PostMapping("/saveMaintenance")
	public String saveEmployee(@ModelAttribute("maintenance") Maintenance maintenance) {
		maintenanceService.saveMaintenance(maintenance);
		return "redirect:/";
	}

}
