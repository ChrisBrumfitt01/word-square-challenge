package com.naimuri.wordsquare.WordSquareChallenge.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PrefixBuilderTest {

    PrefixBuilder prefixBuilder = new PrefixBuilder();

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