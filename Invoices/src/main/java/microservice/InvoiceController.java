package microservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvoiceController {
	
	@Autowired
	private InvoiceService invoiceService;
	
	
	
	@GetMapping("/invoices")
	public List<Invoice> getAllOrdersByUserID(Long userId)
	{
		return invoiceService.getAllOrdersByUserId(userId);
	}
	

}
