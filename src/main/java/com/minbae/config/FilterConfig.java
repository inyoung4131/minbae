package com.minbae.config;

import com.minbae.config.filter.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<Filter> filter1(){
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>(new Filter());
        bean.addUrlPatterns("/**");
        bean.setOrder(0);
        return bean;
    }
}
