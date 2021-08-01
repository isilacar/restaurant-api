package com.finartz.restaurantApi.security;

import com.finartz.restaurantApi.error.JwtError;
import com.finartz.restaurantApi.exception.MalFormedException;
import com.finartz.restaurantApi.exception.TokenExpiredException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    private final MessageSource messageSource;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (TokenExpiredException e) {
            setErrorResponse(response, e);
        } catch (MalFormedException e) {
            setErrorResponse(response, e);
        }
    }

    public void setErrorResponse(HttpServletResponse response, Throwable ex) throws IOException {

        //401 Unauthorized hatası dönüyoruz,tokenın süresi geçmişse
        if (ex instanceof TokenExpiredException) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(),
                    messageSource.getMessage(JwtError.EXPIRED_JWT_EXCEPTION.getMessage(), null, Locale.getDefault()));
        }
        //400 BadRequest Hatası dönüyoruz,tokenda bir alan yanlış ya da eksikse
        if (ex instanceof MalFormedException) {
            response.sendError(HttpStatus.BAD_REQUEST.value(),
                    messageSource.getMessage(JwtError.MALFORMED_EXCEPTION.getMessage(), null, Locale.getDefault()));
        }

    }
}