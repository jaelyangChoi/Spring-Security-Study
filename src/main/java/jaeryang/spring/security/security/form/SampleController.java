package jaeryang.spring.security.security.form;

import jaeryang.spring.security.security.account.AccountContext;
import jaeryang.spring.security.security.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SampleController {

    private final SampleService sampleService;
    private final AccountRepository accountRepository;

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        if (principal == null)
            model.addAttribute("message", "Hello Index");
        else
            model.addAttribute("message", "Hello " + principal.getName());
        return "index";
    }

    @GetMapping("/info")
    public String info(Model model) {
        model.addAttribute("message", "Hello Info");
        return "info";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
//        AccountContext.setAccount(accountRepository.findByUsername(principal.getName()).orElse(null));
        sampleService.dashboard();
        model.addAttribute("message", "Hello " + principal.getName());
        return "dashboard";
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        log.info("GET admin request!@!@");
        model.addAttribute("message", "Hello admin, " + principal.getName());
        return "admin";
    }

    @GetMapping("/user")
    public String user(Model model, Principal principal) {
        log.info("GET admin request!@!@");
        model.addAttribute("message", "Hello user, " + principal.getName());
        return "user";
    }

}
