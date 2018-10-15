package com.cognine.onlinetest.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cognine.onlinetest.model.Role;
import com.cognine.onlinetest.model.User;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private String name;
	private Collection<? extends GrantedAuthority> authorities;

	public CustomUserDetails(User user) {
		this.userName = user.getUserEmail();
		this.password = user.getPassword();
		this.name = user.getUserName();
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		for (Role role : user.getUserRoles()) {
			auths.add(new SimpleGrantedAuthority(role.getRoleName().toUpperCase()));
		}
		this.authorities = auths;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
