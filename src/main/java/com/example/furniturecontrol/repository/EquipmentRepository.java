package com.example.furniturecontrol.repository;

import com.example.furniturecontrol.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

    @Query("SELECT e FROM Equipment e WHERE e.uid = ?1")
    List<Equipment> findEquipmentByUid(int uid);

    @Query("SELECT e FROM Equipment e WHERE e.rid = ?1")
    List<Equipment> findEquipmentByRid(int rid);

    @Query("SELECT e FROM Equipment e WHERE e.equipname = ?1")
    Optional<Equipment> findEquipmentByEquipname(String equipname);
}
