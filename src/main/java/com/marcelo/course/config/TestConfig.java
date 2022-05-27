package com.marcelo.course.config;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.marcelo.course.entities.Category;
import com.marcelo.course.entities.Order;
import com.marcelo.course.entities.User;
import com.marcelo.course.entities.enums.OrderStatus;
import com.marcelo.course.repositories.CategoryRepository;
import com.marcelo.course.repositories.OrderRepository;
import com.marcelo.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");

		List<Category> list3 = new ArrayList<>();
		list3.add(cat1);
		list3.add(cat2);
		list3.add(cat3);

		categoryRepository.saveAll(list3);

		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);

		List<User> list = new ArrayList<>();
		list.add(u1);
		list.add(u2);

		List<Order> list2 = new ArrayList<>();
		list2.add(o1);
		list2.add(o2);
		list2.add(o3);

		userRepository.saveAll(list);
		orderRepository.saveAll(list2);

	}

}
