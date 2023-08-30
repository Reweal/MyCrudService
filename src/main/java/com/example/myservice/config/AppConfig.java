package com.example.myservice.config;

import com.example.myservice.util.ConditionOnProduction;
import com.example.myservice.util.RavenListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    @ConditionOnProduction
    public RavenListener ravenListener() {
        return new RavenListener();
    }

}
