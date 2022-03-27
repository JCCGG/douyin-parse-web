package cn.jcc.dy.config;

import cn.jcc.dy.pojo.po.VideoInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanConfiguration {

    @Bean
    @Scope("singleton")
    public VideoInfo videoInfo(){
        return  new VideoInfo();
    }
}
