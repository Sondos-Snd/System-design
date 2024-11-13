package com.example.mongodb.repository;

import com.example.mongodb.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByAgeGreaterThan(int age);
}
