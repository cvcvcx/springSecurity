package com.cos.security.config.auth;

//시큐리티가 /login으로 주소요청이 오면 로그인을 진행시킨다.
//로그인 진행이 완료가 되면 시큐리티 session을 만들어 주어야함

// 로그인이 되면 Security Session 에 Authentication 이라는 타입이여아하고,
// 유저 정보가 저장되어야 하는데, 그 타입이 UserDetails 타입이어야한다.
// 아래는 UserDetails를 구현한 구현체 UserDetails(PrincipalDetails)가 된다.

import com.cos.security.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {

    private User user;//콤포지션

    public PrincipalDetails(User user) {
        this.user = user;
    }

    //해당 유저의 권한을 리턴하는 곳입니다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
