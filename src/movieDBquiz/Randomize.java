package movieDBquiz;

import java.security.SecureRandom;
import java.util.Random;
import java.util.List;
import java.util.Collections;
import info.movito.themoviedbapi.model.MovieDb;

public class Randomize {
	private static Random rand;
	
	public Randomize() {
		rand = new SecureRandom();
	}
	
	public int randomInt(){
		reSeedTime();
		return rand.nextInt();
	}
	
	public float randomFloat(){
		reSeedTime();
		return rand.nextFloat();
	}
	
	public List<MovieDb> shuffleList(List<MovieDb> list){
		Collections.shuffle(list, rand);
		return list;
	}
	
	private void reSeedTime(){
		rand.setSeed(System.currentTimeMillis());
	}
	
}
