/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.server;

import java.util.*;

import de.hdm.softwarePraktikumGruppe1.shared.*;
import de.hdm.softwarePraktikumGruppe1.shared.bo.*;

/**
 * @author gianluca
 * Klasse die RemoveServerServlet als Superklasse besitzt und das Interface ReportGenerator implementiert
 */
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator{
	
	/**
	 * Methode die die Beitraege eines Users zurück gibt
	 */
	public ArrayList<Beitrag> getBeitraegeFromUser(User u, Date d){
		return null;
	}
	
	/**
	 * Methode die die Likes eines Users zurück gibt
	 */
	public ArrayList<Like> getLikesFromUser(User u, Date d){
		return null;
	}
	
	/**
	 * Methode die das Abonnement eines Users zurück gibt
	 */
	public ArrayList<User> getAbonnementFromPinnwand(Pinnwand p, Date d){
		return null;
	}
	
	/**
	 * Methode die den Kommentar eines Beitrags zurück gibt
	 */
	public ArrayList<Kommentar> getKommentarFromBeitrag(Beitrag b, Date d){
		return null;
	}
	
	/**
	 * Methode die die Likes eines Beitrags zurück gibt
	 */
	public ArrayList<Like> getLikesFromBeitrag(Beitrag b, Date d){
		return null;
	}

}
