package site.metacoding.testproject.config.auth;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.testproject.domain.user.User;
import site.metacoding.testproject.domain.user.UserRepository;

@RequiredArgsConstructor
@Service
public class LoginService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // user가 DB에 있으면 리턴!
        Optional<User> userOp = userRepository.findByUsername(username);

        if (userOp.isPresent()) {
            return new LoginUser(userOp.get()); // user만 뽑아서 세션에 넣어줄거야
        }

        return null;
    }

}
