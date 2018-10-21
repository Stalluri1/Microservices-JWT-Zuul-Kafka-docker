package microservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;

@RestController
@EnableKafka
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private KafkaTemplate<String,Order> kafkaTemplate;

	
	@PostMapping("/order")
	public Order addOrder(Long userId , String productname ,int price)
	{
		Order order = new Order(userId,productname,price);
		orderService.addOrder(order);
		kafkaTemplate.send("orders",order);
		return order;		
	}
	
	@GetMapping("/orders")
	public List<Order> getAllOrdersByUserID( Long userId)
	{
		return orderService.getAllOrdersByUserId(userId);
	}

}
