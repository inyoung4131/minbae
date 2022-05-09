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


    }

}
