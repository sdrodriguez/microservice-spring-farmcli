package sv.com.sadrosoft.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SadrosoftEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SadrosoftEurekaServerApplication.class, args);
	}

}
