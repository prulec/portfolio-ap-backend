package com.argprograma.portfolio.security.controller;

import com.argprograma.portfolio.dto.Message;
import com.argprograma.portfolio.security.dto.JwtDto;
import com.argprograma.portfolio.security.dto.LoginUser;
import com.argprograma.portfolio.security.jwt.JwtProvider;
import com.argprograma.portfolio.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
public class AuthController {
    
    @Autowired
    IUserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
    
    @PostMapping ("/user/login")
    public ResponseEntity<JwtDto> login (@RequestBody LoginUser data, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Message("Incorrect credentials..."), HttpStatus.BAD_REQUEST);
        if (!userService.existsByUsername(data.getUsername()))
            return new ResponseEntity(new Message("The username doesn't exists..."), HttpStatus.BAD_REQUEST);
        Authentication authentication = 
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        data.getUsername(), data.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        JwtDto jwtDto = new JwtDto(jwt);
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
            
}
