package jaeryang.spring.security.security.form;

import jaeryang.spring.security.security.account.Account;
import jaeryang.spring.security.security.account.AccountContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SampleService {

    public void dashboard() {
        Account account = AccountContext.getAccount();
        log.info("account.getUsername() = {}", account.getUsername());
    }
}
