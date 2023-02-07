//package com.naimuri.wordsquare.WordSquareChallenge.fileio;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpMethod;
//import org.springframework.web.client.ResponseExtractor;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//public class DictionaryParserTest {
//
//    private static final String URL = "http://norvig.com/ngrams/enable1.txt";
//
//    @Mock
//    private RestTemplate restTemplate;
//
//    @InjectMocks
//    private DictionaryParser dictionaryParser;
//
//    @Test
//    public void getDictionary_shouldMakeACallToExpectedUrl() {
//        when(restTemplate.execute(anyString(), any(HttpMethod.class), eq(null), any(ResponseExtractor.class))).thenReturn(List.of());
//        dictionaryParser.getDictionary(1, "a");
//        verify(restTemplate).execute(eq(URL), eq(HttpMethod.GET), eq(null), any(ResponseExtractor.class));
//    }
//
//    @Test
//    public void isValidWord_ReturnsTrueWhenWordExists() {
//        when(restTemplate.execute(anyString(), any(HttpMethod.class), eq(null), any(ResponseExtractor.class))).thenReturn(List.of("word"));
//        dictionaryParser.afterPropertiesSet();
//        assertThat(dictionaryParser.isValidWord("word")).isTrue();
//    }
//
//    @Test
//    public void isValidWord_ReturnsFalseWhenWordDoesNotExist() {
//        when(restTemplate.execute(anyString(), any(HttpMethod.class), eq(null), any(ResponseExtractor.class))).thenReturn(List.of("word"));
//        dictionaryParser.afterPropertiesSet();
//        assertThat(dictionaryParser.isValidWord("hello")).isFalse();
//    }
//}
