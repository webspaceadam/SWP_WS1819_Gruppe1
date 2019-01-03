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
 * Interface der Pinnwandverwaltung
 */
public interface PinnwandverwaltungAsync {
	
	/**
	 * Methode die alle User als Vector zurueck gibt
	 */
	public void showAllUser(AsyncCallback callback);
	
	/**
	 * Methode um einen User zu erzeugen
	 */
	public void createUser(String firstName, String lastName, String nickName, String gMail, Timestamp timestamp, AsyncCallback callback);
	
	/**
	 * Methode um einen User zu Bearbeiten
	 */
	public void editUser(User u, AsyncCallback callback);
	
	/**
	 * Methode um einen User zu Loeschen
	 */
	public void deleteUser(User u, AsyncCallback callback);
	
	/**
	 * Methode zur Ueberpruefung der Zugangsberechtigung 
	 */
	public void loginCheck(String nickname, String password, AsyncCallback callback);
	
	/**
	 * Methode um einen User anhand seiner ID zu suchen
	 */
	public void searchUserById(int userId, AsyncCallback callback);
	/**
	 * Methode um einen User upzudaten
	 */
	public void updateUser(User u, AsyncCallback callback);
	
	/**
	 * Methode um einen User anhand seines Nicknamens zu suchen
	 */
	public void searchUserByNickname(String nickname, AsyncCallback callback);
	
	/**
	 * Methode um einen Beitrag zu erzeugen
	 */
	public void createBeitrag(String text, User user, Timestamp timeStamp, AsyncCallback callback);
	
	/**
	 * Methode um alle Beiträge eines Users auszugeben
	 */
	public void findAllBeitraegeOfUser(User u, AsyncCallback callback);
	/**
	 * Methode um einen Beitrag zu Loeschen
	 */
	public void deleteBeitrag(Beitrag b, AsyncCallback callback);
	
	/**
	 * Methode um einen Beitrag zu Bearbeiten
	 */
	public void editBeitrag(Beitrag b, AsyncCallback callback);
	
	/**
	 * Methode um alle Abonnements eines Users anzuzeigen
	 */
	public void showAllAbonnementsByUser(User u, AsyncCallback callback);
	
	/**
	 * Methode um ein neues Abonnement zu erzeugen
	 */
	public void createAbonnement(User u, Pinnwand p, AsyncCallback callback);
	
	/**
	 * Methode um ein bestehendes Abonnement zu Loeschen
	 */
	public void deleteAbonnement(Abonnement a, AsyncCallback callback);
	
	/**
	 * Methode um einen neues Kommentar zu erzeugen
	 */
	public void createKommentar(String text, int userId, int beitragId, Timestamp timeStamp, AsyncCallback callback);
	
	/**
	 * Methode zum Loeschen eines Kommentars
	 */
	public void deleteKommentar(Kommentar k, AsyncCallback callback);
	
	/**
	 * Methode zum anzeigen aller Kommentare
	 */
	public void findAllKommentareOfBeitrag(Beitrag b, AsyncCallback callback);
	
	/**
	 * Methode zum Bearbeiten eines Kommentars
	 */
	public void editKommentar(Kommentar k, AsyncCallback callback);
	
	/**
	 * Methode zum erzeugen eines Likes
	 */
	public void createLike(User u, Beitrag b, AsyncCallback callback);
	
	/**
	 * Methode zur Ueberpruefung ob der Beitrag bereits geliket ist
	 */
	public void likeCheck(User u, Beitrag b, AsyncCallback callback);
	
	/**
	 * Methode um einen Beitrag zu entliken
	 */
	public void deleteLike(Like l, AsyncCallback callback);
	
	/**
	 * Methode um ein Like zu suchen
	 */
	public void searchLike(Like l, AsyncCallback callback);
	
	/**
	 * Methode um alle Likes eines Beitrags zu zaehlen
	 */
	public void countLikes(Beitrag b, AsyncCallback callback);
	
	/**
	 * Methode um Likes eines Beitrags zu entfernen
	 */
	public void deleteLikesOfBeitrag(Beitrag b, AsyncCallback callback);
	
	

}