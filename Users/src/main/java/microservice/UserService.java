package microservice;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;


@Service
@EnableKafka
public class UserService {
	
	
	@Autowired
	private UsersRespository userRepository;
	
	
	@KafkaListener(topics="users")
	public void receiveMessage(User user , Acknowledgment acknowledgment)
	{
	
		
		addUser(user);
		
	}
	
	public void addUser(User user)
	{
		userRepository.save(user);
	}
	
	/*
	private List<User> users = Arrays.asList(
			new User("1","user1","user1@test.com"),
			new User("2","user2","user2@test.com"),
			new User("3","user3","user3@test.com"),
			new User("4","user4","user4@test.com")
			   
			);*/

	@CacheEvict("users")
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAllUsers();
	}
	
	public Optional<User> getUser(Long id)
	{
		return userRepository.findById(id);
	}

	
	

}
