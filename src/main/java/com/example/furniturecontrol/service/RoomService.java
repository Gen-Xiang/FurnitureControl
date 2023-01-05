package com.example.furniturecontrol.service;

import com.example.furniturecontrol.entity.Room;
import com.example.furniturecontrol.entity.User;
import com.example.furniturecontrol.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    public List<Room> getRoomsByUid(int uid){
        List<Room> roomList = roomRepository.findRoomByUid(uid);
        if (!roomList.isEmpty()){
            return roomList;
        }
        else{
            return null;
//            throw new IllegalStateException("no room owned by "+uid);
        }
    }

    public void addNewRoom(Room room) {
        Optional<Room> roomOptional = roomRepository.findRoomByRoomname(room.getRoomname());
        if (roomOptional.isPresent()){
            throw new IllegalStateException("roomname taken");
        }
        roomRepository.save(room);
    }

    public void deleteRoom(int rid){
        boolean exists = roomRepository.existsById(rid);
        if (!exists){
            throw new IllegalStateException("room with id "+ rid + " doesn't exist");
        }
        roomRepository.deleteById(rid);
    }

    @Transactional
    public void updateRoom(int rid,String roomname,String graph){
        Optional<Room> roomOptional = roomRepository.findById(rid);
        Room room;
        if (roomOptional.isPresent()){
            room = roomOptional.get();
        }
        else{
            throw new IllegalStateException("room with id "+ rid + " doesn't exist");
        }

        if (roomname!=null&&roomname.length()>0&&!Objects.equals(room.getRoomname(),roomname)){
            room.setRoomname(roomname);
        }

        if (graph!=null&&graph.length()>0&&!Objects.equals(room.getGraph(),graph)){
            room.setGraph(graph);
        }
    }
}
