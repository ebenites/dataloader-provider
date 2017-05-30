package pe.edu.tecsup.dataloader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DataloaderProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataloaderProviderApplication.class, args);
	}

}
