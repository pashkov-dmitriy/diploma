package com.mymusic.auth.configuration;

import com.mymusic.auth.clients.handlers.RetreiveMessageErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

    @Bean
    public RetreiveMessageErrorDecoder customExceptionHandler() {
        return new RetreiveMessageErrorDecoder();
    }
}
