package sv.com.sadrosoft.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("sv.com.sadrosoft.users.commons.models.entity")
@SpringBootApplication
public class FarmcliServiceUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmcliServiceUsersApplication.class, args);
	}

}
