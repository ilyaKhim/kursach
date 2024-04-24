package net.javaguides.springboot.repository;


import net.javaguides.springboot.model.MaintenanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRepository extends JpaRepository<MaintenanceEntity, Long> {

}
