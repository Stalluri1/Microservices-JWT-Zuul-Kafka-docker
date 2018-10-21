package microservice;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@EnableKafka
public class InvoiceService {
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	

	@KafkaListener(topics="orders")
	public void receiveMessage(Order order , Acknowledgment acknowledgment)
	{
		Invoice invoice =  new Invoice ();
		invoice.setUserId(order.getUserId());
		invoice.setDate(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()));
		addInvoice(invoice);
		
	}
	
	public void addInvoice(Invoice invoice)
	{
		invoiceRepository.save(invoice);
	}
	
	public List<Invoice> getAllOrdersByUserId(Long userID)
	{
		return invoiceRepository.findByUserId(userID);
	}
	

}
