package com.example.iticketfinal.service;

import com.example.iticketfinal.dao.entity.UserEntity;
import com.example.iticketfinal.dao.repository.UserRepository;
import com.example.iticketfinal.enums.Roles;
import com.example.iticketfinal.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Component
public class MyUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;


    public UserEntity getCurrentAuthenticatedUser() {
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(()->
                        new NotFoundException(
                                String.format("USER_NOT_FOUND email : %s", userDetails.getUsername()),
                                String.format("ACTION.ERROR.getUserByEmail email : %s", userDetails.getUsername())
                        )
                )
                ;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new User(user.getEmail(), user.getPassword(), getAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(UserEntity userEntity) {
        Roles role = userEntity.getRole();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.name()));
        return authorities;
    }
}
