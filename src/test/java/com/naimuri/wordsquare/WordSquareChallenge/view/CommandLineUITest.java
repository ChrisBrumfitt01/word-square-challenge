package com.naimuri.wordsquare.WordSquareChallenge.view;

import com.naimuri.wordsquare.WordSquareChallenge.exceptions.NoValidSolutionException;
import com.naimuri.wordsquare.WordSquareChallenge.model.WordSquare;
import com.naimuri.wordsquare.WordSquareChallenge.services.WordSquareService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommandLineUITest {

    @Mock
    private UserInputReader userInputReader;
    @Mock
    private UserOutputWriter userOutputWriter;
    @Mock
    private WordSquareService wordSquareService;

    @InjectMocks
    private CommandLineUI commandLineUI;

    @Test
    public void shouldCallSolveWordSquare_WithExpectedInputs() throws NoValidSolutionException {
        int size = 3;
        String letters = "abc";
        when(userInputReader.readInt()).thenReturn(size);
        when(userInputReader.readString()).thenReturn(letters);
        when(wordSquareService.solveWordSquare(size, letters)).thenReturn(new WordSquare(List.of()));

        commandLineUI.run();

        verify(wordSquareService).solveWordSquare(size, letters);
    }

    @Test
    public void shouldOutputWordSquare_whenSuccessful() throws NoValidSolutionException {
        int size = 3;
        String letters = "abc";
        WordSquare wordSquare = new WordSquare(List.of("one", "two"));
        when(userInputReader.readInt()).thenReturn(size);
        when(userInputReader.readString()).thenReturn(letters);
        when(wordSquareService.solveWordSquare(size, letters)).thenReturn(wordSquare);

        commandLineUI.run();

        verify(userOutputWriter).writeLine("one\ntwo");
    }

    @Test
    public void shouldOutputError_whenUnsuccessful() throws NoValidSolutionException {
        int size = 3;
        String letters = "abc";
        String error = "error";
        when(userInputReader.readInt()).thenReturn(size);
        when(userInputReader.readString()).thenReturn(letters);
        doThrow(new NoValidSolutionException(error)).when(wordSquareService).solveWordSquare(size, letters);

        commandLineUI.run();

        verify(userOutputWriter).writeLine(error);
    }
}
