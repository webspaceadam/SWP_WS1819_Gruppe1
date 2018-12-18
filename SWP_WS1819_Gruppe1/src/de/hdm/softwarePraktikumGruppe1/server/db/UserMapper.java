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


/**
 * @author Gianluca Bernert
 * @author Yesin Soufi
 * 
 */

/**
 * Mit Hilfe der MapperKlasse <code>UserMapper</code> werden User-Objekte auf eine relationale Datenbank abgebildet.
 * Durch das implementieren der Methoden k�nnen User-Objekte gesucht, erzeugt, modifiziert und
 * gel�scht werden.
 */
public class UserMapper {
	
	/**
	 * Durch einem sogeannten <b>Singleton<b> kann die Klasse UserMapper nur einmal instantiiert werden.
	 * Mit Hilfe von <code>static</code> wird dies umgesetzt.
	 */
	
	private static UserMapper userMapper = null;
	
	
	/**
	 * Ein gesch�tzter Konstruktor der weitere Instanzierungen von UserMapper Objekten verhindert.
	 */
	protected UserMapper() {
	}
	
	/**
	 * Stellt die Singeleton-Eigenschaft der Mapperklasse sicher
	 * Sie daf�r sorgt, dass nur eine einzige Instanz von <code>UserMapper</code> existiert.
	 * @return Sie gibt den UserMapper zur�ck.
	 */
	
	public static UserMapper userMapper() {
		if (userMapper == null) {
			userMapper = new UserMapper();
		} 
		
		return userMapper;
	}
	
	/**
	 * /**
	 * Die Methode <code>findByUserId</code> erm�glicht das suchen eines Kunden mit einer
	 * vorgegebnen KundenID. Dadurch dass die ID eindeutig ist kann genau ein Objekt zur�ckgegeben werden.
	 * 
	 * @param User_ID
	 *            Prim�rschl�sselattribut (->DB)
	 * @return Kunden-Objekt, das dem �bergebenen Schl�ssel entspricht, null bei
	 *         nicht vorhandenem DB
	 */
	 
	
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
	
	/**
	 * Die Methode <code> findAll </code> erm�glicht das auslesen s�mtlicher User-Objekte durch einen Vektor.
	 */
	
	public Vector<User> findAll(){
		Vector v = new Vector();
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
				
				v.addElement(user);
			}}catch (SQLException e) {
				e.printStackTrace();
			}
			
			return v;
			
	}
	
	public Vector<User> findByLastName(String name) {
		Connection con = DBConnection.connection();
		Vector<User> result = new Vector<User>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT User_ID, firstName, lastName " + "FROM User "
					+ "WHERE lastName" + name + "' ORDER BY lastName");

			
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("User_ID"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
   public User findUserByNickname(String nickname) {
		
		Connection con =DBConnection.connection();
		
		
	try {
		
		Statement stmt=con.createStatement();
		ResultSet rs =stmt.executeQuery("SELECT User_ID, Firstname, Lastname" + "WHERE Nickname=" + nickname);
		
		while (rs.next()) {
			
			User user = new User();
			user.setId(rs.getInt("User_ID"));
			user.setFirstName(rs.getString("Firstname"));
			user.setLastName(rs.getString("Lastname"));
			user.setNickname(rs.getString("Nickname"));
			
			return user;
		}
	
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
	
   
   }
   /**
    * Die Methode <code> insert </> erm�glicht das einf�gen eines User-Objekts in die Datebbank
	 * @param user
	 * @return �bergebene Objekt <code>User_ID</code>.
	 */
		
		public User insert(User user) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM User ");

			if (rs.next()) {
				
				user.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();
				
				stmt.executeUpdate("INSERT INTO customers (User_ID, FirstName, LastName, Nickname) " + "VALUES (" + user.getUserId(user) + ",'"
						+ user.getFirstName() + "'" +
						user.getLastName() + "'" + user.getNickname());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
		
	}
		
		/**
		 * Wiederholtes Schreiben eines Objekts in die Datenbank.
		 */
		

			public User update(User user) {
				Connection con = DBConnection.connection();

				try {
					Statement stmt = con.createStatement();

					stmt.executeUpdate("UPDATE User " + "SET Firstname=\"" + user.getFirstName() + "\", " + "Lastname=\""
							+ user.getLastName() + "\" " + user.getNickname() + "WHERE User_ID=" + user.getUserId(user));

				} catch (SQLException e) {
					e.printStackTrace();
				}

				
				return user;
			}
			
	/**
	 * L�schen der Daten eines User-Objekts aus der Datenbank.
	 */
			
			public void delete(User user) {
				Connection con = DBConnection.connection();

				try {
					Statement stmt = con.createStatement();

					stmt.executeUpdate("DELETE FROM User " + "WHERE User_ID=" + user.getUserId(user));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

}
