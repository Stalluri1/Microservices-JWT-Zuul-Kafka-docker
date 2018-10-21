package Sample.Project.controller;
import Sample.Project.repository.*;
import Sample.Project.security.JwtTokenProvider;
import Sample.Project.model.Role;
import Sample.Project.model.RoleName;
import Sample.Project.model.User;
import Sample.Project.payload.*;

import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@EnableKafka
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private KafkaTemplate<String,User> kafkaTemplate;
	
	@Autowired
	ApplicationUserRepository applicationUserRepository;
	
	@Autowired
	ApplicationRoleRepository roleRepository;
	
	@Autowired
    AuthenticationManager authenticationManager;
	
	@Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;
	
	//private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    
  
    
    @PostMapping(path="/validatetoken")
    public boolean validateToken(@Valid @RequestBody Token requestToken)
    {
    	System.out.println("got trequest ->" + requestToken.getToken());
    	return StringUtils.hasText(requestToken.getToken())&& tokenProvider.validateToken(requestToken.getToken());
    	
    }
    
    
    @PostMapping(path="/signin",produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );
        System.out.println(loginRequest.getUsernameOrEmail() + loginRequest.getPassword());
        //SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        System.out.println("printng-> "+jwt);
        return new JwtAuthenticationResponse(jwt);
        //return new ResponseEntity<Object>(new JwtAuthenticationResponse(jwt), HttpStatus.OK);
        
        //return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }
	    
	
	
	@PostMapping("/sign-up")
	@Transactional
    public void signUp(@RequestBody SignUpRequest signUpRequest) {
		//signUpRequest.setPassword(bCryptPasswordEncoder.encode(signUpRequest.getPassword()));
		System.out.println(signUpRequest.getEmail() + signUpRequest.getUsername());
        //applicationUserRepository.save(signUpRequest);
        System.out.println(applicationUserRepository.count());
    }
	
	@PostMapping("/users/signup")
    public void registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //Role userRole = roleRepository.findByName(RoleName.ROLE_CHECK);
        
        //user.setRoles(Collections.singleton(userRole));
       
        User result = applicationUserRepository.save(user);
        
        user.setId(applicationUserRepository.findIdByUsername(signUpRequest.getUsername()));
        System.out.println("id--> "+applicationUserRepository.findIdByUsername(signUpRequest.getUsername()));
        kafkaTemplate.send("users",user);

	}

}
