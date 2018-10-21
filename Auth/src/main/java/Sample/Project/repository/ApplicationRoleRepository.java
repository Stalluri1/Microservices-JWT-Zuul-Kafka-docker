package Sample.Project.repository;


import Sample.Project.model.Role;
import Sample.Project.model.RoleName;
import Sample.Project.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ApplicationRoleRepository extends CrudRepository<Role,Long> {
	
	Role findByName(RoleName roleName);

}
