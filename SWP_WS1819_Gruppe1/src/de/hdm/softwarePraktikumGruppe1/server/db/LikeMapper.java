/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.server.db;

import java.util.ArrayList;

import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Like;

/**
 * @author GianlucaBernert
 * @author Yesin Soufi
 *
 */
public class LikeMapper {
	
	private static LikeMapper likeMapper;
	/**
	 * Ein gesch�tzter Konstruktor der weitere Instanzierungen von LikeMapper Objekten verhindert.
	 */
	protected LikeMapper() {
	}
	
	/**
	 * Stellt die Singeleton-Eigenschaft der Mapperklasse sicher
	 * @return Sie gibt den LikeMapper zur�ck.
	 */
	
	public static LikeMapper likeMapper() {
		if (likeMapper == null) {
			likeMapper = new LikeMapper();
		} 
		
		return likeMapper;
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
