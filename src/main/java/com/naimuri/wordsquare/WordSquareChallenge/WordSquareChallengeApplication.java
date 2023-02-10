package com.naimuri.wordsquare.WordSquareChallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WordSquareChallengeApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(WordSquareChallengeApplication.class);
		application.setWebApplicationType(WebApplicationType.NONE);
		application.run(args);
	}

}
