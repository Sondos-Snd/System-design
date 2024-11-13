package com.example.cassandra.entity;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@UserDefinedType("order")
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

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
