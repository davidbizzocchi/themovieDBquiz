package movieDBquiz;

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

public class Quiz {

	private static TmdbApi tmdbApi;
	private static SessionToken sessionToken;

	public static void main(String[] args) {

		// (Hardcoded) my API code from themoviedb.com
		tmdbApi = new TmdbApi("72094b969b9993f31aeea13bb041ee86");

		// retrieves session token using themoviedb ID and password
		sessionToken = getSessionToken();

		// generates quiz question with 4 possible answers
		Question q = generateQuestion(4);
	}

	private static SessionToken getSessionToken() {

		// Enter themoviedb.com username
		System.out.print("Enter user name: ");
		//Scanner scan = new Scanner(System.in);
		String username = "DavidBizzocchi"; //scan.nextLine(); // DavidBizzocchi

		// Enter the moviedb.com password
		System.out.print("Enter password: ");
		String password = "CIS350Project"; //scan.nextLine(); // CIS350Project
		//scan.close();

		// Generate session id using API calls
		TmdbAuthentication tmdbAuth = tmdbApi.getAuthentication();
		TokenSession tokenSession = tmdbAuth.getSessionLogin(username, password);
		System.out.println("Session ID: " + tokenSession.getSessionId());
		SessionToken sessionToken = new SessionToken(tokenSession.getSessionId());

		return sessionToken;

	}

	private static Question generateQuestion(int numAnswers) {

		Question q = new Question();
		TmdbMovies tmdbMovies = tmdbApi.getMovies();
		MovieResultsPage results = tmdbMovies.getNowPlayingMovies("en", 0);
		Iterator<MovieDb> iterator = results.iterator();
		MovieDb movie = new MovieDb();

		// .getNowPlayingMovies returns size of 20
		int answerLocation = ThreadLocalRandom.current().nextInt(16)+4;
		
		// set randomly selected movie to "correct" movie in Question object
		for(int i = 0; i < answerLocation; ++i){
			movie = iterator.next();
		}
		q.setMovieDesc(movie..getOverview());
		String answerTitle = movie.getTitle();
		System.out.println("\n\n" + q.getMovieDesc() + "\n\n");	// prints movie desc to console
		
		iterator = results.iterator();	// reset iterator to start of results
		
		q.setAnswerIndex(ThreadLocalRandom.current().nextInt(numAnswers));	// set answer index within number of answers
		
		// set quiz questions
		for(int i = 0; i < numAnswers; ++i){
			if(i != q.getAnswerIndex()){
				q.addAnswerTitle(i + ": " + iterator.next().getTitle());
			}
			else{
				q.addAnswerTitle(i + ": " + answerTitle);
			}
		}
		
		/***  below can be entirely removed when GUI is implemented **/
		System.out.println(q.toString());	// presents question to user

		Scanner Cin = new Scanner(System.in);
		System.out.print("Answer: ");
		int inputAnswer = Cin.nextInt();	// takes user input for answer
		Cin.close();
		
		// Display if answer was correct
		if(inputAnswer == q.getAnswerIndex()){
			System.out.println("\nCORRECT!");
		}
		else{
			System.out.println("\nINCORRECT!\nCorrect Answer: " + q.getAnswerIndex());
		}
		
		return q;

	}

}