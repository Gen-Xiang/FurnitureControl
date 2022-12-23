package com.example.furniturecontrol.entity;

import jakarta.persistence.*;

@Entity
@Table
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private int uid;
    private String username;
    private String password;
    private String email;
    private String introduction;

    public User() {
    }

    public User(int uid, String username, String password, String email, String introduction) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.introduction = introduction;
    }

    public User(String username, String password, String email, String introduction) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.introduction = introduction;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
