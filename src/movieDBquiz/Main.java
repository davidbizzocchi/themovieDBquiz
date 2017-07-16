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
		
		//MatchingQuestion q = new MatchingQuestion();
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuizDemo window = new QuizDemo();
					window.AppDemo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
