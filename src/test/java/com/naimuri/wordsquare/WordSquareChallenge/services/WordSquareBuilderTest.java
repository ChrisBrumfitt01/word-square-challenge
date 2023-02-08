package com.naimuri.wordsquare.WordSquareChallenge.services;

import com.naimuri.wordsquare.WordSquareChallenge.util.WordUtil;
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
    @Mock private WordUtil wordUtil;

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
    public void shouldReturnTrue_andPopulateGrid_whenGridNeeds2MoreWords_withSolutionAvailable() {
        String letters = "eyesetjimnice";
        String lettersWithoutEye = "setjimnice";
        LinkedList<String> grid = new LinkedList<>(List.of("yes"));
        when(prefixBuilder.buildPrefix(any())).thenReturn("e").thenReturn("se");
        when(wordUtil.stripLetters("eye", letters)).thenReturn(lettersWithoutEye);
        when(wordUtil.isAnagram("set",  lettersWithoutEye)).thenReturn(true);

        boolean result = builder.build(grid, List.of("eye", "set"), 3, letters);
        assertThat(result).isTrue();
        assertThat(grid).containsExactly("yes", "eye", "set");
    }


    @Test
    public void shouldReturnFalse_andRemoveFromGrid_whenGridNeeds2MoreWords_noSolutionAvailableForLastWord() {
        String letters = "eyejimnice";
        String lettersWithoutEye = "jimnice";
        LinkedList<String> grid = new LinkedList<>(List.of("yes"));
        when(prefixBuilder.buildPrefix(any())).thenReturn("e").thenReturn("se");
        when(wordUtil.stripLetters("eye", letters)).thenReturn(lettersWithoutEye);
        when(wordUtil.isAnagram("set",  lettersWithoutEye)).thenReturn(true);

        boolean result = builder.build(grid, List.of("eye", "jim", "nice"), 3, letters);
        assertThat(result).isFalse();
        assertThat(grid).isEmpty();
    }

}
