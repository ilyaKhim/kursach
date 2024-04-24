package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Equipment;
import net.javaguides.springboot.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long>{

}
