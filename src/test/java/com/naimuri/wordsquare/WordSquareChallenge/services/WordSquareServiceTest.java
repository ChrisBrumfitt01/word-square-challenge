package com.naimuri.wordsquare.WordSquareChallenge.services;

import com.naimuri.wordsquare.WordSquareChallenge.exceptions.NoValidSolutionException;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class WordSquareServiceTest {

    @Mock
    private WordSquareBuilder wordSquareBuilder;
    @Mock
    private WordFinder wordFinder;

    @Captor
    ArgumentCaptor<LinkedList<String>> listCaptor;

    @InjectMocks
    private WordSquareService service;

    @Test
    public void shouldCallWordFinder_withExpectedParams() {
        String letters = "abcde";
        int size = 2;
        when(wordSquareBuilder.build(any(LinkedList.class), anyList(), anyInt(), anyString())).thenReturn(true);

        service.solveWordSquare(size, letters);

        verify(wordFinder).getWords(size, letters);
    }

    @Test
    public void shouldCallWordSquareBuilder_withExpectedParams() {
        String letters = "abcde";
        int size = 2;
        List<String> words = List.of("words", "more");
        when(wordFinder.getWords(anyInt(), anyString())).thenReturn(words);
        when(wordSquareBuilder.build(any(LinkedList.class), anyList(), anyInt(), anyString())).thenReturn(true);

        service.solveWordSquare(size, letters);

        verify(wordSquareBuilder).build(listCaptor.capture(), eq(words), eq(size), eq(letters));
        List<String> actualGrid = listCaptor.getValue();
        assertThat(actualGrid).isEmpty();
    }

    @Test
    public void shouldReturnGrid_whenWordSquareBuilderIsSuccessful() {
        String letters = "abcde";
        int size = 2;

        when(wordFinder.getWords(anyInt(), anyString())).thenReturn(List.of());
        when(wordSquareBuilder.build(any(LinkedList.class), anyList(), anyInt(), anyString())).thenReturn(true);

        List<String> actual = service.solveWordSquare(size, letters);
        assertThat(actual).isNotNull();
    }

    @Test
    public void shouldThrowException_whenWordSquareBuilderIsNotSuccessful() {
        String letters = "abcde";
        int size = 2;
        when(wordFinder.getWords(anyInt(), anyString())).thenReturn(List.of());
        when(wordSquareBuilder.build(any(LinkedList.class), anyList(), anyInt(), anyString())).thenReturn(false);
        assertThatThrownBy(() -> service.solveWordSquare(size, letters))
                .isInstanceOf(NoValidSolutionException.class);
    }

}
