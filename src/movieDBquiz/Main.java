package movieDBquiz;

import info.movito.themoviedbapi.TmdbApi;

import java.awt.EventQueue;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbAuthentication;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.config.TokenSession;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.core.SessionToken;

public final class Main {

	public static void main(String[] args) {
		//Initializes session token and API key
//		DbManager manager = new DbManager();
//		
//		
//		// generates quiz question with 4 possible answers
//		MatchingQuestion q = new MatchingQuestion();
//		System.out.print(q.toString());
		
		
		
		MatchingQuestion q = new MatchingQuestion();
		q = q.generateQuestion();
		
		QuizDemo window = new QuizDemo();
		window.setAnswers(q.getPossibleAnswers());
		window.setDescription(q.getMovieDesc());
		int answer = q.getAnswerIndex() + 64;
		System.out.print((char)answer);
		window.setAnswer((char) answer);
		window.AppDemo.setVisible(true);
		
		
	}

}
