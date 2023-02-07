package com.naimuri.wordsquare.WordSquareChallenge.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Dictionary {

    private List<String> words;

    public Dictionary(List<String> words) {
        this.words = words;
    }

    public List<String> getWords() {
        return words;
    }
}
