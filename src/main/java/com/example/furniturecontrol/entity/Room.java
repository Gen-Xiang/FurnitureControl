package com.example.furniturecontrol.entity;

import jakarta.persistence.*;

@Entity
@Table
public class Room {
    @Id
    @SequenceGenerator(
            name = "room_sequence",
            sequenceName = "room_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "room_sequence"
    )
    private int rid;
    private int uid;
    private String roomname;
    private String graph;

    public Room() {
    }

    public Room(int rid, int uid, String roomname, String graph) {
        this.rid = rid;
        this.uid = uid;
        this.roomname = roomname;
        this.graph = graph;
    }

    public Room(int uid, String roomname, String graph) {
        this.uid = uid;
        this.roomname = roomname;
        this.graph = graph;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public String getGraph() {
        return graph;
    }

    public void setGraph(String graph) {
        this.graph = graph;
    }

    @Override
    public String toString() {
        return "Room{" +
                "rid=" + rid +
                ", uid=" + uid +
                ", roomname='" + roomname + '\'' +
                ", graph='" + graph + '\'' +
                '}';
    }
}
