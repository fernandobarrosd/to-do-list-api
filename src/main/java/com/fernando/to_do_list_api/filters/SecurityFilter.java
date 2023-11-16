package com.fernando.to_do_list_api.filters;

import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                if (!isAuthURL(request)) {
                    
                }
        filterChain.doFilter(request, response);
    }

    private boolean isAuthURL(HttpServletRequest request) {
        String url = request.getRequestURI();
        return url.matches("/auth/[a-z]{0,}");
    }
    
}