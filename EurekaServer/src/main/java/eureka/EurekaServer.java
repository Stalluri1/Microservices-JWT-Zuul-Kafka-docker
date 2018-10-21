package eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;


@EnableEurekaServer
@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class EurekaServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(EurekaServer.class, args);

	}

}
