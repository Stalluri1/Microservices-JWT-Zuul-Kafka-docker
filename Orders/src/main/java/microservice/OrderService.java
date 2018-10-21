package microservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	public void addOrder(Order order) {
		// TODO Auto-generated method stub
		orderRepository.save(order);
		
		
	}
	
	public List<Order> getAllOrdersByUserId(Long userID)
	{
		return orderRepository.findByUserId(userID);
	}
}
