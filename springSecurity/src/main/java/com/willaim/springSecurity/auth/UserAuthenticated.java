package com.willaim.springSecurity.auth;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.willaim.springSecurity.model.UserEntity;

public class UserAuthenticated implements UserDetails{

    private UserEntity user;

    public UserAuthenticated(UserEntity user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(()->"admin");    
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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
    
    
}
