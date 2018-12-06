/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.server.db;

import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Like;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Pinnwand;

/**
 * @author GianlucaBernert
 * @author Yesin Soufi
 *
 */
public class PinnwandMapper {
	
	private static PinnwandMapper pinnwandMapper;
	/**
	 * Ein gesch�tzter Konstruktor der weitere Instanzierungen von PinnwandMapper Objekten verhindert.
	 */
	protected PinnwandMapper() {
	}
	
	/**
	 * Stellt die Singeleton-Eigenschaft der Mapperklasse sicher
	 * @return Sie gibt den PinnwandMapper zur�ck.
	 */
	
	public static PinnwandMapper pinnwandMapper() {
		if (pinnwandMapper == null) {
			pinnwandMapper = new PinnwandMapper();
		} 
		
		return pinnwandMapper;
	}

	
	
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
