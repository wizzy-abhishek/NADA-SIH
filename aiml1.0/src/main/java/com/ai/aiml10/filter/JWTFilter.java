package com.ai.aiml10.filter;

import com.ai.aiml10.entity.UserEntity;
import com.ai.aiml10.service.JWTService;
import com.ai.aiml10.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTService jwtService ;
    private final UserService userService ;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver handlerExceptionResolver ;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) {
    try {
        final String requestToken = request.getHeader("Authorization");

        if (requestToken == null || !requestToken.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = requestToken.split("Bearer ")[1];
        Long userId = Long.valueOf(jwtService.getUserIdFromToken(token));

        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserEntity user = userService.findUserById(userId);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            authenticationToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }catch(Exception e){
        handlerExceptionResolver.resolveException(request , response , null , e);
    }
    }
}
