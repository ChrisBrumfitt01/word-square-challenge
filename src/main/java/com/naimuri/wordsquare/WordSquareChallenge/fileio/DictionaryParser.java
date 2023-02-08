package com.naimuri.wordsquare.WordSquareChallenge.fileio;

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

    @Autowired
    public DictionaryParser(final RestTemplate restTemplate, final WordUtil wordUtil) {
        this.restTemplate = restTemplate;
        this.wordUtil = wordUtil;
    }

    public Dictionary getDictionary(int size, String text) {
        String url = "http://norvig.com/ngrams/enable1.txt";
        String result = restTemplate.getForObject(url, String.class);
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
