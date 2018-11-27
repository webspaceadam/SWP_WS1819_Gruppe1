/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.server.db;

import java.util.ArrayList;

import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Kommentar;

/**
 * @author GianlucaBernert
 *
 */
public class KommentarMapper {
	
	private KommentarMapper kMapper;
	
	/** 
	* Konstruktor der Klasse
	 */
	protected KommentarMapper() {
	}
	
	/**
	 * Methode zum speichern eines Kommentars
	 */
	public void insertKommentar(Kommentar k) {
	}
	
	/**
	 * Methode zum updaten eines Kommentars
	 */
	public Kommentar updateKommentar(Kommentar k) {
		return null;
	}
	
	/**
	 * Methode zum loeschen eines Kommentars
	 */
	public void deleteKommentar(Kommentar k) {
	}
	
	/**
	 * Methode 
	 */
	public KommentarMapper kommentarMapper() {
		return kMapper;
	}
	
	/**
	 * Methode zum suchen eines Kommentar anhand der Kommentar ID
	 */
	public Kommentar getKommentartByKommentartId(int kommentarId) {
		return null;
	}
	
	/**
	 * Methode zum suchen eines Kommentar anhand der User ID
	 */
	public ArrayList<Kommentar> getKommentarByUserId(int userId){
		return null;
	}
	

}
