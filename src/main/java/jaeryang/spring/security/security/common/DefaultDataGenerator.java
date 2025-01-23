package jaeryang.spring.security.security.common;

import jaeryang.spring.security.security.account.Account;
import jaeryang.spring.security.security.account.AccountService;
import jaeryang.spring.security.security.book.Book;
import jaeryang.spring.security.security.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DefaultDataGenerator implements ApplicationRunner {

    @Autowired
    AccountService accountService;
    @Autowired
    BookRepository bookRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        /*Account user1 = createUser("admin", "ADMIN");
        Account user2 = createUser("user", "USER");

        createBook("Spring" , user1);
        createBook("JPA" , user2);*/
    }

    private void createBook(String title, Account account) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(account);
        bookRepository.save(book);
    }

    private Account createUser(String username, String role) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(username);
        account.setRole(role);
        return accountService.createNew(account);
    }
}
