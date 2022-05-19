package site.metacoding.testproject.service;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.testproject.domain.user.User;
import site.metacoding.testproject.domain.user.UserRepository;
import site.metacoding.testproject.handler.ex.CustomApiException;
import site.metacoding.testproject.handler.ex.CustomException;
import site.metacoding.testproject.web.dto.user.UpdateReqDto;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final HttpSession session;

    @Transactional
    public void 회원탈퇴(Integer userId) {
        Optional<User> userOp = userRepository.findById(userId);
        if (userOp.isPresent()) {
            userRepository.deleteById(userId);
            session.invalidate();
        } else {
            throw new CustomApiException("존재하지 않는 사용자입니다.");
        }
    }

    @Transactional
    public void 회원가입(User user) {
        String rawPassword = user.getPassword(); // 1234
        String encPassword = bCryptPasswordEncoder.encode(rawPassword); // 해시 알고리즘
        user.setPassword(encPassword);

        userRepository.save(user);
    }

    public User 회원상세보기(Integer userId) {
        Optional<User> userOp = userRepository.findById(userId);
        if (userOp.isPresent()) {
            User userEntity = userOp.get();
            return userEntity;
        } else {
            throw new CustomException("존재하지 않는 사용자입니다.");
        }
    }

    @Transactional
    public User 회원수정하기(Integer userId, UpdateReqDto updateReqDto) {
        Optional<User> userOp = userRepository.findById(userId);

        if (userOp.isPresent()) {
            User userEntity = userOp.get();

            if (!userEntity.getEmail().equals(updateReqDto.getEmail())) {
                userEntity.setEmail(updateReqDto.getEmail());
            }

            if (!userEntity.getAddress().equals(updateReqDto.getAddress())) {
                userEntity.setAddress(updateReqDto.getAddress());
            }

            return userEntity;
        } else {
            throw new CustomApiException("존재하지 않는 사용자입니다.");
        }
    }

}
