package kr.co.sooragenius.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class StudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyApplication.class, args);
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
