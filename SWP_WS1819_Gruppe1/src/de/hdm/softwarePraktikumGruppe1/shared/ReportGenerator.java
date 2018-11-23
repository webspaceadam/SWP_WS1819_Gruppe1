/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared;

import java.util.ArrayList;
import java.util.Date;

import de.hdm.softwarePraktikumGruppe1.shared.bo.*;

/**
 * @author gianluca
 * Interface das RemoteService als Superklasse hat und von ReportGeneratorImpl Implementiert wird
 */
public interface ReportGenerator extends RemoteService{
	
	/**
	 * Methode die die Beitraege eines Users zurück gibt
	 */
	public ArrayList<Beitrag> getBeitraegeFromUser(User u, Date d);
	
	/**
	 * Methode die die Likes eines Users zurück gibt
	 */
	public ArrayList<Like> getLikesFromUser(User u, Date d);
	
	/**
	 * Methode die das Abonnement eines Users zurück gibt
	 */
	public ArrayList<User> getAbonnementFromPinnwand(Pinnwand p, Date d);
	
	/**
	 * Methode die den Kommentar eines Beitrags zurück gibt
	 */
	public ArrayList<Kommentar> getKommentarFromBeitrag(Beitrag b, Date d);
	
	/**
	 * Methode die die Likes eines Beitrags zurück gibt
	 */
	public ArrayList<Like> getLikesFromBeitrag(Beitrag b, Date d);

}
