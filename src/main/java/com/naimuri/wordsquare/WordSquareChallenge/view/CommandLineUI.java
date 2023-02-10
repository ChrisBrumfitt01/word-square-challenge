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

    private final UserInputReader userInputReader;
    private final UserOutputWriter userOutputWriter;
    private final WordSquareService wordSquareService;

    @Autowired
    public CommandLineUI(
            final UserInputReader userInputReader,
            final WordSquareService wordSquareService,
            final UserOutputWriter userOutputWriter
    ) {
        this.userInputReader = userInputReader;
        this.wordSquareService = wordSquareService;
        this.userOutputWriter = userOutputWriter;
    }

    @Override
    public void run(final String... args) {
        System.out.println("Welcome. Enter the size of your Word Square:");
        int size = userInputReader.readInt();

        System.out.println("Enter your letters:");
        String letters = userInputReader.readString();

        try {
            WordSquare wordSquare = wordSquareService.solveWordSquare(size, letters);
            userOutputWriter.writeLine(wordSquare.toString());
        } catch (NoValidSolutionException e) {
            userOutputWriter.writeLine(e.getMessage());
        }
    }
}
