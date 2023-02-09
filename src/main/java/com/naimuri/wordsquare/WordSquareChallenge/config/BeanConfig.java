package com.naimuri.wordsquare.WordSquareChallenge.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

@Configuration
public class BeanConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplateBuilder()
                .defaultHeader("Accept", "plain/text")
                .build();
    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
}
