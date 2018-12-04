/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Like;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Pinnwand;
import de.hdm.softwarePraktikumGruppe1.shared.bo.User;
import de.hdm.thies.bankProjekt.server.db.DBConnection;
import de.hdm.thies.bankProjekt.shared.bo.Customer;

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
			
			ResultSet rs = stmt.executeQuery("SELECT User_ID, FirstName, LastName, Nickname " + "WHERE User_ID=" + id + "ORDED BY LastName");
			
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
	
	
	public Vector<User> findAll(Vector<User> result){
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT User_ID, FirstName, LastName, Nickname" + "FROM User " + "ORDED BY LastName");
			
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt("User_ID"));
				user.setFirstName(rs.getString("FirstName"));
				user.setLastName(rs.getString("LastName"));
				user.setNickname(rs.getString("Nickname"));
				
				result.addElement(user);
			}}catch (SQLException e) {
				e.printStackTrace();
			}
			
			return result;
			
	}
	
	public Vector<User> findByLastName(String name) {
		Connection con = DBConnection.connection();
		Vector<User> result = new Vector<User>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT id, firstName, lastName " + "FROM User "
					+ "WHERE lastName" + name + "' ORDER BY lastName");

			
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
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
