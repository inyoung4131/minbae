package com.minbae.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import com.minbae.user.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class PrincipalDetails implements UserDetails{

	private User user;

    public PrincipalDetails(User user){
        this.user = user;
    }

    public User getUser() {
		return user;
	}

    @Override
    public String getPassword() {
        return user.getUserPwd();
    }

    @Override
    public String getUsername() {
        return user.getUserEmail();
    }

    public Long getUserIdx(){return user.getUserIdx();}

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
    
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        	authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }
}
