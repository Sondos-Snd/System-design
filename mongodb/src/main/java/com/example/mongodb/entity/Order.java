package com.example.mongodb.entity;

public class Order {
    private String id;
    private String description;

    // Default constructor
    public Order() {
    }

    // Parameterized constructor
    public Order(String id, String description) {
        this.id = id;
        this.description = description;
    }

    // Getter and Setter for 'id'
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getter and Setter for 'description'
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
