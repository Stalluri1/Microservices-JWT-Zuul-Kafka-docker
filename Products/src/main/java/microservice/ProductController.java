package microservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import microservice.Product;
import microservice.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public List<Product> getAllProducts()
	{
		return productService.getAllProducts();
		
	}
	
	@GetMapping("/product")
	public Product getProduct(@RequestParam("productid") String productid)
	{
		return productService.getProduct(productid);
	}
		

}
