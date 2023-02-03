package com.naimuri.wordsquare.WordSquareChallenge.services;

import com.naimuri.wordsquare.WordSquareChallenge.fileio.DictionaryParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a string, this class is responsible for finding all words of a given size in that string.
 */
@Component
public class WordFinder {

    @Autowired
    private DictionaryParser dictionaryParser;

    private char[] characters;
    private boolean[] used;
    private int size;

    public List<String> getWords(int size, String text) {
        this.characters = text.toCharArray();
        this.size = size;
        this.used = new boolean[characters.length];

        Set<String> words = new HashSet<>();
        generateWords(0, new StringBuilder(), words);
        return new ArrayList<>(words);
    }

    private void generateWords(int index, StringBuilder word, Set<String> words) {
        if (index == size) {
            String wordStr = word.toString();
            if(dictionaryParser.isValidWord(wordStr)){
                words.add(wordStr);
            }
            return;
        }

        for (int i = 0; i < characters.length; i++) {
            if (!used[i]) {
                used[i] = true;
                word.append(characters[i]);
                generateWords(index + 1, word, words);
                word.deleteCharAt(word.length() - 1);
                used[i] = false;
            }
        }
    }
}
