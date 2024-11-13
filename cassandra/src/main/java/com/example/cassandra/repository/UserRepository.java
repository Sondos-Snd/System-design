package com.example.cassandra.repository;

import com.example.cassandra.entity.User;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;

public interface UserRepository extends CassandraRepository<User, String> {
    List<User> findByAgeGreaterThan(int age);
}
