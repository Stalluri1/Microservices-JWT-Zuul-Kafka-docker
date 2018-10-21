package microservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class ProdutsApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ProdutsApplication.class, args);
	}
}
