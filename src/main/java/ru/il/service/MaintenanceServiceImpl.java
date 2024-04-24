package ru.il.service;

import ru.il.model.Maintenance;
import ru.il.repository.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;

    @Autowired
    public MaintenanceServiceImpl(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    @Override
    public void saveMaintenance(Maintenance maintenance) {
        this.maintenanceRepository.save(maintenance);
    }
}
