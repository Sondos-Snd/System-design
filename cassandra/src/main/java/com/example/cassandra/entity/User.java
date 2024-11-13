package com.example.cassandra.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.CassandraType;

import java.util.List;

@Table("users")
public class User {

    @Id
    private String id;

    @Column("name")
    private String name;

    @Column("age")
    private int age;

    @CassandraType(type = CassandraType.Name.LIST, typeArguments = CassandraType.Name.UDT, userTypeName = "order")
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
