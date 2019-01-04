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

import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Like;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Pinnwand;
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
	 
	
	public User findByUserID(int id) {
		
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM user " + "WHERE UserID= " + id);
			//"ORDED BY UserID"
			
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
	
	/**
	 * Die Methode <code> findAll </code> erm�glicht das auslesen s�mtlicher User-Objekte durch einen Vektor.
	 */
	
	public Vector<User> findAll(){
		Vector v = new Vector();
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT *" + "FROM user " + "ORDER BY UserID");
			
			while(rs.next()) {
				User u = new User();
				u.setId(rs.getInt("UserID"));
				u.setNickname(rs.getString("Nickname"));
				u.setFirstName(rs.getString("FirstName"));
				u.setLastName(rs.getString("LastName"));
				u.setGMail(rs.getString("Gmail"));
				u.setCreationTimeStamp(rs.getTimestamp("CreationTimeStamp"));
				
				v.add(u);
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

			ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE lastName= '" + name +"'"+ " ORDER BY lastName");

			
			while (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("UserID"));
				u.setNickname(rs.getString("Nickname"));
				u.setFirstName(rs.getString("FirstName"));
				u.setLastName(rs.getString("Nachname"));
				u.setGMail(rs.getString("Gmail"));
				u.setCreationTimeStamp(rs.getTimestamp("CreationTimeStamp"));
				result.add(u);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
   public User findUserByNickname(String nickname) {
		
		Connection con =DBConnection.connection();
		Vector<User> result = new Vector<User>();
		
	try {
		
		Statement stmt=con.createStatement();
		ResultSet rs =stmt.executeQuery("SELECT * FROM user WHERE Nickname=" + "'"+nickname+"'");
		
		while (rs.next()) {
			
			User u = new User();
			u.setId(rs.getInt("UserID"));
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
	return null;
	
   
   }
   /**
    * Die Methode <code> insert </> erm�glicht das einf�gen eines User-Objekts in die Datebbank
	 * @param user
	 * @return �bergebene Objekt <code>User_ID</code>.
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
					u.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
		
		}
		
		/**
		 * Wiederholtes Schreiben eines Objekts in die Datenbank.
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
			
	/**
	 * L�schen der Daten eines User-Objekts aus der Datenbank.
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
			
//	//Methode zum Aufruf aller Beiträge eines bestimmten Users
//			public Vector<Beitrag> getAllBeitraegeOfUser(int userId){
//				Connection con = DBConnection.connection();	
//				Vector <Beitrag> v= new Vector<Beitrag>();
//				try {
//					Statement stmt = con.createStatement();
//					ResultSet rs = stmt.executeQuery("Select * FROM Beitrag WHERE User_UserID = "+ userId);
//					
//					while(rs.next()) {
//						Beitrag b =new Beitrag();
////						b.setId(rs.getInt(""));
////						b.setText(rs.getString(""));
////						b.setTimeStamp(rs.getTimestamp(""));
////						v.add(b);
//					}
//					
//				} catch (SQLException e) {
//				e.printStackTrace();
//			}
//				return v;
//			}

}
