package com.example.neo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.neo4j.entity.User;
import com.example.neo4j.entity.Order;
import com.example.neo4j.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;


import java.util.Arrays;

@SpringBootApplication
public class Neo4jApplication implements CommandLineRunner {

		private final UserRepository userRepository;

		public Neo4jApplication(UserRepository userRepository) {
			this.userRepository = userRepository;
		}

		public static void main(String[] args) {
			SpringApplication.run(Neo4jApplication.class, args);
		}

		@Override
		public void run(String... args) {
			userRepository.deleteAll(); // Clean all nodes before adding new data

			Order order1 = new Order("1", "Order 1");
			Order order2 = new Order("2", "Order 2");
			Order order3 = new Order("3", "Order 3");

			User user1 = new User("1", "Alice", 30, Arrays.asList(order1, order2));
			User user2 = new User("2", "Bob", 22, null);
			User user3 = new User("3", "Charlie", 35, Arrays.asList(order3));

			userRepository.save(user1);
			userRepository.save(user2);
			userRepository.save(user3);
		}

//	List<User> users = userRepository.findByAgeGreaterThan(25);
//List<User> users = userRepository.findAll();

}