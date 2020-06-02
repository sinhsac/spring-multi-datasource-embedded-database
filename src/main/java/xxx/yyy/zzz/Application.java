package xxx.yyy.zzz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@PostConstruct
	public void aaa() {
		System.out.println("\n\n\n");
		System.out.println(org.hibernate.Version.getVersionString());
		System.out.println("\n\n\n");
	}
}
