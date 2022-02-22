package shakh.supermarketdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SupermarketDemoApplication {

    public static void main(String[] args){
        SpringApplication.run(SupermarketDemoApplication.class, args);
    }


}
