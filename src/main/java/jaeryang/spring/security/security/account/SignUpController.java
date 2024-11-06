package jaeryang.spring.security.security.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {

    @Autowired
    AccountService accountService;

    @GetMapping("/signup")
    public String signUpForm(Model model) {
        model.addAttribute("account", new Account());
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignUp(@ModelAttribute Account account) {
        account.setRole("USER");
        accountService.createNew(account);
        return "redirect:/";
    }
}
