package com.example.furniturecontrol.controller;


import com.example.furniturecontrol.entity.Room;
import com.example.furniturecontrol.entity.User;
import com.example.furniturecontrol.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/room")
public class RoomController {
    private final RoomService roomService;
    private final String pictureURL="C:\\Users\\38085\\Documents\\GitHub\\FurnitureControl\\vueFurniture\\src\\assets\\";

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public List<Room> getRoomByUid(@RequestBody User user){
//        System.out.println("The max rid is "+roomService.searchMaxRid());
        return roomService.getRoomsByUid(user.getUid());
    }

    @PostMapping("/getroom")
    public Room getRoomByRid(@RequestBody Room room){
        return roomService.getRoomByRid(room.getRid());
    }

    @PostMapping(path = "/addroom/{uid}/{roomname}")
    public Room addNewRoom(@PathVariable("uid") int uid,@PathVariable("roomname") String roomname,@RequestBody MultipartFile file){
//        roomService.addNewRoom(room);
        String fileName = file.getOriginalFilename();
        File saveFile = new File(pictureURL);

        UUID uuid = UUID.randomUUID();
//        int next_rid = roomService.searchMaxRid()+1;
//        System.out.println("next_rid = "+next_rid);

        int index =fileName.indexOf(".");
        String newFileName = uuid+fileName.substring(index);

        Room room = new Room(uid,roomname,newFileName);
        System.out.println(room);
        roomService.addNewRoom(room);

        try {
            //将文件保存指定目录
            file.transferTo(new File(pictureURL + newFileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Save success");


        Room ret_room = roomService.getRoomByGraph(newFileName);
        System.out.println(ret_room);
        return ret_room;
    }

    @DeleteMapping(path="{roomId}")
    public void deleteRoom(@PathVariable("roomId") int rid){
        roomService.deleteRoom(rid);
    }
}
