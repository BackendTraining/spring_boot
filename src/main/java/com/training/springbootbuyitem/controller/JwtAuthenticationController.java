package com.training.springbootbuyitem.controller;

import com.training.springbootbuyitem.entity.request.LoginUserDTO;
import com.training.springbootbuyitem.entity.response.JwtResponseDTO;
import com.training.springbootbuyitem.error.AuthException;
import com.training.springbootbuyitem.utils.TokenProvider;
import com.training.springbootbuyitem.utils.annotation.ServiceOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtAuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    public JwtAuthenticationController(AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping(value = "/authenticate")
    @ServiceOperation("createAuthenticationToken")
    public ResponseEntity<JwtResponseDTO> createAuthenticationToken(@RequestBody LoginUserDTO loginUser) throws AuthException {

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginUser.getUsername(),
                    loginUser.getPassword()
                )
            );
        } catch (DisabledException e) {
            throw new AuthException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new AuthException(e);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtResponseDTO(token));
    }

}