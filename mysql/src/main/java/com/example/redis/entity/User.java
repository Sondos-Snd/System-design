package com.example.mysql.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

@RedisHash("User")
public class User implements Serializable {
    @Id
    private String id;

    private String name;
    private int age;

    private List<Order> orders;

    // Getters and Setters
}
