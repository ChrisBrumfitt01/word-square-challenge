package com.naimuri.wordsquare.WordSquareChallenge.view;

import com.naimuri.wordsquare.WordSquareChallenge.services.WordSquareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class CommandLineUI implements UI {

    private final Scanner scanner;
    private final WordSquareService wordSquareService;

    @Autowired
    public CommandLineUI(final Scanner scanner, final WordSquareService wordSquareService) {
        this.scanner = scanner;
        this.wordSquareService = wordSquareService;
    }

    public void start() {
        System.out.println("Welcome. Enter the size of your Word Square:");
        int size = scanner.nextInt();

        System.out.println("Enter your letters:");
        String letters = scanner.next();

        List<String> result = wordSquareService.solveWordSquare(size, letters);
        for (String line : result) {
            System.out.println(line);
        }
    }

}
