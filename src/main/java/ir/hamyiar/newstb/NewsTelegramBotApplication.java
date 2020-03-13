package ir.hamyiar.newstb;

import ir.hamyiar.newstb.services.IrnaRSSService;
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
