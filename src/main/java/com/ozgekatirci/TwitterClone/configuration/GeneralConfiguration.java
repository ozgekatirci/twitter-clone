package com.ozgekatirci.TwitterClone.configuration;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
