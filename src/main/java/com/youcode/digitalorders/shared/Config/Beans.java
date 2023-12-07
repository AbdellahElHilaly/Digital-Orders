package com.youcode.digitalorders.shared.Config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class Beans {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
