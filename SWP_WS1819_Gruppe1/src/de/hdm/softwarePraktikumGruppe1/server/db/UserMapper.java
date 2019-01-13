/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import de.hdm.softwarePraktikumGruppe1.shared.bo.User;


/**
 * @author Gianluca Bernert
 * @author Yesin Soufi
 * @author SebastianHermann
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
	
	/*
	 * =============================================================================================
	 * Beginn: Standard-Mapper-Methoden. Innerhalb dieses Bereichs werden alle Methoden aufgezählt, die
	 * in allen Mapper-Klassen existieren.
	 */	
		
	/*
	 * Methode, die einen User anhand einer Id zurueck gibt
	 */

	public User findUserById(int userId) {
		
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM user " + "WHERE UserID= " + userId);
			
			if(rs.next()) {
				User u = new User();
				u.setUserId(rs.getInt("UserID"));
				u.setNickname(rs.getString("Nickname"));
				u.setFirstName(rs.getString("FirstName"));
				u.setLastName(rs.getString("Lastname"));
				u.setGMail(rs.getString("Gmail"));
				u.setCreationTimeStamp(rs.getTimestamp("CreationTimeStamp"));
				return u;
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null ;	
	}	
	
	/*
	 * Methode, die das Anlegen eines User-Objekts ermöglicht
	 */
	public void insert(User u) {
		Connection con = DBConnection.connection();

		try {
			PreparedStatement statement = con.prepareStatement(
					"INSERT INTO user (Nickname, Firstname, Lastname, Gmail) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, u.getNickname());
			statement.setString(2, u.getFirstName());
			statement.setString(3, u.getLastName());
			statement.setString(4, u.getGMail());

			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				u.setUserId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	
	}

	/*
	 * Methode, die das Updaten eines User-Objekts in der Datenbank ermöglicht	
	 */
	public void update(User u) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE user SET Nickname='"+ u.getNickname()+"', Firstname='"+u.getFirstName()+"', Lastname='"+ u.getLastName()+"', Gmail='"+ u.getGMail()+"' WHERE UserID="+ u.getUserId());

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	/*
	 * Methode, die das Loeschen eines User-Objekts aus der Datenbank ermöglicht
	 */
	public void deleteUser(User u) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM user " + "WHERE UserID=" + u.getUserId());
  
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* Ende: Standard-Mapper-Methoden
	 * ================================================================================================
	 * Beginn: Foreign Key-Mapper-Methoden
	 */

	/* Ende:  Foreign Key-Mapper-Methoden
	 * ================================================================================================
	 * Beginn: Spezifische Business Object Methoden
	 */	
	
	/*
	 * Methode, die einen User anhand des Nachnamen zurueck gibt
	 */		
		public Vector<User> findUserByLastName(String lName) {
			Connection con = DBConnection.connection();
			Vector<User> result = new Vector<User>();
	
			try {
				Statement stmt = con.createStatement();
	
				ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE lastName= '" + lName +"'"+ " ORDER BY lastName");
	
				
				while (rs.next()) {
					User u = new User();
					u.setUserId(rs.getInt("UserID"));
					u.setNickname(rs.getString("Nickname"));
					u.setFirstName(rs.getString("Firstname"));
					u.setLastName(rs.getString("Lastname"));
					u.setGMail(rs.getString("Gmail"));
					u.setCreationTimeStamp(rs.getTimestamp("CreationTimeStamp"));
					result.add(u);
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}
	/*
	 * Methode, die einen User anhand des Nicknames zurueck gibt
	 */
		 public Vector<User> findUserByNickname(String nickname) {
				
			Connection con =DBConnection.connection();
			Vector<User> result = new Vector<User>();
			
			try {
				
			Statement stmt=con.createStatement();
			ResultSet rs =stmt.executeQuery("SELECT * FROM user WHERE Nickname=" + "'"+nickname+"'");
				
			while (rs.next()) {
					
				User u = new User();
				u.setUserId(rs.getInt("UserID"));
				u.setNickname(rs.getString("Nickname"));
				u.setFirstName(rs.getString("FirstName"));
				u.setLastName(rs.getString("LastName"));
				u.setGMail(rs.getString("Gmail"));
				u.setCreationTimeStamp(rs.getTimestamp("CreationTimeStamp"));
					
				result.add(u);
					
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;  
		   }

	/*
	 * Methode, die einen User anhand des Vornamens zurueck gibt
	 */
		public Vector<User> findUserByFirstName(String fName) {
				
			Connection con =DBConnection.connection();
			Vector<User> result = new Vector<User>();
			
			try {
				
			Statement stmt=con.createStatement();
			ResultSet rs =stmt.executeQuery("SELECT * FROM user WHERE Firstname=" + "'"+ fName+"'");
					
			while (rs.next()) {
					
				User u = new User();
				u.setUserId(rs.getInt("UserID"));
				u.setNickname(rs.getString("Nickname"));
				u.setFirstName(rs.getString("FirstName"));
				u.setLastName(rs.getString("LastName"));
				u.setGMail(rs.getString("Gmail"));
				u.setCreationTimeStamp(rs.getTimestamp("CreationTimeStamp"));
						
				result.add(u);
						
				}
				
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return result;  
		}
	
	/*
	 * Methode, die einen User anhand der Gmail zurueck gibt	
	 */

		public User findUserByGmail(String gMail) {
			
			Connection con =DBConnection.connection();
			
			try {
				
			Statement stmt=con.createStatement();
			ResultSet rs =stmt.executeQuery("SELECT * FROM user WHERE Gmail=" + "'"+ gMail+"'");
					
			if (rs.next()) {
					
				User u = new User();
				u.setUserId(rs.getInt("UserID"));
				u.setNickname(rs.getString("Nickname"));
				u.setFirstName(rs.getString("FirstName"));
				u.setLastName(rs.getString("LastName"));
				u.setGMail(rs.getString("Gmail"));
				u.setCreationTimeStamp(rs.getTimestamp("CreationTimeStamp"));
				return u;
						
				}
				
				} catch (SQLException e) {
					e.printStackTrace();
				}
			return null;  
		}		
		
	/* Ende:  Spezifische Methoden des Business Objects Pinnwand
	 * ================================================================================================
	 */
	 
}
