package jaeryang.spring.security.security.form;

import jaeryang.spring.security.security.account.Account;
import jaeryang.spring.security.security.account.UserAccount;
import jaeryang.spring.security.security.common.CurrentUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.concurrent.Callable;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SampleController {

    private final SampleService sampleService;

    @GetMapping("/")
//    public String index(Model model, Principal principal) {
    //@AuthenticationPrincipal을 붙이면 UserAccount(우리가 구현한 UserDetails)로 받아올 수 있다.
    //Account로 바로 받아오려면 옵션 추가
    public String index(Model model, @CurrentUser Account account) {
        if (account == null)
            model.addAttribute("message", "Hello Index");
        else
            model.addAttribute("message", "Hello " + account.getUsername());
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

    @GetMapping("/asyncHandler")
    @ResponseBody
    public Callable<String> asyncHandler() {
        log("asyncHandler");
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                log("Callable");
                return "Async Handler";
            }
        };
    }

    private static void log(String message) {
        System.out.println(message);
        Thread thread = Thread.currentThread();
        System.out.println("thread = " + thread);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("principal = " + principal);
    }

}
