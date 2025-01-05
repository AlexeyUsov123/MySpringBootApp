package UsovAA.MyThirdTestAppSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("UsovAA")
public class MyThirdTestAppSpringBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(MyThirdTestAppSpringBootApplication.class, args);
	}
}
