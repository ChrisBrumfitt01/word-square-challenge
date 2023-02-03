package com.naimuri.wordsquare.WordSquareChallenge;

import com.naimuri.wordsquare.WordSquareChallenge.services.WordSquareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WordSquareChallengeApplication implements CommandLineRunner {

	@Autowired
	private WordSquareService wordSquareService;

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(WordSquareChallengeApplication.class);
		application.setWebApplicationType(WebApplicationType.NONE);
		application.run(args);
	}

	@Override
	public void run(final String... args) throws Exception {
//		wordSquareService.solveWordSquare(Integer.parseInt(args[0]), args[1]);
	}
}
