package project.CrimeRec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableWebMvc
public class runner {

	public static void main(String[] args) {
		System.out.println(System.getProperty("java.class.path"));
		SpringApplication.run(runner.class, args); // Added SpringApplication.run
	}
}
