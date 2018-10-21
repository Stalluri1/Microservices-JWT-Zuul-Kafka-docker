package microservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableCaching
@EnableEurekaClient
@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class UsersApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(UsersApplication	.class, args);
		
	
	    }
	
}
