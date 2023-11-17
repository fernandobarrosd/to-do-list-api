package com.fernando.to_do_list_api.filters;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.fernando.to_do_list_api.models.AuthUser;
import com.fernando.to_do_list_api.repositories.AuthUserRepository;
import com.fernando.to_do_list_api.services.interfaces.TokenJWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private TokenJWTService tokenJWTService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                if (!isAuthURL(request)) {
                    String token = getAuthorizationTokenHeader(request);
                    if (token != null) {
                        String username = tokenJWTService.getSubject(token);

                        if (username != null) {
                            var authUserOptional = authUserRepository.findByUsername(username);

                            if (authUserOptional.isPresent()) {
                                var authUser = authUserOptional.get();
                                authenticateUser(authUser);
                            }
                        }
                    }
                }
        filterChain.doFilter(request, response);
    }

    private boolean isAuthURL(HttpServletRequest request) {
        String url = request.getRequestURI();
        return url.matches("/auth/[a-z]{0,}");
    }

    private void authenticateUser(AuthUser authUser) {
        var authToken = new UsernamePasswordAuthenticationToken(authUser, 
        null, authUser.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);



    }

    private String getAuthorizationTokenHeader(HttpServletRequest request) {
        String authorization = request.getHeader(AUTHORIZATION_HEADER);

        return authorization != null 
        ? authorization.replace("Bearer ", "") :
        null;
    }
    
}