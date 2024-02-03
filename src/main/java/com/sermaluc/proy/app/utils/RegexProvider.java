package com.sermaluc.proy.app.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegexProvider {

    @Value("${application.config.password-regex}")
    private String pattern;

    @Bean
    public String customRegex(){
        return pattern;
    }
}
