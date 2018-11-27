/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.server.db;

import java.util.ArrayList;

import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Like;

/**
 * @author GianlucaBernert
 *
 */
public class LikeMapper {
	
	private LikeMapper lMapper;
	
	/** 
	* Konstruktor der Klasse
	 */
	protected LikeMapper() {
	}
	
	/**
	 * Methode zum speichern eines Likes
	 */
	public void insertLike(Like l) {
	}
	
	/**
	 * Methode zum loeschen eines Likes
	 */
	public void deleteLike(Like l) {
	}
	
	/**
	 * Methode 
	 */
	public LikeMapper likeMapper() {
		return lMapper;
	}
	
	/**
	 * Methode zum zählen aler Likes eines Beitrags
	 */
	public int countAllLikesFromBeitrag(Beitrag b, Like l) {
		return 0;
	}
	
	/**
	 * Methode zum suchen eines Beitrags anhand der User ID
	 */
	public ArrayList<Like> getLikeByUserId(int userId){
		return null;
	}
	

}
