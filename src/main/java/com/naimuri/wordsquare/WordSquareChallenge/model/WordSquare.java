package com.naimuri.wordsquare.WordSquareChallenge.model;

import java.util.List;

public class WordSquare {

    private List<String> words;

    public WordSquare(final List<String> words) {
        this.words = words;
    }

    @Override
    public String toString() {
        return String.join("\n", words);
    }
}
