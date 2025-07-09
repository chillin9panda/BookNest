package chillin9panda.booknest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "chillin9panda")
public class BookNestApplication {
  public static void main(String[] args) {
    SpringApplication.run(BookNestApplication.class, args);
  }
}
