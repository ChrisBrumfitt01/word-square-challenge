package com.naimuri.wordsquare.WordSquareChallenge.services;

import com.naimuri.wordsquare.WordSquareChallenge.exceptions.NoValidSolutionException;
import com.naimuri.wordsquare.WordSquareChallenge.http.DictionaryHttpReader;
import com.naimuri.wordsquare.WordSquareChallenge.model.WordSquare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WordSquareService {

    private DictionaryHttpReader dictionaryHttpReader;
    private WordSquareBuilder wordSquareBuilder;

    @Autowired
    public WordSquareService(final DictionaryHttpReader dictionaryHttpReader, final WordSquareBuilder wordSquareBuilder) {
        this.dictionaryHttpReader = dictionaryHttpReader;
        this.wordSquareBuilder = wordSquareBuilder;
    }

    public WordSquare solveWordSquare(int size, String text) throws NoValidSolutionException {
        LinkedList<String> wordSquare = new LinkedList<>();
        List<String> words = dictionaryHttpReader.getDictionary(size, text).getWords();
        if(wordSquareBuilder.build(wordSquare, words, size, text)){
            return new WordSquare(wordSquare);
        }
        throw new NoValidSolutionException("No solution found");
    }

}
