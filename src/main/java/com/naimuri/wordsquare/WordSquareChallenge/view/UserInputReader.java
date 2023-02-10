package com.naimuri.wordsquare.WordSquareChallenge.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserInputReader {

    private final Scanner scanner;

    @Autowired
    public UserInputReader(final Scanner scanner) {
        this.scanner = scanner;
    }

    public int readInt(){
        return this.scanner.nextInt();
    }

    public String readString(){
        return this.scanner.next();
    }

}
