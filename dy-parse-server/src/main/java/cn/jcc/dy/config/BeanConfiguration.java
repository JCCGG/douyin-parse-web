package cn.jcc.dy.config;

import cn.jcc.dy.pojo.po.VideoInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public VideoInfo videoInfo(){
        return  new VideoInfo();
    }
}
