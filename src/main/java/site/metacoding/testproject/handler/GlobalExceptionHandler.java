package site.metacoding.testproject.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import site.metacoding.testproject.handler.ex.CustomException;
import site.metacoding.testproject.util.Script;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public String htmlException(Exception e) { // 일반적인 Get(a태그), Post(form태그) 요청 -> html 응답
        return Script.back(e.getMessage());
    }
}