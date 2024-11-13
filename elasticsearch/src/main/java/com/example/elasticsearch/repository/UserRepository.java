package com.example.elasticsearch.repository;

import com.example.demo.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.annotations.Query;

import java.util.List;

public interface UserRepository extends ElasticsearchRepository<User, String> {

    @Query("{\"bool\": {\"must\": [{\"range\": {\"age\": {\"gt\": ?0}}}]}}")
    List<User> findByAgeGreaterThan(int age);
}
