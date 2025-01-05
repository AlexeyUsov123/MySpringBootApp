package UsovAA.MySecondTestAppSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@ComponentScan("UsovAA")
public class MySecondTestAppSpringBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(MySecondTestAppSpringBootApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
