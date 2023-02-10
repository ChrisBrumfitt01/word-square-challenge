package com.naimuri.wordsquare.WordSquareChallenge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DictionaryConfig {

    @Value("${dictionary.url}")
    private String url;

    public String getUrl() {
        return url;
    }

}
