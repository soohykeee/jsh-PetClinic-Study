package kr.co.jshpetclinicstudy.service.model.custom;

import kr.co.jshpetclinicstudy.persistence.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final Member member;

    public CustomUserDetails(Member member) {
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(String.valueOf(member.getRole())));
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getIdentity();
    }

    // jwt 사용하기에, true
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // jwt 사용하기에, true
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // jwt 사용하기에, true
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // jwt 사용하기에, true
    @Override
    public boolean isEnabled() {
        return true;
    }
}
