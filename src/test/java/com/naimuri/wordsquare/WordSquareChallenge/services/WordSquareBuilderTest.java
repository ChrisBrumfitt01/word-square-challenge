package com.naimuri.wordsquare.WordSquareChallenge.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class WordSquareBuilderTest {

    @Mock private PrefixBuilder prefixBuilder;
    @Mock private WordFinder wordFinder;

    @InjectMocks
    private WordSquareBuilder builder;


    @Test
    public void shouldReturnTrue_andPopulateGrid_whenPartlyPopulatedGridPassedIn_withSolutionAvailableInWordsToTry(){
        LinkedList<String> grid = new LinkedList<>(List.of("to"));
        when(prefixBuilder.buildPrefix(grid)).thenReturn("o");
        boolean result = builder.build(grid, List.of("on"), 2, "on");
        assertThat(result).isTrue();
        assertThat(grid).containsExactly("to", "on");
    }

    @Test
    public void shouldReturnTrue_andPopulateGrid_whenPartlyPopulatedGridPassedIn_withSolutionAvailableInWordsToTry_amongstOtherIncorrectWords(){
        LinkedList<String> grid = new LinkedList<>(List.of("to"));
        when(prefixBuilder.buildPrefix(grid)).thenReturn("o");
        boolean result = builder.build(grid, List.of("hi", "do", "on"), 2, "on");
        assertThat(result).isTrue();
        assertThat(grid).containsExactly("to", "on");
    }

    @Test
    public void shouldReturnFalse_andRemoveFromGrid_whenPartlyPopulatedGridPassedIn_WithNoSolutionInWordsToTry() {
        LinkedList<String> grid = new LinkedList<>(List.of("to"));
        when(prefixBuilder.buildPrefix(grid)).thenReturn("o");

        boolean result = builder.build(grid, List.of(), 2, "on");
        assertThat(result).isFalse();
        assertThat(grid).isEmpty();
    }


    @Test
    public void shouldReturnTrue_andPopulateGrid_whenPartlyPopulatedGridPassedIn_withSolutionAvailableInWordsToTry_AndSizeIs3(){
        LinkedList<String> grid = new LinkedList<>(List.of("yes", "eye"));
        when(prefixBuilder.buildPrefix(grid)).thenReturn("se");
        boolean result = builder.build(grid, List.of("set"), 3, "set");
        assertThat(result).isTrue();
        assertThat(grid).containsExactly("yes", "eye", "set");
    }

    @Test
    public void shouldReturnTrue_andPopulateGrid_whenPartlyPopulatedGridPassedIn_withSolutionAvailableInWordsToTry_amongstOtherIncorrectWords_AndSizeIs3(){
        LinkedList<String> grid = new LinkedList<>(List.of("yes", "eye"));
        when(prefixBuilder.buildPrefix(grid)).thenReturn("se");
        boolean result = builder.build(grid, List.of("the", "and", "set"), 3, "set");
        assertThat(result).isTrue();
        assertThat(grid).containsExactly("yes", "eye", "set");
    }


    @Test
    public void shouldReturnFalse_andRemoveFromGrid_whenPartlyPopulatedGridPassedIn_WithNoSolutionInWordsToTry_AndSizeIs3() {
        LinkedList<String> grid = new LinkedList<>(List.of("yes", "eye"));
        when(prefixBuilder.buildPrefix(grid)).thenReturn("se");

        boolean result = builder.build(grid, List.of(), 3, "on");
        assertThat(result).isFalse();
        assertThat(grid).containsExactly("yes");
    }

    @Test
    public void shouldFindWords_usingCorrectLetters_WithLettersFromTheWordThatWasJustAddedRemoved() {
        LinkedList<String> grid = new LinkedList<>(List.of("yes"));
        when(prefixBuilder.buildPrefix(any())).thenReturn("e");

        builder.build(grid, List.of("eye", "the", "and",  "set"), 3, "eyeset");
        verify(wordFinder).getWords(3, "set");
    }

    @Test
    public void shouldReturnTrue_andPopulateGrid_whenGridNeeds2MoreWords_withSolutionAvailable() {
        LinkedList<String> grid = new LinkedList<>(List.of("yes"));
        when(prefixBuilder.buildPrefix(any())).thenReturn("e").thenReturn("se");
        when(wordFinder.getWords(anyInt(), anyString())).thenReturn(List.of("set"));

        boolean result = builder.build(grid, List.of("eye"), 3, "eyeset");
        assertThat(result).isTrue();
        assertThat(grid).containsExactly("yes", "eye", "set");
    }

    @Test
    public void shouldReturnFalse_andRemoveFromGrid_whenGridNeeds2MoreWords_noSolutionAvailableForLastWord() {
        LinkedList<String> grid = new LinkedList<>(List.of("yes"));
        when(prefixBuilder.buildPrefix(any())).thenReturn("e").thenReturn("se");
        when(wordFinder.getWords(anyInt(), anyString())).thenReturn(List.of("bad"));

        boolean result = builder.build(grid, List.of("eye"), 3, "eyeset");
        assertThat(result).isFalse();
        assertThat(grid).isEmpty();
    }

}
