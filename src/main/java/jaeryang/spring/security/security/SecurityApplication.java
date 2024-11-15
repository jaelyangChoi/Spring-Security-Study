package jaeryang.spring.security.security;

import jaeryang.spring.security.security.account.AccountRepository;
import jaeryang.spring.security.security.account.AccountService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

    @Bean
    public InitData initData(AccountService accountService) {
        return new InitData(accountService);
    }
}
