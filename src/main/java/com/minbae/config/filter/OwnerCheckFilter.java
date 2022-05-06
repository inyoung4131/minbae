package com.minbae.config.filter;

import com.minbae.sso.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@WebFilter("/owner/*")
public class OwnerCheckFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(request);
        String token = jwtTokenProvider.getToken();
        System.out.println(response.getHeader("user-token"));
        response.setHeader("user-token",token);
        System.out.println(response.getHeader("user-token"));
        boolean result = jwtTokenProvider.validateExpToken(token);
        boolean result2 = false;
        if (result) {
            result2 = jwtTokenProvider.validateExpTokenForRole(token, "owner");
        }
        if (!result2) {
            response.sendRedirect("/error403");

            return;
        }

    }

}
