package ru.il.service;

import java.util.List;
import java.util.Optional;

import ru.il.model.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ru.il.repository.EquipmentRepository;

@Service
public class EquipmentServiceImpl implements EquipmentService {

	@Autowired
	private EquipmentRepository equipmentRepository;

	@Override
	public List<Equipment> getAllEmployees() {
		return equipmentRepository.findAll();
	}

	@Override
	public void saveEquipment(Equipment equipment) {
		this.equipmentRepository.save(equipment);
	}

	@Override
	public Equipment getEquipmentById(long id) {
		Optional<Equipment> optional = equipmentRepository.findById(id);
		Equipment equipment;
		if (optional.isPresent()) {
			equipment = optional.get();
		} else {
			throw new RuntimeException(" Equipment not found for id :: " + id);
		}
		return equipment;
	}

	@Override
	public void deleteEquipmentById(long id) {
		this.equipmentRepository.deleteById(id);
	}

	@Override
	public Page<Equipment> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.equipmentRepository.findAll(pageable);
	}
}
