package com.kovalenko.deployer.backend.service.user.impl;

import com.kovalenko.deployer.backend.model.user.ApplicationUserDetails;
import com.kovalenko.deployer.backend.model.user.UserModel;
import com.kovalenko.deployer.backend.service.user.ApplicationUserDetailsService;
import com.kovalenko.deployer.backend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationUserDetailsServiceImpl implements ApplicationUserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userService.findUserByLoginOrThrow(username);
        return ApplicationUserDetails.builder()
            .login(user.getLogin())
            .password(user.getPassword())
            .enabled(user.getEnabled())
            .authorities(getAuthorities(user))
            .build();
    }

    private Set<SimpleGrantedAuthority> getAuthorities(UserModel user) {
        return user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.getRole().name())))
            .collect(Collectors.toSet());
    }
}
