package ru.il.service;

import ru.il.model.Maintenance;
import ru.il.model.dao.MaintenanceDao;
import ru.il.repository.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;

    @Autowired
    public MaintenanceServiceImpl(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    @Override
    public void saveMaintenance(Maintenance maintenance) {
//        this.maintenanceRepository.save(maintenance);
    }

    @Override
    public List<MaintenanceDao> findAll() {
        return maintenanceRepository.findAll();
    }
}