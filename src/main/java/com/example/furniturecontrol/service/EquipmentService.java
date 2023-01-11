package com.example.furniturecontrol.service;

import com.example.furniturecontrol.entity.Equipment;
import com.example.furniturecontrol.repository.EquipmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public List<Equipment> getAllEquipments(){
        return equipmentRepository.findAll();
    }

    public Equipment getEquipmentByEid(int eid){
        Optional<Equipment> equipmentOptional = equipmentRepository.findEquipmentByEid(eid);
        if (equipmentOptional.isPresent()){
            return equipmentOptional.get();
        }
        else{
            return null;
        }
    }

    public List<Equipment> getEquipmentByUid(int uid){
        List<Equipment> equipmentList = equipmentRepository.findEquipmentByUid(uid);
        if (!equipmentList.isEmpty()){
            return equipmentList;
        }
        else{
            return null;
        }
    }

    public List<Equipment> getEquipmentByRid(int rid){
        List<Equipment> equipmentList = equipmentRepository.findEquipmentByRid(rid);
        if (!equipmentList.isEmpty()){
            return equipmentList;
        }
        else{
            return null;
        }
    }

    public Equipment getEquipmentByEquipname(String equipname){
        Optional<Equipment> equipmentOptional = equipmentRepository.findEquipmentByEquipname(equipname);
        if (equipmentOptional.isPresent()){
            return equipmentOptional.get();
        }
        else{
            return null;
        }
    }

    public void addNewEquipment(Equipment equipment){
        Optional<Equipment> equipmentOptional = equipmentRepository.findEquipmentByEquipname(equipment.getEquipname());
        if (equipmentOptional.isPresent()){
            throw new IllegalStateException("equipname taken");
        }
        equipmentRepository.save(equipment);
    }

    public void deleteEquipment(int eid){
        boolean exists = equipmentRepository.existsById(eid);
        if (!exists){
            throw new IllegalStateException("equipment with id "+ eid + " doesn't exist");
        }
        equipmentRepository.deleteById(eid);
    }

    @Transactional
    public void updateEquipment(int eid,String equipname,int type, boolean status, int temperature, int humidity, int luminance, int x, int y){
        Optional<Equipment> equipmentOptional = equipmentRepository.findById(eid);
        Equipment equipment;
        if (equipmentOptional.isPresent()){
            equipment = equipmentOptional.get();
        }
        else {
            throw new IllegalStateException("equipment with id "+ eid + " doesn't exist");
        }

        if (equipname!=null&&equipname.length()>0&&!Objects.equals(equipment.getEquipname(),equipname)){
            equipment.setEquipname(equipname);
        }

        if (!Objects.equals(equipment.getType(),type)){
            equipment.setType(type);
        }

        if (!Objects.equals(equipment.getStatus(),status)){
            equipment.setStatus(status);
        }

        if (!Objects.equals(equipment.getTemperature(),temperature)){
            equipment.setTemperature(temperature);
        }

        if (!Objects.equals(equipment.getHumidity(),humidity)){
            equipment.setHumidity(humidity);
        }

        if (!Objects.equals(equipment.getLuminance(),luminance)){
            equipment.setLuminance(luminance);
        }

        if (!Objects.equals(equipment.getX(),x)){
            equipment.setX(x);
        }

        if (!Objects.equals(equipment.getY(),y)){
            equipment.setY(y);
        }
    }
}
