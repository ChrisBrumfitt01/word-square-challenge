package com.naimuri.wordsquare.WordSquareChallenge.services;

import com.naimuri.wordsquare.WordSquareChallenge.exceptions.NoValidSolutionException;
import com.naimuri.wordsquare.WordSquareChallenge.model.WordSquare;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@ActiveProfiles("test")
@SpringBootTest
public class WordSquareServiceIntegrationTest {

    @Autowired
    private WordSquareService wordSquareService;

    @ParameterizedTest
    @MethodSource("successfulTestParams")
    public void solveWordSquare_shouldReturnExpectedWordSquare_whenSuccessful(int size, String text, List<String> expected) throws NoValidSolutionException {
        WordSquare actual = wordSquareService.solveWordSquare(size, text);
        assertThat(actual.getWords()).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("unsuccessfulTestParams")
    public void solveWordSquare_shouldThrowNoSolutionFoundException_whenUnsuccessful(int size, String text) {
        assertThatThrownBy(() -> wordSquareService.solveWordSquare(size, text))
                .isInstanceOf(NoValidSolutionException.class);
    }

    private static Stream<Arguments> successfulTestParams() {
        return Stream.of(
                arguments(4, "eeeeddoonnnsssrv", List.of("rose", "oven", "send", "ends")),
                arguments(4, "aaccdeeeemmnnnoo", List.of("moan", "once", "acme", "need")),
                arguments(5, "aaaeeeefhhmoonssrrrrttttw", List.of("feast", "earth", "armer", "stone", "throw")),
                arguments(5, "aabbeeeeeeeehmosrrrruttvv", List.of("heart", "ember", "above", "revue", "trees")),
                arguments(7, "aaaaaaaaabbeeeeeeedddddggmmlloooonnssssrrrruvvyyy",
                        List.of("bravado", "renamed", "analogy", "valuers", "amoebas", "degrade", "odyssey"))
        );
    }

    private static Stream<Arguments> unsuccessfulTestParams() {
        return Stream.of(
                arguments(4, "tgtgtgtgtgtgtgtg")
        );
    }

}
