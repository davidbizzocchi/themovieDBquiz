package moviedbquiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

public class MatchingQuestion {

	private String movieDesc;
	private ArrayList<String> possibleAnswers;
	private List<MovieDb> movieList;
	private int answerIndex;
	private int movieIndex;
	private boolean correctAnswer;
	private TmdbApi tmdbApi;
	private TmdbMovies tmdbMovies;
	
	public MatchingQuestion(){
		movieList = new ArrayList<MovieDb>();
		possibleAnswers = new ArrayList<String>();
		tmdbApi = new TmdbApi("72094b969b9993f31aeea13bb041ee86");
		movieIndex = 0;
		tmdbMovies = tmdbApi.getMovies();
		populateMovieList();
		randomizeMovieList();
	}
	
	public List<MovieDb> getMovieList(){
		return movieList;
	}
	
	public void addAnswerTitle(String title) {
		possibleAnswers.add(title);
	}
	
	public String getMovieDesc() {
		return movieDesc;
	}
	
	public ArrayList<String> getPossibleAnswers() {
		return possibleAnswers;
	}
	
	public int getAnswerIndex() {
		return answerIndex;
	}
	
	public boolean isCorrectAnswer() {
		return correctAnswer;
	}
	
	public void setMovieDesc(String movieDesc) {
		this.movieDesc = movieDesc;
	}
	
	public void setAnswerIndex(int answerIndex) {
		this.answerIndex = answerIndex;
	}
	
	public void setCorrectAnswer(boolean correctAnswer) {
		this.correctAnswer = correctAnswer;
	} 
	
	public MatchingQuestion generateQuestion(){
		if(!possibleAnswers.isEmpty()){
			possibleAnswers.clear();
		}
		
		if(movieIndex+4 >= movieList.size()){
			randomizeMovieList();
			movieIndex = 0;
		}
			
		for(int i = 0; i < 4; i++){
			addAnswerTitle(movieList.get(movieIndex++).getTitle());
		}
		
		setAnswerIndex(Randomize.randomInt(1, 4));
		setMovieDesc(movieList.get(getAnswerIndex()-1).getOverview());
		
		return this;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(String title : possibleAnswers){
			sb.append(title + '\n');
		}
		return sb.toString();
	}
	
	private void populateMovieList(){
		int i = 0;
		int previousSize = 0;
		MovieResultsPage results;
		
		do{
			previousSize = movieList.size();
			
			results = tmdbMovies.getNowPlayingMovies("en", i++);
			
			for( MovieDb movie : results.getResults()){
				movieList.add(movie);
			}
			
		}while(previousSize != movieList.size());
	}
	
	private void randomizeMovieList(){
		movieList = Randomize.shuffleList(movieList);
	}
}

