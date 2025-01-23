package jaeryang.spring.security.security;

import jaeryang.spring.security.security.account.Account;
import jaeryang.spring.security.security.account.AccountService;
import jaeryang.spring.security.security.book.Book;
import jaeryang.spring.security.security.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class InitData {
    private final AccountService accountService;
    private final BookRepository bookRepository;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        Account admin = accountService.createNew(new Account("admin", "admin", "ADMIN"));
        Account user =accountService.createNew(new Account("user", "user", "USER"));
        createBook("Spring" , admin);
        createBook("JPA" , user);
    }

    private void createBook(String title, Account account) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(account);
        bookRepository.save(book);
    }
}
