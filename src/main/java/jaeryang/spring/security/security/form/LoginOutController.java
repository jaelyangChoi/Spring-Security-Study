package jaeryang.spring.security.security.form;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginOutController {

    @GetMapping("/login")
    public String signinForm() {
        return "login";
    }

    @GetMapping("/logout")
    public String signoutForm() {
        return "logout";
    }

}
