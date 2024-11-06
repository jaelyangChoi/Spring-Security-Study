package jaeryang.spring.security.security;

import jaeryang.spring.security.security.account.Account;
import jaeryang.spring.security.security.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class InitData {
    private final AccountRepository accountRepository;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        Account account = new Account("admin", "123", "ADMIN");
        accountRepository.save(account);
    }
}
