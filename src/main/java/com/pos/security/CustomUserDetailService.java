package com.pos.security;

import com.pos.dto.response.UserResponse;
import com.pos.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserResponse user = userMapper.findByEmail(username);

    if (user == null) {
        throw new UsernameNotFoundException("User not found with username: " + username);
    }

    Set<GrantedAuthority> authorities = new HashSet<>();
    // Assuming user.getRole() returns the role name (e.g., "ADMIN")
    authorities.add(new SimpleGrantedAuthority(String.valueOf(user.getRoleId())));

    return new User(
            user.getEmail(),
            user.getPassword(),
            authorities
    );
}
}
