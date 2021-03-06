package site.metacoding.testproject.web;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import site.metacoding.testproject.domain.user.User;
import site.metacoding.testproject.service.UserService;
import site.metacoding.testproject.util.UtilValid;
import site.metacoding.testproject.web.dto.user.JoinReqDto;
import site.metacoding.testproject.web.dto.user.UpdateReqDto;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String main() {
        return "/user/main";
    }

    // 상세보기 페이지
    @GetMapping("/s/user/{userId}")
    public String detailForm(@PathVariable Integer userId, Model model) {
        User userEntity = userService.회원상세보기(userId);
        model.addAttribute("user", userEntity);
        return "/user/detailForm";
    }

    // 회원 수정 페이지
    @GetMapping("/s/user/{userId}/update-form")
    public String updateForm(@PathVariable Integer userId, Model model) {
        User userEntity = userService.회원상세보기(userId);
        model.addAttribute("user", userEntity);
        return "/user/updateForm";
    }

    // 회원 수정
    @PutMapping("/s/user/{userId}")
    public @ResponseBody ResponseEntity<?> update(@PathVariable Integer userId,
            @RequestBody UpdateReqDto updateReqDto) {
        System.out.println(updateReqDto.toString());
        User userEntity = userService.회원수정하기(userId, updateReqDto);
        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }

    // 회원 탈퇴
    @DeleteMapping("/s/user/{userId}")
    public @ResponseBody ResponseEntity<?> deleteAccount(@PathVariable Integer userId) {
        userService.회원탈퇴(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 회원가입 페이지
    @GetMapping("/join-form")
    public String joinForm() {
        return "/user/joinForm";
    }

    // 회원가입
    @PostMapping("/join")
    public String join(@Valid JoinReqDto joinReqDto, BindingResult bindingResult) {
        UtilValid.요청에러처리(bindingResult);
        userService.회원가입(joinReqDto.toEntity());
        return "redirect:/login-form";
    }

    // 로그인 페이지
    @GetMapping("/login-form")
    public String loginForm() {
        return "/user/loginForm";
    }

    @GetMapping("/user/juso-popup")
    public String jusoPopup() {
        return "/user/jusoPopup";
    }

    @PostMapping("/user/juso-popup")
    public String jusoCallback(String inputYn, String roadFullAddr, Model model) {
        model.addAttribute("inputYn", inputYn);
        model.addAttribute("roadFullAddr", roadFullAddr);
        return "/user/jusoPopup";
    }

}
