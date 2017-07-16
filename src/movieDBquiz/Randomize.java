package movieDBquiz;

import java.security.SecureRandom;
import java.util.Random;
import java.util.List;
import java.util.Collections;
import info.movito.themoviedbapi.model.MovieDb;

public class Randomize {
	private static Random rand = new SecureRandom();
	
	public static int randomInt(int min, int max){
		reSeedTime();
		return rand.nextInt(((max - min) + 1) + min);
	}
	
	public static float randomFloat(float min, float max){
		reSeedTime();
		return rand.nextFloat() * (max - min) + min;
	}
	
	public static List<MovieDb> shuffleList(List<MovieDb> list){
		Collections.shuffle(list, rand);
		return list;
	}
	
	private static void reSeedTime(){
		rand.setSeed(System.currentTimeMillis());
	}
	
}
