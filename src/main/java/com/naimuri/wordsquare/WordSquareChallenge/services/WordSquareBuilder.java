package com.naimuri.wordsquare.WordSquareChallenge.services;

import com.naimuri.wordsquare.WordSquareChallenge.util.WordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WordSquareBuilder {

    private final PrefixBuilder prefixBuilder;
    private final WordUtil wordUtil;

    @Autowired
    public WordSquareBuilder(final PrefixBuilder prefixBuilder, final WordUtil wordUtil) {
        this.prefixBuilder = prefixBuilder;
        this.wordUtil = wordUtil;
    }

    public boolean build(LinkedList<String> grid, List<String> words, int size, String letters) {
        final String prefix = prefixBuilder.buildPrefix(grid);
        List<String> filteredWords = words.stream()
                .filter(w -> w.startsWith(prefix))
                .collect(Collectors.toList());

        for(String word : filteredWords) {
            boolean success = handleWord(grid, words, word, size, letters);
            if(success){
                return true;
            }
        }

        grid.removeLast();
        return false;
    }

    private boolean handleWord(LinkedList<String> grid, List<String> words, String word, int size, String letters){
        grid.add(word);
        if(grid.size() == size) {
            return true;
        }

        String availableLetters = wordUtil.stripLetters(word, letters);

        List<String> wordsWithNewlyReducedLetters = filterWords(words, availableLetters);
        build(grid, wordsWithNewlyReducedLetters, size, availableLetters);
        return grid.size() == size;
    }

    private List<String> filterWords(final List<String> words, final String letters) {
        return words.stream()
                .filter(word -> wordUtil.isAnagram(word, letters))
                .collect(Collectors.toList());
    }

}
