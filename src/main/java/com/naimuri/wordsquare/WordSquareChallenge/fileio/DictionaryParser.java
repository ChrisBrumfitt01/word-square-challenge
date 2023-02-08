package com.naimuri.wordsquare.WordSquareChallenge.fileio;

import com.naimuri.wordsquare.WordSquareChallenge.config.DictionaryConfig;
import com.naimuri.wordsquare.WordSquareChallenge.model.Dictionary;
import com.naimuri.wordsquare.WordSquareChallenge.util.WordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class DictionaryParser {

    private final RestTemplate restTemplate;
    private final WordUtil wordUtil;
    private final DictionaryConfig config;

    @Autowired
    public DictionaryParser(final RestTemplate restTemplate, final WordUtil wordUtil, final DictionaryConfig config) {
        this.restTemplate = restTemplate;
        this.wordUtil = wordUtil;
        this.config = config;
    }

    public Dictionary getDictionary(int size, String text) {
        String result = restTemplate.getForObject(config.getUrl(), String.class);
        String[] words = result.split("\n");
        return new Dictionary(
                Arrays.stream(words)
                .filter(w -> isMatch(w, size, text))
                .map(String::toLowerCase)
                .collect(Collectors.toList())
        );
    }

    private boolean isMatch(String word, int size, String text) {
        if(word.length() != size) {
            return false;
        }
        return wordUtil.isAnagram(word, text);
    }
}
