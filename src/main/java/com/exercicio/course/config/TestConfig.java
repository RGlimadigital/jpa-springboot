package com.exercicio.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.exercicio.course.entities.Order;
import com.exercicio.course.entities.User;
import com.exercicio.course.entities.enums.OrderStatus;
import com.exercicio.course.repositories.OrderRepository;
import com.exercicio.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void run(String... args) throws Exception {
		
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456"); 
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		User u3 = new User(null, "Rodrigo Guedes", "rodrigoguedes@mail.com","+5562991735771", "1234567");
		User u4 = new User(null, "Angela Frison", "angelaafrison@hotmail.com","+55629934167169", "1234567");
		
		//Orders - data formato ISO8601
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"),OrderStatus.WAITTING_PAYMENT, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:53:07Z"),OrderStatus.PAID, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T19:53:07Z"),OrderStatus.SHIPPED, u1);
		
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3, u4));
		
		
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
	} 

}
