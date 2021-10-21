package bot.itemku;

import bot.itemku.Controller.RequestListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ItemkuApplication {
//	public static Logger logger = LogManager.getRootLogger();
private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ItemkuApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ItemkuApplication.class, args);
		RequestListener.startBrowser();
	}

}
