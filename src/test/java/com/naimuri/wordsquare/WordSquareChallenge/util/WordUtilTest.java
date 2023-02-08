package com.naimuri.wordsquare.WordSquareChallenge.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class WordUtilTest {

    private WordUtil wordUtil = new WordUtil();

    @ParameterizedTest
    @MethodSource("testParams")
    public void shouldReturnTrue_whenAllCharactersOfAWord_areIncludedInTheLetters(String word, String letters,
                                                                                  boolean expected) {
        assertThat(wordUtil.isAnagram(word, letters)).isEqualTo(expected);
    }

    private static Stream<Arguments> testParams() {
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

}
