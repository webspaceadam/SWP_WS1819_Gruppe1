/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.softwarePraktikumGruppe1.shared.bo.Abonnement;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Kommentar;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Like;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Pinnwand;
import de.hdm.softwarePraktikumGruppe1.shared.bo.User;

/**
 * @author GianlucaBernert
 * @autor SebastianHermann
 * @author Yesin Soufi
 *
 */

@RemoteServiceRelativePath("PinnwandverwaltungImpl")
public interface Pinnwandverwaltung extends RemoteService {
	
	/**
	 * Methode um einen User zu erzeugen
	 * @param firstName
	 * @param lastName
	 * @param nickName
	 * @param gMail
	 * @param timestamp
	 */
	public User createUser(String firstName, String lastName, String nickName, String gMail, Timestamp timestamp);
	
	/**
	 * Methode um einen User zu Bearbeiten
	 * @param u
	 */
	public void editUser(User u);
	
	/**
	 * Methode um einen User zu Loeschen
	 * @param u
	 */
	public void deleteUser(User u);
	
	/**
	 * Methode zur Ueberpruefung der Zugangsberechtigung 
	 * @param nickname
	 * @param password
	 */
	public User loginCheck(String nickname, String password);
	
	/**
	 * Methode um einen User anhand seiner ID zu suchen
	 * @param userId
	 */
	public User getUserById(int userId);
	
	/**
	 * Methode um einen User anhand seines Nicknamens zu suchen
	 * @param nickname
	 */
	public Vector<User> getUserByNickname(String nickname);
	
	/**
	 * Methode um einen User anhand seines Vornamens zu suchen
	 * @param name
	 */
	
	public Vector<User> getUserByFirstName(String fName);
	
	/**
	 * Methode um einen User anhand seines Nachnamens zu suchen
	 * @param lName
	 */
	public Vector<User> getUserByLastName(String lName);
	
	/**
	 * Methode um einen User anhand seiner Gmail zu suchen
	 * @param gMail
	 */
	public User getUserByGmail(String gMail);
	
	
	/**
	 * Methode um einen Beitrag zu erzeugen
	 * @param text
	 * @param user
	 * @param timeStamp
	 */
	public Beitrag createBeitrag(String text, User user, Timestamp timeStamp);
	
	/**
	 * Methode um alle Beiträge eines Users auszugeben
	 * @param u
	 */
	public Vector<Beitrag> getAllBeitraegeOfUser(User u);
	
	/**
	 * Methode um die Anzahl der Beiträge des Users auszugeben
	 * @param u
	 */
	
	public int getBeitragAmountOfUser(User u);
	
	/**
	 * Methode um einen Beitrag zu Loeschen
	 * @param b
	 */
	
	public void deleteBeitrag(Beitrag b);
	
	/**
	 * Methode um einen Beitrag zu Bearbeiten
	 * @param b
	 */
	public Beitrag editBeitrag(Beitrag b);
	
	/**
	 * Methode um alle Abonnements eines Users anzuzeigen
	 * @param u
	 */
	public Vector<Abonnement> showAllAbonnementsByUser(User u);
	
	/**
	 * Methode um ein neues Abonnement zu erzeugen
	 * @param u1
	 * @param u2
	 * @param timestamp
	 */
	public Abonnement createAbonnement(User u1, User u2, Timestamp timestamp);
	
	/**
	 * Methode um ein bestehendes Abonnement zu Loeschen
	 * @param a
	 */
	public void deleteAbonnement(Abonnement a);
	
	/**
	 * Methode um einen neues Kommentar zu erzeugen
	 * @param text
	 * @param userId
	 * @param beitragId
	 * @param timestamp
	 */
	public Kommentar createKommentar(String text, int userId, int beitragId, Timestamp timeStamp);
	
	/**
	 * Methode zum Loeschen eines Kommentars
	 * @param k
	 */
	public void deleteKommentar(Kommentar k);
	
	/**
	 * Methode zum anzeigen aller Kommentare
	 * @param b
	 */
	public Vector<Kommentar> getAllKommentareOfBeitrag(Beitrag b);
	
	/**
	 * Methode zum Bearbeiten eines Kommentars
	 * @param k
	 */
	public void editKommentar(Kommentar k);
	
	/**
	 * Methode zum erzeugen eines Likes
	 * @param u
	 * @param b
	 * @param timestamp
	 */
	public Like createLike(User u, Beitrag b, Timestamp timestamp);
	
	/**
	 * Methode zur Ueberpruefung ob der Beitrag bereits geliket ist gibt ein Like zurück
	 * @param u
	 * @param b
	 */
	public Like likeCheck(User u, Beitrag b);
	
	/**
	 * Methode um einen Beitrag zu entliken
	 * @param l
	 */
	public Boolean deleteLike(Like l);
	
	/**
	 * Methode um alle Likes eines Beitrags zu zaehlen
	 * @param b
	 */
	public int countLikes(Beitrag b);
	
	/**
	 * Methode um die Pinnwand anhand der ID auszugeben
	 * @param pinnwandId
	 */
	public Pinnwand getPinnwandById(int pinnwandId);
	
	/**
	 * Methode um einen die Likes des Beitrags zu entfernen
	 * @param b
	 */
	
	public void deleteLikesOfBeitrag(Beitrag b);
	
	/**
	 * Methode um eine Pinnwand zu erstellen
	 * @param u
	 * @param timestamp
	 */
	
	public Pinnwand createPinnwand(User u, Timestamp timestamp);
	
	/**
	 * Methode um die Pinnwand des Users zu finden
	 * @param u
	 */
	
	public Pinnwand getPinnwandOfUser(User u);
	
	/**
	 * Methode um einen Pinnwand zu Loeschen
	 * @param p
	 */
	
	public void deletePinnwand(Pinnwand p);
	
	/**
	 * Methode um einen bestimmten User zu suchen
	 * @param searchQuery
	 */
	
	public Vector<User> searchFunction(String searchQuery);
	
	/**
	 * Methode um zu sehen ob der User schon den jeweiligen User schon abonniert hat
	 * @param u
	 * @param p
	 */
	
	public boolean abonnementCheck(User u, Pinnwand p);
	
	/**
	 * Methode um Abonnement zwischen User anzuzeigen
	 * @param u1
	 * @param u2
	 */
	
	public Abonnement getAbonnementBetweenUsers(User u1, User u2);
	
}
