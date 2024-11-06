package jaeryang.spring.security.security;

import jaeryang.spring.security.security.account.Account;
import jaeryang.spring.security.security.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class InitData {
    private final AccountService accountService;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        accountService.createNew(new Account("admin", "admin", "ADMIN"));
        accountService.createNew(new Account("user", "user", "USER"));
    }
}
