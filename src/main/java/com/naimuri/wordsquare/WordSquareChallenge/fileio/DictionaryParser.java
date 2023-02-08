package com.naimuri.wordsquare.WordSquareChallenge.fileio;

import com.naimuri.wordsquare.WordSquareChallenge.model.Dictionary;
import com.naimuri.wordsquare.WordSquareChallenge.util.WordUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class DictionaryParser {

    private final RestTemplate restTemplate;
    private final WordUtil wordUtil;

    @Autowired
    public DictionaryParser(final RestTemplate restTemplate, final WordUtil wordUtil) {
        this.restTemplate = restTemplate;
        this.wordUtil = wordUtil;
    }

    public Dictionary getDictionary(int size, String text) {
        String url = "http://norvig.com/ngrams/enable1.txt";
        return restTemplate.execute(url, HttpMethod.GET, null, response -> {
            BufferedReader br = new BufferedReader(new InputStreamReader(response.getBody()));
            List<String> words = new ArrayList<>();
            String line;
            while((line = br.readLine()) != null){
                if (isMatch(line, size, text)){
                    words.add(line.toLowerCase());
                }
            }
            br.close();
            return new Dictionary(words);
        });
    }

    private boolean isMatch(String word, int size, String text) {
        if(word.length() != size) {
            return false;
        }
        return wordUtil.isAnagram(word, text);
    }
}
