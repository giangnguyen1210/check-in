package com.pos.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtGenerator tokenGenerator;
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getJwtFromRequest(request);

//        System.out.println(token);

        if(StringUtils.hasText(token) && tokenGenerator.validateToken(token)){
            String email = tokenGenerator.getEmailFromJwt(token).getSubject();
            System.out.println(email);

            UserDetails userDetails = customUserDetailService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken authenticationToken = new
                    UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    public String getJwtFromRequest(HttpServletRequest request){
//        System.out.println(request.);
        String bearerToken = request.getHeader("Authorization");
//        System.out.println(bearerToken);
        if(StringUtils.hasText(bearerToken)&&bearerToken.startsWith("Bearer ")){
            System.out.println(bearerToken.substring(7,bearerToken.length()));
            return bearerToken.substring(7,bearerToken.length());
        }
        return null;
    }
}
