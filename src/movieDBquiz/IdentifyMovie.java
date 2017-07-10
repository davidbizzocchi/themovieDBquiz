package movieDBquiz;

import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

public class IdentifyMovie {
	
	
	
	public IdentifyMovie() {
//		TmdbMovies tmdbMovies = tmdbApi.getMovies();
//		MovieResultsPage results = tmdbMovies.getNowPlayingMovies("en", 0);
//		Iterator<MovieDb> iterator = results.iterator();
//		MovieDb movie = new MovieDb();
//		
//		// .getNowPlayingMovies returns size of 20
//		//int answerLocation = ThreadLocalRandom.current().nextInt(16)+4;
//		
//		// set randomly selected movie to "correct" movie in Question object
//		for(int i = 0; i < answerLocation; ++i){
//			movie = iterator.next();
//		}
//		
//		q.setMovieDesc(movie.getOverview());
//		String answerTitle = movie.getTitle();
//		System.out.println("\n\n" + q.getMovieDesc() + "\n\n");	// prints movie desc to console
//		
//		iterator = results.iterator();	// reset iterator to start of results
//		
//		q.setAnswerIndex(ThreadLocalRandom.current().nextInt(numAnswers));
	}
	
	
	public int getRandomIndex(){
		return ThreadLocalRandom.current().nextInt(16)+4;
	}
	
	
}
