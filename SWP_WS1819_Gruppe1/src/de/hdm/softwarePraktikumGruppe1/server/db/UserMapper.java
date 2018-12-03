/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	public User findByUserID(int id) {
		
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT User_ID, Vorname, Nachname, Nickname " + "WHERE User_ID=" + id + "ORDED BY Nachname");
			
			if(rs.next()) {
				User u = new User();
				u.setId(rs.getInt("User_ID"));
				u.setFirstName(rs.getString("FirstName"));
				u.setLastName(rs.getString("Nachname"));
				u.setNickname(rs.getString("Nickname"));
				
				return u;
				
			}
			
		}catch(SQLException e) {
				e.printStackTrace();
			
			}
		return null ;

			
		
		
		

	
	
	
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

}
