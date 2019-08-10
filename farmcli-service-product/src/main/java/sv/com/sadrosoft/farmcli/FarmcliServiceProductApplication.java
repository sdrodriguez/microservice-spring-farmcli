package sv.com.sadrosoft.farmcli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"sv.com.sadrosoft.commons.models.entity"})
public class FarmcliServiceProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmcliServiceProductApplication.class, args);
	}

}
