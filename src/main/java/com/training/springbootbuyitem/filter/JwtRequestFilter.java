package com.training.springbootbuyitem.filter;

import com.training.springbootbuyitem.service.JwtUserDetailsService;
import com.training.springbootbuyitem.utils.TokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUserDetailsService jwtUserDetailsService;
    private final TokenProvider jwtTokenUtil;
    @Value("${jwt.header.string}")
    public String headerString;
    @Value("${jwt.token.prefix}")
    public String tokenPrefix;

    public JwtRequestFilter(JwtUserDetailsService jwtUserDetailsService, TokenProvider tokenProvider) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtTokenUtil = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws ServletException, IOException {

        String header = request.getHeader(headerString);
        String username = null;
        String authToken = null;

        // JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
        if (header != null && header.startsWith(tokenPrefix)) {
            authToken = header.replace(tokenPrefix, "");
            try {
                username = jwtTokenUtil.getUsernameFromToken(authToken);
            } catch (IllegalArgumentException e) {
                log.error("An error occurred while fetching Username from Token", e);
            } catch (ExpiredJwtException e) {
                log.error("The token has expired", e);
            } catch (SignatureException e) {
                log.error("Authentication Failed. Username or Password not valid.");
            }
        } else {
            log.warn("Couldn't find bearer string, header will be ignored");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

            if (Boolean.TRUE.equals(jwtTokenUtil.validateToken(authToken, userDetails))) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                log.info("authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }

}