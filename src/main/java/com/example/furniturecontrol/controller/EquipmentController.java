package com.example.furniturecontrol.controller;


import com.example.furniturecontrol.entity.Equipment;
import com.example.furniturecontrol.entity.Room;
import com.example.furniturecontrol.entity.User;
import com.example.furniturecontrol.result.Result;
import com.example.furniturecontrol.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

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
    public Equipment addNewEquipment(@RequestBody Equipment equipment){
        equipmentService.addNewEquipment(equipment);
        Equipment ret_equipment = equipmentService.getEquipmentByEquipname(equipment.getEquipname());
        if (ret_equipment.getType()==1){
            equipmentService.updateEquipment(ret_equipment.getEid(),ret_equipment.getEquipname(),ret_equipment.getType(),ret_equipment.getStatus(),ret_equipment.getTemperature(),ret_equipment.getHumidity(),100,ret_equipment.getX(),ret_equipment.getY());
        }
        else if (ret_equipment.getType()==3){
            Random r = new Random();
            int t = r.nextInt(66)-20;
            int h = r.nextInt(101);
            equipmentService.updateEquipment(ret_equipment.getEid(),ret_equipment.getEquipname(),ret_equipment.getType(),ret_equipment.getStatus(),t,h, ret_equipment.getLuminance(), ret_equipment.getX(),ret_equipment.getY());
        }
        return ret_equipment;
    }

    @DeleteMapping(path="{equipId}")
    public void deleteUser(@PathVariable("equipId") int eid){
        equipmentService.deleteEquipment(eid);
    }

    @PutMapping(path="/status/{equipmentId}")
    public Result updateEquipmentByStatus(@PathVariable("equipmentId")int eid,@RequestBody Equipment equipment){
        Equipment ret_equipment = equipmentService.getEquipmentByEid(eid);
        equipmentService.updateEquipment(ret_equipment.getEid(),ret_equipment.getEquipname(),ret_equipment.getType(),equipment.getStatus(),ret_equipment.getTemperature(),ret_equipment.getHumidity(),ret_equipment.getLuminance(),ret_equipment.getX(),ret_equipment.getY());
        return new Result(200);
    }

    @PutMapping(path="/luminance/{equipmentId}")
    public Result updateEquipmentByLuminance(@PathVariable("equipmentId")int eid,@RequestBody Equipment equipment){
        Equipment ret_equipment = equipmentService.getEquipmentByEid(eid);
        equipmentService.updateEquipment(ret_equipment.getEid(),ret_equipment.getEquipname(),ret_equipment.getType(),ret_equipment.getStatus(),ret_equipment.getTemperature(),ret_equipment.getHumidity(),equipment.getLuminance(),ret_equipment.getX(),ret_equipment.getY());
        return new Result(200);
    }

    @PutMapping(path="/xy/{equipmentId}")
    public Result updateEquipmentByXY(@PathVariable("equipmentId")int eid,@RequestBody Equipment equipment){
        Equipment ret_equipment = equipmentService.getEquipmentByEid(eid);
        equipmentService.updateEquipment(ret_equipment.getEid(),ret_equipment.getEquipname(),ret_equipment.getType(),ret_equipment.getStatus(),ret_equipment.getTemperature(),ret_equipment.getHumidity(),ret_equipment.getLuminance(),equipment.getX(),equipment.getY());
        return new Result(200);
    }
}
