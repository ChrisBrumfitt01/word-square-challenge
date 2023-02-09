package com.naimuri.wordsquare.WordSquareChallenge.services;

import com.naimuri.wordsquare.WordSquareChallenge.exceptions.NoValidSolutionException;
import com.naimuri.wordsquare.WordSquareChallenge.http.DictionaryHttpReader;
import com.naimuri.wordsquare.WordSquareChallenge.model.Dictionary;
import com.naimuri.wordsquare.WordSquareChallenge.model.WordSquare;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WordSquareServiceTest {

    @Mock
    private WordSquareBuilder wordSquareBuilder;
    @Mock
    private DictionaryHttpReader dictionaryHttpReader;

    @Captor
    ArgumentCaptor<LinkedList<String>> listCaptor;

    @InjectMocks
    private WordSquareService service;

    private final String letters = "abcde";
    private final int size = 2;
    private final List<String> words = List.of("words", "more");
    private final Dictionary dictionary = new Dictionary(words);

    @BeforeEach
    public void beforeEach(){
        when(dictionaryHttpReader.getDictionary(anyInt(), anyString())).thenReturn(dictionary);
        when(wordSquareBuilder.build(any(LinkedList.class), anyList(), anyInt(), anyString())).thenReturn(true);

        service.solveWordSquare(size, letters);
    }

    @Test
    public void shouldCallWordSquareBuilder_withExpectedParams() {
        verify(wordSquareBuilder).build(listCaptor.capture(), eq(words), eq(size), eq(letters));
        List<String> actualGrid = listCaptor.getValue();
        assertThat(actualGrid).isEmpty();
    }

    @Test
    public void shouldReturnWordSquare_whenWordSquareBuilderIsSuccessful() {
        String letters = "abcde";
        int size = 2;
        List<String> words = List.of("words", "more");
        Dictionary dictionary = new Dictionary(words);

        when(dictionaryHttpReader.getDictionary(anyInt(), anyString())).thenReturn(dictionary);
        when(wordSquareBuilder.build(any(LinkedList.class), anyList(), anyInt(), anyString())).thenReturn(true);

        WordSquare actual = service.solveWordSquare(size, letters);
        assertThat(actual).isNotNull();
    }

    @Test
    public void shouldThrowException_whenWordSquareBuilderIsNotSuccessful() {
        String letters = "abcde";
        int size = 2;
        List<String> words = List.of("words", "more");
        Dictionary dictionary = new Dictionary(words);

        when(dictionaryHttpReader.getDictionary(anyInt(), anyString())).thenReturn(dictionary);
        when(wordSquareBuilder.build(any(LinkedList.class), anyList(), anyInt(), anyString())).thenReturn(false);
        assertThatThrownBy(() -> service.solveWordSquare(size, letters))
                .isInstanceOf(NoValidSolutionException.class);
    }

}
