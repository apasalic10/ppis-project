package ba.unsa.etf.nwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class NwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(NwtApplication.class, args);
	}

}
