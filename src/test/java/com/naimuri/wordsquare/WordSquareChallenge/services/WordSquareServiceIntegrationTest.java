package com.naimuri.wordsquare.WordSquareChallenge.services;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest
public class WordSquareServiceIntegrationTest {

    @Autowired
    private WordSquareService wordSquareService;

    @ParameterizedTest
    @MethodSource("testParams")
    public void solveWordSquare_shouldReturnExpectedWordSquare(int size, String text, List<String> expected) {
        List<String> actual = wordSquareService.solveWordSquare(size, text);
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> testParams() {
        return Stream.of(
                arguments(4, "eeeeddoonnnsssrv", List.of("rose", "oven", "send", "ends")),
                arguments(4, "aaccdeeeemmnnnoo", List.of("moan", "once", "acme", "need")),
                arguments(5, "aaaeeeefhhmoonssrrrrttttw", List.of("feast", "earth", "armer", "stone", "throw")),
                arguments(5, "aabbeeeeeeeehmosrrrruttvv", List.of("heart", "ember", "above", "revue", "trees")),
                arguments(7, "aaaaaaaaabbeeeeeeedddddggmmlloooonnssssrrrruvvyyy",
                        List.of("bravado", "renamed", "analogy", "valuers", "amoebas", "degrade", "odyssey"))
        );
    }

}
