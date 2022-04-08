package com.example.demo.config;

import com.example.demo.annatation.EnableColor;
import com.example.demo.entity.Yellow;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableColor
@Configuration
public class ColorConfiguration {

    @Bean
    public Yellow yellow() {
        return new Yellow();
    }

}
