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

}
