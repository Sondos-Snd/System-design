package com.example.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.redis.entity.User;
import com.example.redis.entity.Order;
import com.example.redis.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;


import java.util.Arrays;

@SpringBootApplication
public class RedisApplication implements CommandLineRunner {

	private final UserRepository userRepository;

	public RedisApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}

	@Override
	public void run(String... args) {
		User user1 = new User();
		user1.setId("1");
		user1.setName("Alice");
		user1.setAge(30);
		user1.setOrders(Arrays.asList(new Order("1", "Order 1"), new Order("2", "Order 2")));

		User user2 = new User();
		user2.setId("2");
		user2.setName("Bob");
		user2.setAge(22);

		User user3 = new User();
		user3.setId("3");
		user3.setName("Charlie");
		user3.setAge(35);
		user3.setOrders(Arrays.asList(new Order("3", "Order 3")));

		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
	}

}
