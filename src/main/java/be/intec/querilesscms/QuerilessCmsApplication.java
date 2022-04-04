package be.intec.querilesscms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class QuerilessCmsApplication {

    @Bean
    public BCryptPasswordEncoder getEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(QuerilessCmsApplication.class, args);
    }

}
