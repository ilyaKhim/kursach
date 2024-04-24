package net.javaguides.springboot.service;

import java.util.List;

import net.javaguides.springboot.model.Equipment;
import org.springframework.data.domain.Page;

public interface EquipmentService {
	List<Equipment> getAllEmployees();
	void saveEquipment(Equipment equipment);
	Equipment getEquipmentById(long id);
	void deleteEquipmentById(long id);
	Page<Equipment> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
