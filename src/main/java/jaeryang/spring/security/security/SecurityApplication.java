package jaeryang.spring.security.security;

import jaeryang.spring.security.security.account.AccountService;
import jaeryang.spring.security.security.book.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

    @Bean
    public InitData initData(AccountService accountService, BookRepository bookRepository) {
        return new InitData(accountService, bookRepository);
    }
}
