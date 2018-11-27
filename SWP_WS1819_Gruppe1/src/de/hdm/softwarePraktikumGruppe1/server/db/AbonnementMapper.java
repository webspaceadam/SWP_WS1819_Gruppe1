/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.server.db;

import java.util.ArrayList;

import de.hdm.softwarePraktikumGruppe1.shared.bo.Abonnement;

/**
 * @author yesin
 * 
 */
public class AbonnementMapper {
	
	private static AbonnementMapper AbonnementMapper;
	
	/**
	 * Ein geschützter Konstruktor der weitere Instanzierungen von AbonnementMapper Objekten verhindert.
	 */
	protected AbonnementMapper() {
	}
	
	/**
	 * Stellt die Singeleton-Eigenschaft der Mapperklasse sicher
	 * @return Sie gibt den AbonnementMapper zurück.
	 */
	
	public static AbonnementMapper AbonnementMapper() {
		if (AbonnementMapper == null) {
			AbonnementMapper = new AbonnementMapper();
		} 
		
		return AbonnementMapper;
	}
		
	
	
	/**
	 * Methode zum speichern eines Abonnements
	 */
	public void insertAbonnement(Abonnement a) {
	}
	
	/**
	 * Methode zum loeschen eines Abonnements
	 */
	public void deleteAbonnement(Abonnement a) {
	}
	
	/**
	 * Methode 
	 */
	public AbonnementMapper abonnementMapper() {
		return aMapper;
	}
	
	/**
	 * Methode zum suchen eines Abonnements anhand der Abonnement ID
	 */
	public Abonnement getAbonnementByAbonnementId(int abonnementId) {
		return null;
	}
	
	/**
	 * Methode zum suchen eines Abonnements anhand der User ID
	 */
	public ArrayList<Abonnement> getAbonnementByUserId(int userId){
		return null;
	}
	
}
