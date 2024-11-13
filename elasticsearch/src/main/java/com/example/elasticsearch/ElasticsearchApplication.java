package com.example.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.elasticsearch.entity.User;
import com.example.elasticsearch.entity.Order;
import com.example.elasticsearch.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;


import java.util.Arrays;

@SpringBootApplication
public class ElasticsearchApplication implements CommandLineRunner {

		private final UserRepository userRepository;

		public ElasticsearchApplication(UserRepository userRepository) {
			this.userRepository = userRepository;
		}

		public static void main(String[] args) {
			SpringApplication.run(ElasticsearchApplication.class, args);
		}

		@Override
		public void run(String... args) {
			userRepository.deleteAll(); // Clean the table before adding data

			User user1 = new User("1", "Alice", 30, Arrays.asList(new Order("1", "Order 1"), new Order("2", "Order 2")));
			User user2 = new User("2", "Bob", 22, null);
			User user3 = new User("3", "Charlie", 35, Arrays.asList(new Order("3", "Order 3")));

			userRepository.save(user1);
			userRepository.save(user2);
			userRepository.save(user3);
		}

//	List<User> users = userRepository.findByAgeGreaterThan(25);
//List<User> users = userRepository.findAll();

}
