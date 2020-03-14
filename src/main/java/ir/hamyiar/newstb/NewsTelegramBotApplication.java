package ir.hamyiar.newstb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@PropertySource(value = {"classpath:profile.properties"})
public class NewsTelegramBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsTelegramBotApplication.class, args);
	}

}
