/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared;

import java.sql.Timestamp;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Abonnement;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Kommentar;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Like;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Pinnwand;
import de.hdm.softwarePraktikumGruppe1.shared.bo.User;

/**
 * @author GianlucaBernert
 *
 */
public interface Pinnwandverwaltung extends RemoteService {
	
	/**
	 * Methode um einen User zu erzeugen
	 */
	public User createUser(String firstName, String lastName, String nickName, String gMail, Timestamp timestamp);
	
	/**
	 * Methode um einen User zu Bearbeiten
	 */
	public void editUser(User u);
	
	/**
	 * Methode um einen User zu Loeschen
	 */
	public void deleteUser(User u);
	
	/**
	 * Methode zur Ueberpruefung der Zugangsberechtigung 
	 */
	public User loginCheck(String nickname, String password);
	
	/**
	 * Methode um einen User anhand seiner ID zu suchen
	 */
	public User getUserById(int userId);
	
	/**
	 * Methode um einen User anhand seines Nicknamens zu suchen
	 */
	public Vector<User> getUserByNickname(String nickname);
	
	/**
	 * Methode um einen User anhand seines Vornamens zu suchen
	 */
	
	public Vector<User> getUserByFirstName(String fName);
	
	/**
	 * Methode um einen User anhand seines Nachnamens zu suchen
	 */
	public Vector<User> getUserByLastName(String lName);
	
	/**
	 * Methode um einen User anhand seiner Gmail zu suchen
	 */
	public User getUserByGmail(String gMail);
	
	
	/**
	 * Methode um einen Beitrag zu erzeugen
	 */
	public Beitrag createBeitrag(String text, User user, Timestamp timeStamp);
	
	/**
	 * Methode um alle Beiträge eines Users auszugeben
	 */
	public Vector<Beitrag> getAllBeitraegeOfUser(User u);
	/**
	 * Methode um einen Beitrag zu Loeschen
	 */
	
	public int getBeitragAmountOfUser(User u);
	
	public void deleteBeitrag(Beitrag b);
	
	/**
	 * Methode um einen Beitrag zu Bearbeiten
	 */
	public Beitrag editBeitrag(Beitrag b);
	
	/**
	 * Methode um alle Abonnements eines Users anzuzeigen
	 */
	public Vector<Abonnement> showAllAbonnementsByUser(User u);
	
	/**
	 * Methode um ein neues Abonnement zu erzeugen
	 */
	public Abonnement createAbonnement(User u, Pinnwand p, Timestamp timestamp);
	
	/**
	 * Methode um ein bestehendes Abonnement zu Loeschen
	 */
	public void deleteAbonnement(Abonnement a);
	
	/**
	 * Methode um einen neues Kommentar zu erzeugen
	 */
	public Kommentar createKommentar(String text, int userId, int beitragId, Timestamp timeStamp);
	
	/**
	 * Methode zum Loeschen eines Kommentars
	 */
	public void deleteKommentar(Kommentar k);
	
	/**
	 * Methode zum anzeigen aller Kommentare
	 */
	public Vector<Kommentar> getAllKommentareOfBeitrag(Beitrag b);
	
	/**
	 * Methode zum Bearbeiten eines Kommentars
	 */
	public void editKommentar(Kommentar k);
	
	/**
	 * Methode zum erzeugen eines Likes
	 */
	public Like createLike(User u, Beitrag b, Timestamp timestamp);
	
	/**
	 * Methode zur Ueberpruefung ob der Beitrag bereits geliket ist
	 */
	public Like likeCheck(User u, Beitrag b);
	
	/**
	 * Methode um einen Beitrag zu entliken
	 */
	public void deleteLike(Like l);
	
	/**
	 * Methode um alle Likes eines Beitrags zu zaehlen
	 */
	public int countLikes(Beitrag b);
	
	/**
	 * Methode um Likes eines Beitrags zu entfernen
	 */
	public void deleteLikesOfBeitrag(Beitrag b);
	
	public Pinnwand createPinnwand(User u, Timestamp timestamp);
	
	public Pinnwand getPinnwandByUserId(int userId);
	
	public void deletePinnwand(Pinnwand p);
	
	
	
	
	
	

}
