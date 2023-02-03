package com.naimuri.wordsquare.WordSquareChallenge.fileio;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class DictionaryParser implements InitializingBean {

    @Autowired
    RestTemplate restTemplate;

    private List<String> dictionary;

    public boolean isValidWord(String word) {
        return Collections.binarySearch(dictionary, word) >= 0;
    }

    @Override
    public void afterPropertiesSet() {
        String url = "http://norvig.com/ngrams/enable1.txt";
        dictionary = restTemplate.execute(url, HttpMethod.GET, null, response -> {
            BufferedReader br = new BufferedReader(new InputStreamReader(response.getBody()));
            List<String> lines = new ArrayList<>();
            String line;
            while((line = br.readLine()) != null){
                lines.add(line.toLowerCase());
            }
            br.close();
            return lines;
        });
    }
}
