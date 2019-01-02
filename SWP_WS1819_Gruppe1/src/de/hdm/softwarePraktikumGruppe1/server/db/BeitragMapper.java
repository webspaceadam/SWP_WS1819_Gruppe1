/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.server.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import de.hdm.softwarePraktikumGruppe1.shared.bo.Abonnement;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Pinnwand;
import de.hdm.softwarePraktikumGruppe1.shared.bo.User;

/**
 * @author GianlucaBernert
 * @author Yesin Soufi
 *
 *
 */

/**
 * Mit Hilfe der MapperKlasse <code>UserMapper</code> werden User-Objekte auf eine relationale Datenbank abgebildet.
 * Durch das implementieren der Methoden k�nnen User-Objekte gesucht, erzeugt, modifiziert und
 * gel�scht werden.
 */

public class BeitragMapper {
	
	private static BeitragMapper beitragMapper;
	
	/**
	 * Ein gesch�tzter Konstruktor der weitere Instanzierungen von BeitragMapper Objekten verhindert.
	 */
	
	protected BeitragMapper() {
	}
	
	/**
	 * Stellt die Singeleton-Eigenschaft der Mapperklasse sicher
	 * @return Sie gibt den BeitragMapper zur�ck.
	 */
	
	public static BeitragMapper beitragMapper() {
		if (beitragMapper == null) {
			beitragMapper = new BeitragMapper();
		}
		return beitragMapper;
	}
	
	
	
	 /**
	    * Die Methode <code> insert </> erm�glicht das einf�gen eines Beitrag-Objekts in die Datebbank
		 * @param user
		 * @return �bergebene Objekt <code>BeitragID</code>.
		 */
	
	public void insertBeitrag(Beitrag b) {
		
			Connection con = DBConnection.connection();

			try {
				PreparedStatement statement = con.prepareStatement(
						"INSERT INTO textbeitrag (BeitragID, inhalt, creationTimeStamp) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
<<<<<<< HEAD
				statement.setInt(1, b.getId());
=======
				statement.setInt(1, b.getOwnerId());
>>>>>>> refs/heads/master
				statement.setString(2, b.getText());
				statement.setDate(3, (Date) b.getCreationTimeStamp());

				statement.executeUpdate();
				ResultSet rs = statement.getGeneratedKeys();
				if (rs.next()) {
					b.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
	}
	
	/**
	 * L�schen der Daten eines Beitrag-Objekts aus der Datenbank.
	 */
	
	public void deleteBeitrag(Beitrag b) {
		
			Connection con = DBConnection.connection();
			try {
				Statement stmt = con.createStatement();
				stmt.executeUpdate("DELETE FROM textbeitrag " + "WHERE id = " + b.getOwnerId());
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * L�schen der Daten eines Beitrag-Objekts von einem bestimmten User aus der Datenbank.
	 */
			
			public void deleteBeitraegeOfUser(User user) {
				Connection con =DBConnection.connection();
				
				try {
					Statement stmt = con.createStatement();
				
					stmt.executeUpdate("DELETE FROM beitrag" + "WHERE User_User_ID=" + user.getUserId(user));
				}
				
				catch(SQLException e) {
					e.printStackTrace();
				}

	}
	
	
	/**
	 * Methode zum suchen eines Beitrags anhand der Beitrags ID
	 */
	public Beitrag getBeitragByBeitragtId(int BeitragID) {
    
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt= con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT BeitragID, Inhalt, creationTimeStamp" + "WHERE BeitragID =" + BeitragID);
			
			if (rs.next()) {
				Beitrag b = new Beitrag();
				b.setId(rs.getInt("BeitragID"));
				b.setText(rs.getString("Inhalt"));
				b.setCreationTimeStamp(rs.getDate("CreationTimeStamp"));
				
				return b;
			}
			
		}	
		catch (SQLException e) {
			e.printStackTrace();
			
			return null;
		}
		
		return null;
				
		
	}
	
	/**
	 * Die Methode <code> findAll </code> erm�glicht das auslesen s�mtlicher User-Objekte durch einen Vektor.
	 */
	
	public Vector<Beitrag> getAllBeitraege() {
		Connection con = DBConnection.connection();
		
		Vector<Beitrag> result = new Vector<Beitrag>();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT BeitragID, Inhalt, CreationTimeStamp FROM beitrag"
			+ "ORDER BY BeitragID");
			
			while (rs.next());{
				Beitrag b = new Beitrag();
				b.setId(rs.getInt("BeitragID"));
				b.setText(rs.getString("Inhalt"));
				b.setCreationTimeStamp(rs.getDate("CreationTimeStamp"));
				result.addElement(b);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		return result;
		}
	
	

	//Methode updateBeitrag;
	public Beitrag updateBeitrag(Beitrag b) {
		
		return null;
		
	}

	public Vector <Beitrag> getBeitraegeOfPinnwand(int Pinnwand_PinnwandID){
		Connection con = DBConnection.connection();
		Vector<Beitrag> result = new Vector<Beitrag>();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT BeitragID, Inhalt, CreationTimeStamp FROM beitrag"
			+"WHERE = Pinnwand_PinnwandID=" +"'"+ Pinnwand_PinnwandID + "'" + "ORDER BY BeitragID");
			
			while (rs.next()) {
				Beitrag b = new Beitrag();
				b.setId(rs.getInt("BeitragID"));
				b.setText(rs.getString("Inhalt"));
				b.setCreationTimeStamp(rs.getDate("CreationTimeStamp"));
				
				result.addElement(b);
			}			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

		
		
		
	}



}
