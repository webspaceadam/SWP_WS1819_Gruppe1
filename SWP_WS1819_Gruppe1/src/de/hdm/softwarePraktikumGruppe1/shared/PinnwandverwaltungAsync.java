/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.softwarePraktikumGruppe1.shared.bo.*;

/**
 * @author gianluca
 * Interface der Pinnwandverwaltung
 */
public interface PinnwandverwaltungAsync {
	
	/**
	 * Methode zum erstellen eines Beitrags
	 */
	public void createBeitrag(String inhalt, AsyncCallback callback);
	
	/**
	 * Methode zum erstellen eines Kommentars
	 */
	public void createKommentar(String inhalt, AsyncCallback callback);
	
	/**
	 * Methode zum anzeigen aller Pinnwände
	 */
	public void showAllPinnwaende(AsyncCallback callback);
	
	/**
	 * Methode zum erstellen eines Users
	 */
	public User createUser(User u, AsyncCallback callback);
	
	/**
	 * Methode zum bearbeiten eines Users
	 */
	public User editUser(User u, AsyncCallback callback);
	
	/**
	 * Methode zum loeschen eines Users
	 */
	public void deleteUser(User u, AsyncCallback callback);
	
	/**
	 * Methode zum anzeigen aller User
	 */
	public ArrayList<User> showAllUser(AsyncCallback callback);
	
	/**
	 * Methode zur pruefung der Zugangsberechtigung 
	 */
	public String loginCheck(AsyncCallback callback);
	
	/**
	 * Methode zum suchen einer User ID
	 */
	public int searchUserId(int userId, AsyncCallback callback);
	
	/**
	 * Methode zum updaten eines Users
	 */
	public User updateUser(User u, AsyncCallback callback);
	
	/**
	 * Methode zum anzeigen aller Beitraege eines Users
	 */
	public ArrayList<Beitrag> findAllUserBeitraege(User u, AsyncCallback callback);
	
	/**
	 * Methode zum loeschen eines Beitrags
	 */
	public void deleteBeitrag(Beitrag b, AsyncCallback callback);
	
	/**
	 * Methode zum bearbeiten eines Beitrags
	 */
	public Beitrag editBeitrag(Beitrag b, AsyncCallback callback);
	
	/**
	 * Methode zum anzeigen aller User Abonnements
	 */
	public ArrayList<Abonnement> showAllUserAbonnements(User u, AsyncCallback callback);
	
	/**
	 * Methode zum anzeigen aller Kommentare eines Users
	 */
	public ArrayList<Kommentar> findAllUserKommentare(Kommentar k, AsyncCallback callback);
	
	/**
	 * Methode zum suchen eines Beitrags anhand der Pinnwand
	 */
	public ArrayList<Beitrag> searchBeitragByPinnwand(Pinnwand p, AsyncCallback callback);
	
	/**
	 * Methode zum anzeigen eines Users anhand seiner IDs
	 */
	public User getUserByNickname(String nickname, AsyncCallback callback);
	
	/**
	 * Methode zum erstellen eines Abonnements
	 */
	public Abonnement createAbonnement(User u, AsyncCallback callback);
	
	/**
	 * Methode zum loeschen eines Abonnements
	 */
	public void deleteAbonnement(Abonnement abo, AsyncCallback callback);
	
	/**
	 * Methode zum erstellen eines Likes
	 */
	public Like createLike(User u, AsyncCallback callback);
	
	/**
	 * Methode zum bearbeiten eines Kommentars
	 */
	public Kommentar editKommentar(Kommentar k, AsyncCallback callback);
	
	/**
	 * Methode zum ueberpruefen ob ein Beitrag schon von eienm User geliket wurde
	 */
	public Boolean likeCheck(User u, Beitrag b, AsyncCallback callback);
	
	/**
	 * Methode zum loeschen eines Likes
	 */
	public void deleteLike(Like l, AsyncCallback callback);
	
	/**
	 * Methode zum suchen eines Likes
	 */
	public Like searchLike(Like l, AsyncCallback callback);
	
	/**
	 * Methode zum zählen aller Likes eines Beitrags
	 */
	public int countLike(Beitrag b, AsyncCallback callback);
	
	

}