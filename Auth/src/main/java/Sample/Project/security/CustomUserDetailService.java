package Sample.Project.security;




import Sample.Project.repository.*;
import Sample.Project.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomUserDetailService implements UserDetailsService {
	
	 @Autowired
	    ApplicationUserRepository userRepository;

	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		 
		        // Let people login with either username or email
		        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(()->new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail));

		        return UserPrincipal.create(user);
	}

	
	 @Transactional
	    public UserDetails loadUserById(Long id) {
	        User user = userRepository.findById(id).orElseThrow(
	            () -> new UsernameNotFoundException("User not found with id : " + id)
	        );

	        return UserPrincipal.create(user);
	    }
}
