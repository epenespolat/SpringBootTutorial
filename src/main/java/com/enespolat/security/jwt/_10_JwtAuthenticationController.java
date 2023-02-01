package com.enespolat.security.jwt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@RestController
@CrossOrigin
public class _10_JwtAuthenticationController {


    private final AuthenticationConfiguration authConfiguration;

    public _10_JwtAuthenticationController(AuthenticationConfiguration authConfiguration) {
        this.authConfiguration = authConfiguration;
    }
    @Autowired
    public AuthenticationManager authenticationManager() throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    public void configure(AuthenticationManagerBuilder builder, AuthenticationProvider authenticationProvider) {
        builder.authenticationProvider(authenticationProvider);
    }

    @Autowired
    private _04_JwtTokenUtil jwtTokenUtil;

    @Autowired
    private _08_JwtUserDetailsService userDetailsService;


    //Kayıt olmak
    // http://localhost:8080/register ==> POST { "username":"Enes","password":"123"
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody _01_UserDto user) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(user));
    }

    //Login bilgilerle sistemin bana bearer token verecek yer
    //http://localhost:8080/authenticate  ==> POST { "username":"Hamit","password":"123"
    //Dönen bearer token ekleyerek sisteme giriş sağlamak
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody _06_JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new _07_JwtResponse(token));
    }


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager().authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}