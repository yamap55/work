package sample.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HogeProvider {

    @Bean
    public Hoge getHoge() {
        System.out.println("HogeProvider#getHoge()");
        return new Hoge("hoge provider");
    }
}
