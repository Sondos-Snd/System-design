package com.example.dynamodb.repository;

import com.example.dynamodb.entity.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface UserRepository extends CrudRepository<User, String> {

    List<User> findByAgeGreaterThan(int age);
}
