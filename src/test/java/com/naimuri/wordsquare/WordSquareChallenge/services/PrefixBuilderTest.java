package com.naimuri.wordsquare.WordSquareChallenge.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PrefixBuilderTest {

    @Autowired PrefixBuilder prefixBuilder;

    @Test
    public void shouldReturnEmptyString_whenGridIsEmpty() {
        String actual = prefixBuilder.buildPrefix(List.of());
        assertThat(actual).isEmpty();
    }

    @Test
    public void shouldReturnSecondCharacterOfFirstElement_whenGridIsSize1(){
        String actual = prefixBuilder.buildPrefix(List.of("rose"));
        assertThat(actual).isEqualTo("o");
    }

    @Test
    public void shouldReturnThirdCharacterOfFirstElement_andThirdCharacterOfSecondElement_whenGridIsSize2() {
        String actual = prefixBuilder.buildPrefix(List.of("rose", "oven"));
        assertThat(actual).isEqualTo("se");
    }

}