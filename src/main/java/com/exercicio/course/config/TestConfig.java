package com.exercicio.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.exercicio.course.entities.Category;
import com.exercicio.course.entities.Order;
import com.exercicio.course.entities.OrderItem;
import com.exercicio.course.entities.Payment;
import com.exercicio.course.entities.Post;
import com.exercicio.course.entities.Product;
import com.exercicio.course.entities.User;
import com.exercicio.course.entities.enums.OrderStatus;
import com.exercicio.course.repositories.CategoryRepository;
import com.exercicio.course.repositories.OrderItemRepository;
import com.exercicio.course.repositories.OrderRepository;
import com.exercicio.course.repositories.PostRepository;
import com.exercicio.course.repositories.ProductRepository;
import com.exercicio.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public void run(String... args) throws Exception {
		
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456"); 
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		User u3 = new User(null, "Rodrigo Guedes", "rodrigoguedes@mail.com","+5562991735771", "1234567");
		User u4 = new User(null, "Angela Frison", "angelaafrison@hotmail.com","+55629934167169", "1234567");
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3, u4));
		
		//Orders - data formato ISO8601
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"),OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:53:07Z"),OrderStatus.PAID, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T19:53:07Z"),OrderStatus.SHIPPED, u1);
		
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
		//Instanciando as Categorias:
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		//Instanciando os Produtos:
		Product prod1 = new Product(null, "The Lord of the Rings", "Livro Capa dura edicao especial", 132.6, "");
		Product prod2 = new Product(null, "Smart Tv LG", "Conexao WiFi , aplicativos e Som 5.1", 4999.9, "");
		Product prod3 = new Product(null, "MacBook Pro", "16Gb de RAM , processador Intel i9", 19990.00, "");
		Product prod4 = new Product(null, "PC Gamer", "Intel i9 , 16Gb de Ram e 1Tb de disco duro", 9999.9, "");
		productRepository.saveAll(Arrays.asList(prod1, prod2, prod3, prod4));
		
		//Isntanciando Posts:
		Post post1 = new Post(null, "Teste", "Teste", Instant.parse("2019-07-22T19:53:07Z"), u1);
		Post post2 = new Post(null, "Teste", "Teste", Instant.parse("2019-07-22T19:53:07Z"), u2);
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		//Relacionando os produtos e as categorias
		prod1.getCategories().add(cat2);
		prod2.getCategories().add(cat1);
		prod2.getCategories().add(cat3);
		prod4.getCategories().add(cat3);
		prod4.getCategories().add(cat2);
		prod3.getCategories().add(cat3);
		
		//Salvando as Relacoes - ocorre quando salvamos uma vez mais os produtos no banco de dados.
		productRepository.saveAll(Arrays.asList(prod1, prod2, prod3, prod4));
		
		
		//Seed do OrderItem.
		OrderItem oi1 = new OrderItem(o1, prod1, 2, prod1.getPrice());
		OrderItem oi2 = new OrderItem(o1, prod3, 1, prod3.getPrice());
		OrderItem oi3 = new OrderItem(o2, prod3, 2, prod3.getPrice());
		OrderItem oi4 = new OrderItem(o3, prod4, 3, prod4.getPrice());
		
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
		
		Payment pay1 = new Payment(null, Instant.parse("2019-07-21T21:53:07Z"), o2);
		Payment pay2 = new Payment(null, Instant.parse("2019-07-21T21:53:07Z"), o1);
		
		o2.setPayment(pay1);
		o1.setPayment(pay2);
		
		orderRepository.save(o2);
		orderRepository.save(o1);
		
		
		
//		System.out.println(oi1.totalValue());
		
		
		
	} 

}

//----- Links de Suporte ------
// -> http://localhost:8080/users
// -> http://localhost:8080/h2-console
