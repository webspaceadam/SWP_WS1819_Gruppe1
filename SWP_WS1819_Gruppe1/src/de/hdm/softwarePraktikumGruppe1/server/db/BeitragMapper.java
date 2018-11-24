/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.server.db;

import java.util.ArrayList;

import de.hdm.softwarePraktikumGruppe1.shared.bo.Abonnement;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;

/**
 * @author gianluca
 *
 */
public class BeitragMapper {
	
	private BeitragMapper bMapper;
	
	/** 
	* Konstruktor der Klasse
	 */
	protected BeitragMapper() {
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
	 * Methode 
	 */
	public BeitragMapper beitragMapper() {
		return bMapper;
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
