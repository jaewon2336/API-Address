package site.metacoding.testproject.web;

import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import site.metacoding.testproject.service.UserService;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

}
