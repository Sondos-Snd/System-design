package com.example.redis.repository;

import com.example.redis.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}


//    Iterable<User> users = userRepository.findAll();
// Optional<User> user = userRepository.findById("1");