/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.softwarePraktikumGruppe1.shared.bo.*;

/**
 * @author GianlucaBernert
 * @author SebastianHermann
 * @author Yesin Soufi
 * Interface der Pinnwandverwaltung
 */
public interface PinnwandverwaltungAsync{
	/**
	 * Methode um einen User zu erzeugen
	 * @param firstName
	 * @param lastName
	 * @param nickName
	 * @param gMail
	 * @param timestamp
	 */
	
	public void createUser(String firstName, String lastName, String nickName, String gMail, Timestamp timestamp, AsyncCallback <User> callback);
	
	/**
	 * Methode um einen User zu Bearbeiten
	 * @param u
	 * @param callback
	 */
	
	public void editUser(User u, AsyncCallback <Void> callback);
	
	/**
	 * Methode um einen User zu Loeschen
	 * @param u
	 * @param callback
	 */
	
	public void deleteUser(User u, AsyncCallback<Void> callback);
	
	/**
	 * Methode zur Ueberpruefung der Zugangsberechtigung 
	 * @param nickname
	 * @param password
	 * @param callback
	 */
	
	public void loginCheck(String nickname, String password, AsyncCallback<User> callback);
	
	/**
	 * Methode um einen User anhand seiner ID zu suchen
	 * @param userId
	 * @param callback
	 */
	
	public void getUserById(int userId, AsyncCallback<User> callback);
	
	/**
	 * Methode um einen User anhand seines Nicknamens zu suchen
	 * @param nickname
	 * @param callback
	 */
	
	public void getUserByNickname(String nickname, AsyncCallback<Vector<User>> callback);
	
	/**
	 * Methode um einen Beitrag zu erzeugen
	 * @param text
	 * @param user
	 * @param timeStamp
	 * @param callback
	 */
	
	public void createBeitrag(String text, User user, Timestamp timeStamp, AsyncCallback<Beitrag> callback);
	
	/**
	 * Methode um alle Beiträge eines Users auszugeben
	 * @param u
	 * @param callback
	 */
	
	public void getAllBeitraegeOfUser(User u, AsyncCallback<Vector<Beitrag>> callback);
	/**
	 * Methode um einen Beitrag zu Loeschen
	 * @param b
	 * @param callback
	 */
	public void deleteBeitrag(Beitrag b, AsyncCallback<Void> callback);
	
	/**
	 * Methode um einen Beitrag zu Bearbeiten
	 * @param b
	 * @param callback
	 */
	public void editBeitrag(Beitrag b, AsyncCallback<Beitrag> callback);
	
	/**
	 * Methode um alle Abonnements eines Users anzuzeigen
	 * @param u
	 * @param callback
	 */
	public void showAllAbonnementsByUser(User u, AsyncCallback<Vector<Abonnement>> callback);
	
	/**
	 * Methode um ein Abonnement zu erzeugen
	 * @param u1
	 * @param u2
	 * @param timestamp
	 * @param callback
	 */
	void createAbonnement(User u1, User u2, Timestamp timestamp, AsyncCallback<Abonnement> callback);
	
	/**
	 * Methode um ein bestehendes Abonnement zu Loeschen
	 * @param a
	 * @param callback
	 */
	
	public void deleteAbonnement(Abonnement a, AsyncCallback<Void> callback);
	
	/**
	 * Methode um einen neues Kommentar zu erzeugen
	 * @param text
	 * @param userId
	 * @param beitragId
	 * @param timeStamp
	 * @param callback
	 */
	
	public void createKommentar(String text, int userId, int beitragId, Timestamp timeStamp, AsyncCallback<Kommentar> callback);
	
	/**
	 * Methode zum Loeschen eines Kommentars
	 * @param k
	 * @param callback
	 */
	
	public void deleteKommentar(Kommentar k, AsyncCallback<Void> callback);
	
	/**
	 * Methode zum anzeigen aller Kommentare
	 * @param b
	 * @param callback
	 */
	
	public void getAllKommentareOfBeitrag(Beitrag b, AsyncCallback<Vector<Kommentar>> callback);
	
	/**
	 * Methode zum Bearbeiten eines Kommentars
	 * @param k
	 * @param callback
	 */
	
	public void editKommentar(Kommentar k, AsyncCallback<Void> callback);
	
	/**
	 * Methode zum erzeugen eines Likes
	 * @param u
	 * @param b
	 * @param timestamp
	 * @param callback
	 */
	
	public void createLike(User u, Beitrag b, Timestamp timestamp, AsyncCallback<Like> callback);
	
	/**
	 * Methode zur Ueberpruefung ob der Beitrag bereits geliket ist (like)
	 * @param u
	 * @param b
	 * @param callback
	 */
	
	public void likeCheck(User u, Beitrag b, AsyncCallback<Like> callback);
	
	/**
	 * Methode um einen Beitrag zu entliken
	 * @param l
	 * @param callback
	 */
	
	public void deleteLike(Like l, AsyncCallback<Boolean> callback);
	
	/**
	 * Methode um alle Likes eines Beitrags zu zaehlen
	 * @param b
	 * @param callback
	 */
	
	public void countLikes(Beitrag b, AsyncCallback<Integer> callback);
	
	/**
	 * Methode um Likes eines Beitrags zu entfernen
	 * @param b
	 * @param callback
	 */
	
	public void deleteLikesOfBeitrag(Beitrag b, AsyncCallback<Void> callback);
	
	/**
	 * Methode um eine Pinnwand zu erzeugen
	 * @param u
	 * @param timestamp
	 * @param callback
	 */
	
	void createPinnwand(User u, Timestamp timestamp, AsyncCallback<Pinnwand> callback);
	
	/**
	 * Methode um die Pinnwand zu löschen
	 * @param p
	 * @param callback
	 */

	void deletePinnwand(Pinnwand p, AsyncCallback<Void> callback);
	
	/**
	 * Methode um die Anzahl der Beiträge des Users zu zählen
	 * @param u
	 * @param callback
	 */

	void getBeitragAmountOfUser(User u, AsyncCallback<Integer> callback);
	
	/**
	 * Methode um die Pinnwand des Users zurückzugeben
	 * @param u
	 * @param callback
	 */

	void getPinnwandOfUser(User u, AsyncCallback<Pinnwand> callback);
	
	/**
	 * Methode um den User nach Vorname zurückzugeben 
	 * @param fName
	 * @param callback
	 */

	void getUserByFirstName(String fName, AsyncCallback<Vector<User>> callback);
	
	/**
	 * Methode um den User nach Gmail zurückzugeben
	 * @param gMail
	 * @param callback
	 */

	void getUserByGmail(String gMail, AsyncCallback<User> callback);
	
	/**
	 * Methode um den User nach Nachname zurückzugeben
	 * @param lName
	 * @param callback
	 */

	void getUserByLastName(String lName, AsyncCallback<Vector<User>> callback);
	
	/**
	 * Methode um die Pinnwand nach der Id zurückzugeben
	 * @param pinnwandId
	 * @param callback
	 */

	void getPinnwandById(int pinnwandId, AsyncCallback<Pinnwand> callback);
	
	/**
	 * Methode um einen User zu suchen
	 * @param searchQuery
	 * @param callback
	 */

	void searchFunction(String searchQuery, AsyncCallback<Vector<User>> callback);
	
	/**
	 * Methode um zu checken ob ein User schon abonniert wurde
	 * @param u
	 * @param p
	 * @param callback
	 */

	void abonnementCheck(User u, Pinnwand p, AsyncCallback<Boolean> callback);
	
	/**
	 * Methode um die Abonnements des User zurückzugeben
	 * @param u1
	 * @param u2
	 * @param callback
	 */

	void getAbonnementBetweenUsers(User u1, User u2, AsyncCallback<Abonnement> callback);
	
		

}