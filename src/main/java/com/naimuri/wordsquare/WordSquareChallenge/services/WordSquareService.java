package com.naimuri.wordsquare.WordSquareChallenge.services;

import com.naimuri.wordsquare.WordSquareChallenge.exceptions.NoValidSolutionException;
import com.naimuri.wordsquare.WordSquareChallenge.http.DictionaryHttpReader;
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

    public List<String> solveWordSquare(int size, String text){
        LinkedList<String> grid = new LinkedList<>();
        List<String> words = dictionaryHttpReader.getDictionary(size, text).getWords();
        if(wordSquareBuilder.build(grid, words, size, text)){
            return grid;
        }
        throw new NoValidSolutionException("No solution found");
    }

}
