//package com.naimuri.wordsquare.WordSquareChallenge.fileio;
//
//import com.naimuri.wordsquare.WordSquareChallenge.util.WordUtil;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.mockito.stubbing.Answer;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpMethod;
//import org.springframework.web.client.ResponseExtractor;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class DictionaryParserTest {
//
//    private static final String URL = "http://norvig.com/ngrams/enable1.txt";
//
//    @Mock
//    private RestTemplate restTemplate;
//
//    @Mock
//    private WordUtil wordUtil;
//
//    @InjectMocks
//    private DictionaryParser dictionaryParser;
//
//    @Test
//    public void testGetDictionary() {
//        List<String> words = new ArrayList<>();
//        words.add("test");
//        words.add("word");
//
//        when(restTemplate.execute(anyString(), any(HttpMethod.class), any(), any()))
//                .thenAnswer(new Answer<List<String>>() {
//
//                });
//
//        when(mockWordUtil.isAnagram(anyString(), anyString()))
//                .thenReturn(true);
//
//        Dictionary result = parser.getDictionary(4, "test");
//        assertEquals(2, result.getWords().size());
//        assertTrue(result.getWords().containsAll(words));
//    }
//
////    @Test
////    public void getDictionary_shouldMakeACallToExpectedUrl() {
////        when(restTemplate.execute(anyString(), any(HttpMethod.class), eq(null), any(ResponseExtractor.class))).thenReturn(List.of());
////        dictionaryParser.getDictionary(1, "a");
////        verify(restTemplate).execute(eq(URL), eq(HttpMethod.GET), eq(null), any(ResponseExtractor.class));
////    }
////
////    @Test
////    public void isValidWord_ReturnsTrueWhenWordExists() {
////        when(restTemplate.execute(anyString(), any(HttpMethod.class), eq(null), any(ResponseExtractor.class))).thenReturn(List.of("word"));
////        dictionaryParser.afterPropertiesSet();
////        assertThat(dictionaryParser.isValidWord("word")).isTrue();
////    }
////
////    @Test
////    public void isValidWord_ReturnsFalseWhenWordDoesNotExist() {
////        when(restTemplate.execute(anyString(), any(HttpMethod.class), eq(null), any(ResponseExtractor.class))).thenReturn(List.of("word"));
////        dictionaryParser.afterPropertiesSet();
////        assertThat(dictionaryParser.isValidWord("hello")).isFalse();
////    }
//}
