package movieDBquiz;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

public class Question {

	private String movieDesc;
	private ArrayList<String> possibleAnswers;
	private int answerIndex;
	private boolean correctAnswer;
	
	public Question(){
		possibleAnswers = new ArrayList<String>();
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
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(String title : possibleAnswers){
			sb.append(title + '\n');
		}
		return sb.toString();
	}
	
	
}
