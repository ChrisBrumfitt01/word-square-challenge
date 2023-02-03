package com.naimuri.wordsquare.WordSquareChallenge.services;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrefixBuilder {

    public String buildPrefix(List<String> grid) {
        String prefix = "";
        for(int i=0; i<grid.size(); i++){
            prefix += grid.get(i).charAt(grid.size());
        }
        return prefix;
    }

}
