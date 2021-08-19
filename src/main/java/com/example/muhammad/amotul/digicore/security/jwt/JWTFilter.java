package com.example.muhammad.amotul.digicore.security.jwt;

import com.example.muhammad.amotul.digicore.model.AccountUserDetails;
import com.example.muhammad.amotul.digicore.service.implementation.AccountUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.util.StringUtils.hasText;

@Component
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    private JWTProvider provider;
    @Autowired
    private AccountUserDetailsService userDetailsService;

    public String getToken(HttpServletRequest request){
        String bearer = request.getHeader("Authorization");
        if(hasText(bearer) && bearer.startsWith("Bearer")){
            return bearer.substring(7);
        }
        return null;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(httpServletRequest);
        if(token != null && provider.validateToken(token)){
            String accountNumber = provider.getAccountNumberFromToken(token);
            AccountUserDetails userDetails = userDetailsService.loadUserByUsername(accountNumber);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails,null,
                    userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
