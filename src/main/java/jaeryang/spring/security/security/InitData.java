package jaeryang.spring.security.security;

import jaeryang.spring.security.security.account.Account;
import jaeryang.spring.security.security.account.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
public class InitData {
    private final AccountService accountService;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        log.info("init data");
        Account account = new Account("admin", "123", "ADMIN");
        accountService.createNew(account);
        log.info("save account = {}", account);
    }
}
