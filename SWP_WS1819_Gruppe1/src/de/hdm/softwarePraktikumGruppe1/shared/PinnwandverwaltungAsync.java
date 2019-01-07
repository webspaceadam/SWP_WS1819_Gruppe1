/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared;

import java.sql.Timestamp;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.softwarePraktikumGruppe1.shared.bo.*;

/**
 * @author GianlucaBernert
 * @author SebastianHermann
 * Interface der Pinnwandverwaltung
 */
public interface PinnwandverwaltungAsync{
	/**
	 * Methode um einen User zu erzeugen
	 */
	public void createUser(String firstName, String lastName, String nickName, String gMail, Timestamp timestamp, AsyncCallback <User> callback);
	
	/**
	 * Methode um einen User zu Bearbeiten
	 */
	public void editUser(User u, AsyncCallback <Void> callback);
	
	/**
	 * Methode um einen User zu Loeschen
	 */
	public void deleteUser(User u, AsyncCallback<Void> callback);
	
	/**
	 * Methode zur Ueberpruefung der Zugangsberechtigung 
	 */
	public void loginCheck(String nickname, String password, AsyncCallback<User> callback);
	
	/**
	 * Methode um einen User anhand seiner ID zu suchen
	 */
	public void getUserById(int userId, AsyncCallback<User> callback);
	
	/**
	 * Methode um einen User anhand seines Nicknamens zu suchen
	 */
	public void getUserByNickname(String nickname, AsyncCallback<Vector<User>> callback);
	
	/**
	 * Methode um einen Beitrag zu erzeugen
	 */
	public void createBeitrag(String text, User user, Timestamp timeStamp, AsyncCallback<Beitrag> callback);
	
	/**
	 * Methode um alle Beiträge eines Users auszugeben
	 */
	public void getAllBeitraegeOfUser(User u, AsyncCallback<Vector<Beitrag>> callback);
	/**
	 * Methode um einen Beitrag zu Loeschen
	 */
	public void deleteBeitrag(Beitrag b, AsyncCallback<Void> callback);
	
	/**
	 * Methode um einen Beitrag zu Bearbeiten
	 */
	public void editBeitrag(Beitrag b, AsyncCallback<Beitrag> callback);
	
	/**
	 * Methode um alle Abonnements eines Users anzuzeigen
	 */
	public void showAllAbonnementsByUser(User u, AsyncCallback<Vector<Abonnement>> callback);
	
	/**
	 * Methode um ein neues Abonnement zu erzeugen
	 */
	public void createAbonnement(User u, Pinnwand p, Timestamp timestamp, AsyncCallback<Abonnement> callback);
	
	/**
	 * Methode um ein bestehendes Abonnement zu Loeschen
	 */
	public void deleteAbonnement(Abonnement a, AsyncCallback<Void> callback);
	
	/**
	 * Methode um einen neues Kommentar zu erzeugen
	 */
	public void createKommentar(String text, int userId, int beitragId, Timestamp timeStamp, AsyncCallback<Kommentar> callback);
	
	/**
	 * Methode zum Loeschen eines Kommentars
	 */
	public void deleteKommentar(Kommentar k, AsyncCallback<Void> callback);
	
	/**
	 * Methode zum anzeigen aller Kommentare
	 */
	public void getAllKommentareOfBeitrag(Beitrag b, AsyncCallback<Vector<Kommentar>> callback);
	
	/**
	 * Methode zum Bearbeiten eines Kommentars
	 */
	public void editKommentar(Kommentar k, AsyncCallback<Void> callback);
	
	/**
	 * Methode zum erzeugen eines Likes
	 */
	public void createLike(User u, Beitrag b, Timestamp timestamp, AsyncCallback<Like> callback);
	
	/**
	 * Methode zur Ueberpruefung ob der Beitrag bereits geliket ist
	 */
	public void likeCheck(User u, Beitrag b, AsyncCallback<Like> callback);
	
	/**
	 * Methode um einen Beitrag zu entliken
	 */
	public void deleteLike(Like l, AsyncCallback<Void> callback);
	
	/**
	 * Methode um alle Likes eines Beitrags zu zaehlen
	 */
	public void countLikes(Beitrag b, AsyncCallback<Integer> callback);
	
	/**
	 * Methode um Likes eines Beitrags zu entfernen
	 */
	public void deleteLikesOfBeitrag(Beitrag b, AsyncCallback<Void> callback);
	
	void createPinnwand(User u, Timestamp timestamp, AsyncCallback<Pinnwand> callback);

	void deletePinnwand(Pinnwand p, AsyncCallback<Void> callback);

	void getBeitragAmountOfUser(User u, AsyncCallback<Integer> callback);

	void getPinnwandByUserId(int userId, AsyncCallback<Pinnwand> callback);

	void getUserByFirstName(String fName, AsyncCallback<Vector<User>> callback);

	void getUserByGmail(String gMail, AsyncCallback<User> callback);

	void getUserByLastName(String lName, AsyncCallback<Vector<User>> callback);
	
		

}