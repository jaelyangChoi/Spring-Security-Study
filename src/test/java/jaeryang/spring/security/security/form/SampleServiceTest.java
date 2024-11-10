package jaeryang.spring.security.security.form;

import jaeryang.spring.security.security.account.Account;
import jaeryang.spring.security.security.account.AccountService;
import jaeryang.spring.security.security.account.WithAdmin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SampleServiceTest {

    @Autowired
    SampleService sampleService;

    @Autowired
    AccountService accountService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Test
    void dashboard() {
        //iven : 계정 생성 후 인증
        Account account = new Account("test", "test", "ADMIN");
        accountService.createNew(account);

        UserDetails userDetails = accountService.loadUserByUsername("test");

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, "test");
        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // when & then
        sampleService.dashboard();
    }

    @Test
    @WithMockUser(username = "test", roles = "ADMIN")
    void dashboardAdmin(){
        sampleService.dashboardAdmin();
    }
}