/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.server.db;

import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Like;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Pinnwand;

/**
 * @author gianluca
 *
 */
public class PinnwandMapper {
	
	private PinnwandMapper pMapper;
	
	/**
	 * Konstruktor der Klasse
	 */
	protected PinnwandMapper() {
	}
	/**
	 * Methode zum speichern einer Pinnwand
	 */
	public void insertPinnwand(Pinnwand p) {
	}
	
	/**
	 * Methode zum loeschen einer Pinnwand
	 */
	public void deletePinnwand(Pinnwand p) {
	}
	
	/**
	 * Methode zum zaehlen aller Likes eines Beitrags
	 */
	public int countAllLikesFromBeitrag(Beitrag b, Like l) {
	return 0;
	}
	
}
