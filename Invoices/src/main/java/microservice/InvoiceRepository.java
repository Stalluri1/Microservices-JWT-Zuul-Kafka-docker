package microservice;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice,Long>{
	List<Invoice> findByUserId(Long userId);

}
