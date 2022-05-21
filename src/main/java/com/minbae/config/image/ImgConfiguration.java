package com.minbae.config.image;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class ImgConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        /* '/staticImg/**'로 호출하는 자원은 '/static/img/' 폴더 아래에서 찾는다. */
        registry.addResourceHandler("/staticImg/**").addResourceLocations("classpath:/static/img/").setCachePeriod(60 * 60 * 24 * 365);
        registry.addResourceHandler("/staticMusic/**").addResourceLocations("classpath:/static/music/").setCachePeriod(60 * 60 * 24 * 365);
        registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/").resourceChain(false);
        registry.setOrder(1);
    }
}
