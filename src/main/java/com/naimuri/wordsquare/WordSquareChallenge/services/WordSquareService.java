package com.naimuri.wordsquare.WordSquareChallenge.services;

import com.naimuri.wordsquare.WordSquareChallenge.exceptions.NoValidSolutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WordSquareService {

    @Autowired private WordFinder wordFinder;
    @Autowired private WordSquareBuilder wordSquareBuilder;

    public List<String> solveWordSquare(int size, String text){
        LinkedList<String> grid = new LinkedList<>();
        List<String> words = wordFinder.getWords(size, text);
        if(wordSquareBuilder.build(grid, words, size, text)){
            return grid;
        }
        throw new NoValidSolutionException("No solution found");
    }

}
