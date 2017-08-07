package movieDBquiz;

import info.movito.themoviedbapi.model.config.Account;
import javafx.scene.image.Image;

/**
 * Wrapper class to access info. needed for accounts page.
 * @author David Bizzocchi, Josh Aitken, Caitlyn Heyn
 * @version 1.1
 */
public class AccountInfo {

	/** Field to store tmdb user account object.*/
	private Account userAccount;

	/** Field to provide access to movie/tv/account info.*/
	private DbManager manager;

	/** Field to hold a profile image that persists through app.*/
	private static Image profilePicture;

	/**
	 * Constructor to instantiate fields.
	 */
	public AccountInfo() {
		manager = new DbManager();
		userAccount = manager.getAccount();
	}

	/** Gets user according to current session within DbManager.
	 * @return the userName of current user.
	 */
	public String getUserName() {
		return userAccount.getUserName();
	}

	/**
	 * Gets the name of user according to current DbManager session.
	 * @return the name of the user in tmdb account.
	 */
	public String getName() {
		return userAccount.getName();
	}

	/**
	 * Gets the tmdb id of user.
	 * @return user id number as a string.
	 */
	public String getUserID() {
		return Integer.toString(userAccount.getId());
	}

	/**
	 * Accesses the current tmdb session account to change user name.
	 * @param name the new username to set.
	 */
	public void changeName(final String name) {
		userAccount.setName(name);
	}
}
