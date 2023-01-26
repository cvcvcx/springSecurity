package com.cos.security.config.auth;

import com.cos.security.model.User;
import com.cos.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//시큐리티 설정에서 loginProcessingUrl("/login")
// /login요청이 오면 자동으로 UserDetailService 타입으로 Ioc되어있는 loadUserByUsername함수가 실행됨
@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {
    //final을 빼먹어서 의존성주입이 일어나지 않았었음 주의할것
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username);
        System.out.println(userEntity);
        if(userEntity !=null){
            return new PrincipalDetails(userEntity);
        }
        return null;
    }
}
