package com.naimuri.wordsquare.WordSquareChallenge.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class WordUtilTest {

    private WordUtil wordUtil = new WordUtil();

    @ParameterizedTest
    @MethodSource("isAnagramParams")
    public void isAnagramTest(String word, String letters, boolean expected) {
        assertThat(wordUtil.isAnagram(word, letters)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("stripLettersParams")
    public void stripLettersTest(String word, String letters, String expected) {
        assertThat(wordUtil.stripLetters(word, letters)).isEqualTo(expected);
    }

    private static Stream<Arguments> isAnagramParams() {
        return Stream.of(
                arguments("a", "a", true),
                arguments("a", "abc", true),
                arguments("and", "andletters", true),
                arguments("and", "nothere", false),
                arguments("and", "nd", false),
                arguments("aa", "a", false),
                arguments("balloons", "balons", false)
        );
    }

    private static Stream<Arguments> stripLettersParams() {
        return Stream.of(
                arguments("a", "a", ""),
                arguments("b", "b", ""),
                arguments("word", "word", ""),
                arguments("word", "wordand", "and"),
                arguments("word", "dddword", "ddd")
        );
    }

}
