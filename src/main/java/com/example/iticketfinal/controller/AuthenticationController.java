package com.example.iticketfinal.controller;

import com.example.iticketfinal.dao.entity.UserEntity;
import com.example.iticketfinal.dao.repository.UserRepository;
import com.example.iticketfinal.dto.login.LoginRequestDto;
import com.example.iticketfinal.dto.token.TokenResponseDto;
import com.example.iticketfinal.service.MyUserDetailService;
import com.example.iticketfinal.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final MyUserDetailService userDetailsService;

    private final UserRepository userRepository;

    @PostMapping("/user/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequestDto authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getEmail());

        final String jwt = jwtTokenUtil.generateToken(userDetails);
        UserEntity user = userRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow(()-> new RuntimeException("USER_NOT_FOUND"));
        return ResponseEntity.ok(new TokenResponseDto(jwt , user.getRole()));
    }
}
