package com.example.furniturecontrol.entity;


import jakarta.persistence.*;

@Entity
@Table
public class Equipment {
    @Id
    @SequenceGenerator(
            name = "equipment_sequence",
            sequenceName = "equipment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "equipment_sequence"
    )
    private int eid;
    private int uid;
    private int rid;
    private String equipname;
    private int type;
    private boolean status;
    private int temperature;
    private int humidity;
    private int luminance;
    private int x;
    private int y;

    public Equipment() {
    }

    public Equipment(int eid, int uid, int rid, String equipname, int type, boolean status, int temperature, int humidity, int luminance, int x, int y) {
        this.eid = eid;
        this.uid = uid;
        this.rid = rid;
        this.equipname = equipname;
        this.type = type;
        this.status = status;
        this.temperature = temperature;
        this.humidity = humidity;
        this.luminance = luminance;
        this.x = x;
        this.y = y;
    }

    public Equipment(int uid, int rid, String equipname, int type) {
        this.uid = uid;
        this.rid = rid;
        this.equipname = equipname;
        this.type = type;
    }

    public Equipment(int uid, int rid, String equipname, int type, boolean status) {
        this.uid = uid;
        this.rid = rid;
        this.equipname = equipname;
        this.type = type;
        this.status = status;
    }

    public Equipment(int uid, int rid, String equipname, int type, boolean status, int luminance) {
        this.uid = uid;
        this.rid = rid;
        this.equipname = equipname;
        this.type = type;
        this.status = status;
        this.luminance = luminance;
    }

    public Equipment(int uid, int rid, String equipname, int type, boolean status, int humidity, int luminance) {
        this.uid = uid;
        this.rid = rid;
        this.equipname = equipname;
        this.type = type;
        this.status = status;
        this.humidity = humidity;
        this.luminance = luminance;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getEquipname() {
        return equipname;
    }

    public void setEquipname(String equipname) {
        this.equipname = equipname;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getLuminance() {
        return luminance;
    }

    public void setLuminance(int luminance) {
        this.luminance = luminance;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "eid=" + eid +
                ", uid=" + uid +
                ", rid=" + rid +
                ", equipname='" + equipname + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", luminance=" + luminance +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
