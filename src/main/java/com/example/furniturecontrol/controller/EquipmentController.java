package com.example.furniturecontrol.controller;


import com.example.furniturecontrol.entity.Equipment;
import com.example.furniturecontrol.entity.Room;
import com.example.furniturecontrol.entity.User;
import com.example.furniturecontrol.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/equipment")
public class EquipmentController {
    private final EquipmentService equipmentService;

    @Autowired
    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @PostMapping
    public List<Equipment> getEquipmentByUid(@RequestBody User user){
        return equipmentService.getEquipmentByUid(user.getUid());
    }

    @PostMapping(path = "/room")
    public List<Equipment> getEquipmentByRid(@RequestBody Room room){
        return equipmentService.getEquipmentByRid(room.getRid());
    }

    @PostMapping(path = "/add")
    public void addNewEquipment(@RequestBody Equipment equipment){
        equipmentService.addNewEquipment(equipment);
    }
}
