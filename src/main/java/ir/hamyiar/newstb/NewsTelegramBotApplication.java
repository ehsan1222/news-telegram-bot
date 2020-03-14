package ir.hamyiar.newstb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NewsTelegramBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsTelegramBotApplication.class, args);
	}

}
