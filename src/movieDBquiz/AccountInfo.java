package movieDBquiz;

import info.movito.themoviedbapi.model.config.Account;
import javafx.scene.image.Image;

public class AccountInfo {
	Account userAccount;
	DbManager manager;
	static Image profilePicture;
	
	public AccountInfo() {
		manager = new DbManager();
		userAccount = manager.getAccount();
	}
	
	public String getUserName(){
		return userAccount.getUserName();
	}
	
	public String getName(){
		return userAccount.getName();
	}
	
	public String getUserID(){
		return Integer.toString(userAccount.getId());
	}
	
	public void changeName(String name){
		userAccount.setName(name);
	}
}
