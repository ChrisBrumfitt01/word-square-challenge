package com.naimuri.wordsquare.WordSquareChallenge.fileio;

import com.naimuri.wordsquare.WordSquareChallenge.model.Dictionary;
import com.naimuri.wordsquare.WordSquareChallenge.util.WordUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DictionaryParserTest {

    private static final String URL = "http://norvig.com/ngrams/enable1.txt";

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private WordUtil wordUtil;

    @InjectMocks
    private DictionaryParser dictionaryParser;

    @Test
    public void getDictionary_shouldMakeCallToExpectedUrl() {
        when(restTemplate.getForObject(URL, String.class)).thenReturn("test");
        dictionaryParser.getDictionary(4, "test");
        verify(restTemplate).getForObject(URL, String.class);
    }

    @Test
    public void getDictionary_shouldFilterOnWordLength(){
        String response = "word\ntoo\nnot\nrose";
        when(restTemplate.getForObject(URL, String.class)).thenReturn(response);
        when(wordUtil.isAnagram(any(), any())).thenReturn(true);
        Dictionary actual = dictionaryParser.getDictionary(4, "test");
        assertThat(actual.getWords()).containsExactly("word", "rose");
    }

    @Test
    public void getDictionary_shouldFilterOnResponseFromIsAnagram(){
        String letters = "abc";
        String response = "word\nrose";
        when(restTemplate.getForObject(URL, String.class)).thenReturn(response);
        when(wordUtil.isAnagram("word", letters)).thenReturn(true);
        when(wordUtil.isAnagram("rose", letters)).thenReturn(false);
        Dictionary actual = dictionaryParser.getDictionary(4, letters);
        assertThat(actual.getWords()).containsExactly("word");
    }

    @Test
    public void getDictionary_shouldConvertWordsToLowerCase(){
        String response = "Word\nROSE";
        when(restTemplate.getForObject(URL, String.class)).thenReturn(response);
        when(wordUtil.isAnagram(any(), any())).thenReturn(true);
        Dictionary actual = dictionaryParser.getDictionary(4, "test");
        assertThat(actual.getWords()).containsExactly("word", "rose");
    }

}
