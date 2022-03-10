package cn.jcc.dy.config;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

/**
 * @program: dy-parse-server
 * @description:
 * @author: CCC
 * @create: 2022-03-08 14:12
 **/

@Configuration
public class OkHttpConfiguration {
    @Bean
    public OkHttpClient okHttpClient(){
        return new OkHttpClient.Builder()
                .connectionPool(new ConnectionPool(200,5, TimeUnit.MINUTES))
                .followRedirects(false)
//                .proxy(new Proxy(Proxy.Type.HTTP,new InetSocketAddress("127.0.0.1",10809)))
                .build();
    }
}
