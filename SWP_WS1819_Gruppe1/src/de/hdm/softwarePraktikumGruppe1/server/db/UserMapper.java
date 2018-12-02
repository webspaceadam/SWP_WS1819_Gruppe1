/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.server.db;

import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Like;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Pinnwand;
import de.hdm.softwarePraktikumGruppe1.shared.bo.User;

/**
 * @author Gianluca Bernert
 * @author Yesin Soufi
 * 
 *
 */
public class UserMapper {
	
	private static UserMapper userMapper;
	/**
	 * Ein gesch�tzter Konstruktor der weitere Instanzierungen von UserMapper Objekten verhindert.
	 */
	protected UserMapper() {
	}
	
	/**
	 * Stellt die Singeleton-Eigenschaft der Mapperklasse sicher
	 * @return Sie gibt den UserMapper zur�ck.
	 */
	
	public static UserMapper UserMapper() {
		if (userMapper == null) {
			userMapper = new UserMapper();
		} 
		
		return userMapper;
	}
	/**
	 * Methode zum speichern eines User
	 */
	public void insertUser(User u) {
	}
	
	/**
	 * Methode zum loeschen eines User
	 */
	public void deleteUser(User u) {
	}
	
	/**
	 * Methode zum anzeigen eines User anhand der User ID
	 */
	public User findeUserByUserId(int id) {
	return null;
	}

}
