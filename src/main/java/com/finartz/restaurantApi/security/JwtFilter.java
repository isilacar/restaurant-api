package com.finartz.restaurantApi.security;

import com.finartz.restaurantApi.error.UserError;
import com.finartz.restaurantApi.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private static final String BEARER = "Bearer ";
    private static final String AUTHORIZATION = "Authorization";
    //oncePerRequest,her request için bu filterı çalıştır demek

    private final UserDetailsService userDetailsService;
    private final TokenManager tokenManager;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String tokenHeader=httpServletRequest.getHeader(AUTHORIZATION);
        String username=null;
        String token=null;
        UserDetails userDetails = null;

        //stringleri değişkenlerle tanımla

        if(tokenHeader !=null && tokenHeader.startsWith(BEARER)){
            token=tokenHeader.substring(BEARER.length());
            username=tokenManager.getUsernameFromToken(token);

            if(username==null){
                throw new ResourceNotFoundException(UserError.USER_NOT_FOUND);
            }
        }
       if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
         userDetails = userDetailsService.loadUserByUsername(username);

           if (tokenManager.validateJwtToken(token,userDetails)){
               UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(
                       userDetails,null,userDetails.getAuthorities()
               );
               authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
               SecurityContextHolder.getContext().setAuthentication(authenticationToken);
           }
       }

       filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
