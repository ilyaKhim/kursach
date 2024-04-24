package net.javaguides.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final UserLoginInterceptor userLoginInterceptor;

    @Autowired
    public WebConfig(UserLoginInterceptor userLoginInterceptor) {
        this.userLoginInterceptor = userLoginInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userLoginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/css/**", "/js/**");
    }

}
