package sample.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        try (ConfigurableApplicationContext ctx = SpringApplication.run(Main.class, args)) {
            Hoge h = ctx.getBean(Hoge.class);
            System.out.println(h);
        }
    }
}
