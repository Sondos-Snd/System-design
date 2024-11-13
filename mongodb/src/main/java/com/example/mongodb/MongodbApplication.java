package com.example.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.mongodb.entity.User;
import com.example.mongodb.entity.Order;
import com.example.mongodb.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;


import java.util.Arrays;

@SpringBootApplication
public class MongodbApplication implements CommandLineRunner {

		private final UserRepository userRepository;

		public MongodbApplication(UserRepository userRepository) {
			this.userRepository = userRepository;
		}

		public static void main(String[] args) {
			SpringApplication.run(MongodbApplication.class, args);
		}

		@Override
		public void run(String... args) {
			userRepository.deleteAll();

			User user1 = new User(null, "Alice", 30, Arrays.asList(new Order("1", "Order 1"), new Order("2", "Order 2")));
			User user2 = new User(null, "Bob", 22, null);
			User user3 = new User(null, "Charlie", 35, Arrays.asList(new Order("3", "Order 3")));

			userRepository.save(user1);
			userRepository.save(user2);
			userRepository.save(user3);
		}

//	List<User> users = userRepository.findByAgeGreaterThan(25);
//List<User> users = userRepository.findAll();

}
