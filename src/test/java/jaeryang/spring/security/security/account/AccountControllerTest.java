package jaeryang.spring.security.security.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AccountService accountService;

    @Test
    @DisplayName("인덱스 페이지 익명 유저로 접근")
    void index_anonymous() throws Exception {
        mockMvc.perform(get("/").with(anonymous()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void index_anonymous2() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void index_user() throws Exception {
        mockMvc.perform(get("/").with(user("jaeryang").roles("USER"))) //로그인 되어 있는 상태로 가정. security test 제공
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("admin 페이지 USER 권한으로 접근 시 권한 없음 오류")
//    @WithMockUser(username = "jaeryang", roles = "USER")
    @WithUser
    void admin_user() throws Exception {
        mockMvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("admin 페이지 ADMIN 권한으로 접근 시 정상 접근")
    @WithAdmin
    void admin_admin() throws Exception {
        mockMvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional //롤백
    @DisplayName("form 로그인 테스트")
    void login() throws Exception {
        // Given
        String username = "jaeryang";
        String password = "123";
        Account user = createUser(username, password);

        // When & Then
        mockMvc.perform(formLogin().user(username).password(password))
                .andExpect(authenticated());
    }

    @Test
    @Transactional //롤백
    @DisplayName("form 로그인 실패 테스트")
    void login_fail() throws Exception {
        // Given
        String username = "jaeryang";
        String password = "123";
        Account user = createUser(username, password);

        // When & Then
        mockMvc.perform(formLogin().user(username).password("xxxx"))
                .andExpect(unauthenticated());
    }

    private Account createUser(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setRole("USER");
        accountService.createNew(account);
        return account;
    }

}