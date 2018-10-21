package microservice;


import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import microservice.Product;


@Service
public class ProductService {
	
	private List<Product> products = Arrays.asList(
			new Product("1","category1","product1"),
			new Product("2","category2","product2"),
			new Product("3","category3","product3"),
			new Product("4","category4","product4")
			   
			);

	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return products;
	}
	
	public Product getProduct(String id)
	{
		return products.stream().filter(t->t.getId().equals(id)).findFirst().get();
	}


}

