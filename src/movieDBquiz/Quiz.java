package movieDBquiz;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbAuthentication;
import info.movito.themoviedbapi.model.config.TokenSession;
import info.movito.themoviedbapi.model.core.SessionToken;

/**
 * Driver for the movie quiz.
 * @author David Bizzocchi, Josh Aitken, Caitlin Heyn
 * @version 1.0 Summer 2017
 */
public final class Quiz {
	
	/** Instance of the API to generate questions and answers. **/
	private static TmdbApi tmdbApi;
	
	/**
	 * Private consturctor because this is a useless utility class. 
	 **/
	private Quiz() { }
	/**
	 * Runs the code to learn the API.
	 * @param args console input.
	 */
	
	public static void main(final String[] args) {

		// (Hardcoded) my API code from themoviedb.com
		tmdbApi = new TmdbApi("72094b969b9993f31aeea13bb041ee86");

		getSessionToken();
	}
	/**
	 * gets the session token from the user provided so the API can be accessed.
	 * @return the session token to be used
	 */
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
		TokenSession tokenSession = tmdbAuth.getSessionLogin(username, 
				password);
		System.out.println("Session ID: " + tokenSession.getSessionId());
		SessionToken sessionToken = new 
				SessionToken(tokenSession.getSessionId());

		return sessionToken;
	}
	
	

}
