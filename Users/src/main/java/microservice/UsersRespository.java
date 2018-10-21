package microservice;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRespository extends CrudRepository<User,Long>{
	Optional<User> findById(Long userId);
	@Query("select u from User u")
	List<User> findAllUsers();

}
