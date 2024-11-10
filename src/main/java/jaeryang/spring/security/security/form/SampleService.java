package jaeryang.spring.security.security.form;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SampleService {

    @Secured("ROLE_USER") //메소드 호출 전 권한 검사
    public void dashboard() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        log.info("authentication = {}", authentication);
        log.info("account.getUsername() = {}", ((UserDetails) principal).getUsername());
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void dashboardAdmin() {}
}
