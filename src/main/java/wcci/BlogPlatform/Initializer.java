package wcci.BlogPlatform;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Initializer implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception
		{
		System.out.println("RUNNING INITIALIZER");
		}
}
