package ru.il.controller;

import ru.il.config.UserLoginInterceptor;
import ru.il.model.Equipment;
import ru.il.model.Maintenance;
import ru.il.model.Supplier;
import ru.il.model.dao.MaintenanceDao;
import ru.il.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static ru.il.config.UserLoginInterceptor.addIsAdminToForm;
import static ru.il.config.UserLoginInterceptor.isCurrentUserIsAdmin;

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

	@GetMapping("/showAllMaintenances")
	public String showAllMaintenances(Model model) {
		if (isCurrentUserIsAdmin()) {
			addIsAdminToForm(model);
			List<MaintenanceDao> maintenances = maintenanceService.findAll();
			model.addAttribute("maintenances", maintenances);
			return "view_maintenances";
		} else return "redirect:/";
	}

}
