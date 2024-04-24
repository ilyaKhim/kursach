package ru.il.service;

import java.util.List;

import ru.il.model.Equipment;
import org.springframework.data.domain.Page;
import ru.il.model.dao.EquipmentDao;

public interface EquipmentService {
	void saveEquipment(Equipment equipment);
	Equipment getEquipmentById(long id);
	void deleteEquipmentById(long id);
	Page<EquipmentDao> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
