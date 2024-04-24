package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Maintenance;
import net.javaguides.springboot.repository.MaintenanceRepository;
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
