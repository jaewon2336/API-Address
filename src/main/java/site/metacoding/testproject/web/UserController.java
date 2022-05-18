package site.metacoding.testproject.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.testproject.service.UserService;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String main() {
        return "/user/main";
    }

    @GetMapping("join-form")
    public String joinForm() {
        return "/user/joinForm";
    }

    @GetMapping("login-form")
    public String loginForm() {
        return "/user/loginForm";
    }

}
