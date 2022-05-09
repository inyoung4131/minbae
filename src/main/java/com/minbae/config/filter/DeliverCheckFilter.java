package com.minbae.config.filter;

import com.minbae.sso.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;

import javax.servlet.*;
import javax.servlet.Filter;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@WebFilter("/deliver/*")
public class DeliverCheckFilter implements Filter {

    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpResponse=(HttpServletResponse) servletResponse;
        String token = jwtTokenProvider.getToken();
        boolean result = jwtTokenProvider.validateExpToken(token);
        boolean result2 = false;
        if(result) {
            result2 = jwtTokenProvider.validateExpTokenForRole(token, "deliver");
        }
        if(!result2){
            httpResponse.sendRedirect("/error403");
            return;
        }
    }
}
