package Sample.Project.repository;
import Sample.Project.model.*;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;

import Sample.Project.payload.*;
public interface ApplicationUserRepository  extends CrudRepository<User,String>  {
	
	 
	 Optional<User> findByUsernameOrEmail(String username, String email);
	 Optional <User> findById(Long id);
	 //@Secured("ROLE_USER")
	 Optional <User> findByUsername(String username);
	 
	 @Query("SELECT r.id FROM User r where r.username = :name") 
	  Long findIdByUsername(@Param("name") String name);

}
