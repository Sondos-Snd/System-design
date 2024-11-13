package com.example.neo4j.repository;

import com.example.neo4j.entity.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface UserRepository extends Neo4jRepository<User, String> {

    @Query("MATCH (u:User) WHERE u.age > $age RETURN u")
    List<User> findByAgeGreaterThan(int age);
}
