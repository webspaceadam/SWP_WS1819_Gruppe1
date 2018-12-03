/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.server.db;

import java.util.ArrayList;

import de.hdm.softwarePraktikumGruppe1.shared.bo.Abonnement;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;

/**
 * @author GianlucaBernert
 * @author Yesin Soufi
 *
 *
 */
public class BeitragMapper {
	
	private static BeitragMapper beitragMapper;
	
	/**
	 * Ein gesch�tzter Konstruktor der weitere Instanzierungen von BeitragMapper Objekten verhindert.
	 */
	
	protected BeitragMapper() {
	}
	
	/**
	 * Stellt die Singeleton-Eigenschaft der Mapperklasse sicher
	 * @return Sie gibt den BeitragMapper zur�ck.
	 */
	
	public static BeitragMapper beitragMapper() {
		if (beitragMapper == null) {
			beitragMapper = new BeitragMapper();
		}
		return beitragMapper;
	}
	
	
	
	/**
	 * Methode zum speichern eines Beitrags
	 */
	public void insertBeitrag(Beitrag b) {
	}
	
	/**
	 * Methode zum updaten eines Beitrags
	 */
	public Beitrag updateBeitrag(Beitrag b) {
		return null;
	}
	
	/**
	 * Methode zum loeschen eines Beitrags
	 */
	public void deleteBeitrag(Beitrag b) {
	}
	
	
	/**
	 * Methode zum suchen eines Beitrags anhand der Beitrags ID
	 */
	public Beitrag getBeitragByBeitragtId(int beitragId) {
		return null;
	}
	
	/**
	 * Methode zum suchen eines Beitrags anhand der User ID
	 */
	public ArrayList<Beitrag> getBeitragByUserId(int userId){
		return null;
	}
	

}
