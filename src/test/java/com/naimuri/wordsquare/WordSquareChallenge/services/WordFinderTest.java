//package com.naimuri.wordsquare.WordSquareChallenge.services;
//
//import com.naimuri.wordsquare.WordSquareChallenge.fileio.DictionaryParser;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//public class WordFinderTest {
//
//    @Mock
//    private DictionaryParser dictionaryParser;
//
//    @InjectMocks
//    private WordFinder wordFinder;
//
//
//    @Test
//    public void shouldReturnWords_whenSizeIs1(){
//        when(dictionaryParser.isValidWord(any())).thenReturn(true);
//        List<String> actual = wordFinder.getWords(1, "abc");
//        assertThat(actual).containsExactlyInAnyOrder("a", "b", "c");
//    }
//
//    @Test
//    public void shouldNotReturnWords_whenSizeIs1_butAreInvalidWords(){
//        when(dictionaryParser.isValidWord(any())).thenReturn(false);
//        List<String> actual = wordFinder.getWords(1, "abc");
//        assertThat(actual).isEmpty();
//    }
//
//    @Test
//    public void shouldReturnDistinctWordsOnly_whenSizeIs1(){
//        when(dictionaryParser.isValidWord(any())).thenReturn(true);
//        List<String> actual = wordFinder.getWords(1, "aaa");
//        assertThat(actual).containsExactlyInAnyOrder("a");
//    }
//
//
//    @Test
//    public void shouldReturnWords_whenSizeIs2(){
//        when(dictionaryParser.isValidWord(any())).thenReturn(true);
//        List<String> actual = wordFinder.getWords(2, "abcd");
//        assertThat(actual).containsExactlyInAnyOrder("ab", "ac", "ad", "ba", "bc", "bd",
//                "ca", "cb", "cd", "da", "db", "dc");
//    }
//
//    @Test
//    public void shouldReturnNoWords_whenSizeIs2_butAreInvalidWords(){
//        when(dictionaryParser.isValidWord(any())).thenReturn(false);
//        List<String> actual = wordFinder.getWords(2, "abcd");
//        assertThat(actual).isEmpty();
//    }
//
//}
