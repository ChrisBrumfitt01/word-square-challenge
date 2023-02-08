package com.naimuri.wordsquare.WordSquareChallenge.services;

import com.naimuri.wordsquare.WordSquareChallenge.exceptions.NoValidSolutionException;
import com.naimuri.wordsquare.WordSquareChallenge.fileio.DictionaryParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WordSquareService {

    private DictionaryParser dictionaryParser;
    private WordSquareBuilder wordSquareBuilder;

    @Autowired
    public WordSquareService(final DictionaryParser dictionaryParser, final WordSquareBuilder wordSquareBuilder) {
        this.dictionaryParser = dictionaryParser;
        this.wordSquareBuilder = wordSquareBuilder;
    }

    public List<String> solveWordSquare(int size, String text){
        LinkedList<String> grid = new LinkedList<>();
        List<String> words = dictionaryParser.getDictionary(size, text).getWords();
        if(wordSquareBuilder.build(grid, words, size, text)){
            return grid;
        }
        throw new NoValidSolutionException("No solution found");
    }

}
