package com.footballmatches.matches.controller;

import com.footballmatches.matches.entity.JWTRequest;
import com.footballmatches.matches.entity.JWTResponse;
import com.footballmatches.matches.security.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    JwtHelper jwtHelper;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<JWTResponse> authenticateAndGetToken(@RequestBody JWTRequest request){
        this.doAuthenticate(request.getUsername(),request.getPassword());
        UserDetails userDetails= userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.jwtHelper.generateToken(userDetails);
        JWTResponse response = JWTResponse.builder()
                .jwtToken(token).username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String userName, String password) {
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userName,password);
        try {
            manager.authenticate(authentication);
        }catch(BadCredentialsException ex){
            throw new BadCredentialsException("Credentials Invalid!");
        }
    }
}
