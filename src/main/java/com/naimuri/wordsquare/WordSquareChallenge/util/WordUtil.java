package com.naimuri.wordsquare.WordSquareChallenge.util;

import org.springframework.stereotype.Component;

@Component
public class WordUtil {

    public boolean isAnagram(String word, String text){
        for(char c : word.toCharArray()){
            if (text.indexOf(c) == -1){
                return false;
            }
            text = text.replaceFirst(Character.toString(c), "");
        }
        return true;
    }

    public String stripLetters(String word, String letters) {
        for(Character c : word.toCharArray()){
            letters = letters.replaceFirst(String.valueOf(c), "");
        }
        return letters;
    }

}
