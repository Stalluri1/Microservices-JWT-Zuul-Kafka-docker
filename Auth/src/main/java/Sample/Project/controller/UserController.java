package Sample.Project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Sample.Project.model.User;
import Sample.Project.repository.ApplicationUserRepository;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	ApplicationUserRepository applicationUserRepository;
	
	@GetMapping(path="/users/{username}",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    public Object getUserProfile(@PathVariable(value = "username") String username) {
        Optional<User> user = applicationUserRepository.findByUsername(username);
        
        return user;
        
	}
                

}
