package com.minbae.config.filter;

import javax.servlet.*;
import java.io.IOException;


public class Filter implements javax.servlet.Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        chain.doFilter(request, response);
    }

}
