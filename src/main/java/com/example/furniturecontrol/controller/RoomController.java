package com.example.furniturecontrol.controller;


import com.example.furniturecontrol.entity.Room;
import com.example.furniturecontrol.entity.User;
import com.example.furniturecontrol.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/room")
public class RoomController {

    private  final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getRoomByUid(@RequestBody User user){
        return roomService.getRoomsByUid(user.getUid());
    }

//    @PostMapping
//    public void addNewRoom(@RequestBody Room room){
//        roomService.addNewRoom(room);
//
//    }
}
