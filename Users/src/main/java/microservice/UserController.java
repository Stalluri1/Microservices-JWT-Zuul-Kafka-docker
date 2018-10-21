package microservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;





@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	

	
	@GetMapping("/users")
	public List<User> getAllUsers()
	{
		return userService.getAllUsers();
		
	}
	
	@GetMapping("/user")
	public Optional<User> getUser(@RequestParam("userid") Long userid)
	{
		return userService.getUser(userid);
		
	}
	
	

}
