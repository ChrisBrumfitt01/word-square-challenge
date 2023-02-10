package com.naimuri.wordsquare.WordSquareChallenge.view;

import com.naimuri.wordsquare.WordSquareChallenge.exceptions.NoValidSolutionException;
import com.naimuri.wordsquare.WordSquareChallenge.model.WordSquare;
import com.naimuri.wordsquare.WordSquareChallenge.services.WordSquareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!test")
@Component
public class CommandLineUI implements CommandLineRunner {

    private final WordSquareService wordSquareService;

    @Autowired
    public CommandLineUI(final WordSquareService wordSquareService) {
        this.wordSquareService = wordSquareService;
    }

    @Override
    public void run(final String... args) {
        try {
            WordSquare wordSquare = wordSquareService.solveWordSquare(Integer.parseInt(args[0]), args[1]);
            System.out.println(wordSquare);
        } catch (NoValidSolutionException e) {
            System.out.println(e.getMessage());
        }
    }

}
