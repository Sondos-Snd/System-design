package com.example.firebase.entity;

import com.google.cloud.firestore.annotation.DocumentId;

import java.util.List;

public class User {

    @DocumentId
    private String id;
    private String name;
    private int age;
    private List<Order> orders;

    // Default constructor
    public User() {
    }

    // Parameterized constructor
    public User(String id, String name, int age, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.orders = orders;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
