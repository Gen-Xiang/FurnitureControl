package com.example.furniturecontrol.repository;

import com.example.furniturecontrol.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query("SELECT r FROM Room r WHERE r.uid = ?1")
    List<Room> findRoomByUid(int uid);

    @Query("SELECT r FROM Room r WHERE r.roomname = ?1")
    Optional<Room> findRoomByRoomname(String roomname);

    @Query("SELECT r FROM Room r WHERE r.graph = ?1")
    Optional<Room> findRoomByGraph(String graph);

    @Query("SELECT MAX(r.rid) FROM Room r ")
    int searchMaxRid();
}
