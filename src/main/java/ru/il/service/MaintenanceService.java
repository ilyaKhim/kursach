package ru.il.service;

import ru.il.model.Maintenance;
import ru.il.model.dao.MaintenanceDao;

import java.util.List;

public interface MaintenanceService {
    void saveMaintenance(Maintenance maintenance);

    List<MaintenanceDao> findAll();

    Maintenance getMaintenanceById(long id);
}
