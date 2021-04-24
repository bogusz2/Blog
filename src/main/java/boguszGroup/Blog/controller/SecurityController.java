package boguszGroup.Blog.controller;

import boguszGroup.Blog.model.UserDto;
import boguszGroup.Blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class SecurityController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping("/register")
    public String registerUserAccount(@ModelAttribute("user") @Valid final UserDto accountDto) {
        return userService
                .registerNewUserAccount(accountDto)
                .isEmpty() ? "redirect:/register" : "home"; //todo return loginError page
    }
}
