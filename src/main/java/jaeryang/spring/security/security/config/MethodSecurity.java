package jaeryang.spring.security.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;


/* 아래 애노테이션을 통해 메서드 보안이 활성화되며,
   AOP 를 통해 @PreAuthorize, @PostAuthorize 같은 메서드 보안 애노테이션을 사용할 수 있게 된다 */
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@Configuration
public class MethodSecurity {
    // 추가적인 보안 설정이 필요한 경우 이곳에 작성

    // AuthenticationManager를 Bean으로 등록
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        return RoleHierarchyImpl.fromHierarchy("ROLE_ADMIN > ROLE_USER");
    }
}
