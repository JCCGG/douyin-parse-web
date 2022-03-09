package cn.jcc.dy.config;

import cn.jcc.dy.intercepter.SysInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String  [] addPathPatterns ={
                "/**"
        };
        registry.addInterceptor(new SysInterceptor()).addPathPatterns(addPathPatterns);
    }
}
