/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.server.db;

import java.util.ArrayList;

import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Kommentar;

/**
 * @author GianlucaBernert
 * @author Yesin Soufi
 *
 */
public class KommentarMapper {
	
	private static KommentarMapper kommentarMapper;
	/**
	 * Ein gesch�tzter Konstruktor der weitere Instanzierungen von KommentarMapper Objekten verhindert.
	 */
	protected KommentarMapper() {
	}
	
	/**
	 * Stellt die Singeleton-Eigenschaft der Mapperklasse sicher
	 * @return Sie gibt den KommentarMapper zur�ck.
	 */
	
	public static KommentarMapper kommentarMapper() {
		if (kommentarMapper == null) {
			kommentarMapper = new KommentarMapper();
		} 
		
		return kommentarMapper;
	}

	
	
	 //* Methode zum speichern eines Kommentars
	 
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
