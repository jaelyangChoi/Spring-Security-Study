package jaeryang.spring.security.security.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

@Slf4j
@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/css/**", "/js/**", "/images/**", "/fonts/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder(); //기본 추천 전략인 bcrypt 방식
        //"{bcrypt}$2a$10$Z0mUanbB26XYAAnPOlj3W.7THw8wywvzYIID2gXS5UP4PDf4inAwa" 와 같이 생성됨
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests.
                requestMatchers("/", "/info", "/account/**", "/signup").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/user").hasRole("USER")
                .anyRequest().authenticated()
        );

        http.httpBasic(Customizer.withDefaults());

        //기본 사용
//        http.formLogin(Customizer.withDefaults());

        //커스텀 로그인 페이지 사용 (로그인, 로그아웃 페이지 생성 필터 사라짐)
        http.formLogin(login ->
                login
                        .loginPage("/login")
                        .permitAll()
        );

        http.logout(logout ->
                logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
        );

        /*
        http.sessionManagement(auth -> auth
                .maximumSessions(1) //다중 로그인 허용 개수
                .maxSessionsPreventsLogin(true) //다중 로그인 개수 초과 시 처리 방법 (true: 추가 로그인 허용 안함, 기본 false)
        );

        http.sessionManagement(auth -> auth
                .sessionFixation().changeSessionId() //세션 고정 보호 (전략: 세션 Id 변경)
        );

        http.sessionManagement(auth -> auth
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션 사용 안함
        );
        */

        http.exceptionHandling(exception -> exception
//                        .accessDeniedPage("/access-denied")
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                            log.info("{} is denied to access {}", principal.getUsername(), request.getRequestURI());
                            response.sendRedirect("/access-denied");
                        })

        );
        return http.build();
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        return RoleHierarchyImpl.fromHierarchy("ROLE_ADMIN > ROLE_USER");
    }

    /**
     * 인메모리 유저 추가
     */
//    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails adminUser = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(adminUser);
    }

    /*
    @Bean
    @Order(3)
    public SecurityFilterChain securityFilterChain1(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/**")
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()
                );
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChain2(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/**")
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated()
                );
        return http.build();
    }
    */
}
