package com.naimuri.wordsquare.WordSquareChallenge;

import com.naimuri.wordsquare.WordSquareChallenge.services.WordSquareService;
import com.naimuri.wordsquare.WordSquareChallenge.view.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class WordSquareChallengeApplication implements CommandLineRunner {

	//TODO: Add validation
	//TODO: Add UI

	private final WordSquareService wordSquareService;
	private final UI ui;

	@Autowired
	public WordSquareChallengeApplication(final WordSquareService wordSquareService, final UI ui) {
		this.wordSquareService = wordSquareService;
		this.ui = ui;
	}

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(WordSquareChallengeApplication.class);
		application.setWebApplicationType(WebApplicationType.NONE);
		application.run(args);
	}

	@Override
	public void run(final String... args) throws Exception {
		ui.start();
	}
}
