package jaeryang.spring.security.security.form;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SampleService {

    public void dashboard() {
//        Account account = AccountContext.getAccount();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        log.info("authentication = {}", authentication);
        log.info("account.getUsername() = {}", ((UserDetails) principal).getUsername());
    }
}
