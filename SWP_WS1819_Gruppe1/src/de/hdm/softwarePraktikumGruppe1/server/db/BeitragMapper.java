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
 * Durch das implementieren der Methoden können User-Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden.
 */

public class BeitragMapper {
	
	private static BeitragMapper beitragMapper;
	
	/**
	 * Ein geschï¿½tzter Konstruktor der weitere Instanzierungen von BeitragMapper Objekten verhindert.
	 */
	
	protected BeitragMapper() {
	}
	
	/**
	 * Stellt die Singeleton-Eigenschaft der Mapperklasse sicher
	 * @return Sie gibt den BeitragMapper zurï¿½ck.
	 */
	
	public static BeitragMapper beitragMapper() {
		if (beitragMapper == null) {
			beitragMapper = new BeitragMapper();
		}
		return beitragMapper;
	}
	
	
	
	 /**
	    * Die Methode <code> insert </> ermöglicht das einfügen eines Beitrag-Objekts in die Datebbank
		 * @param user
		 * @return übergebene Objekt <code>BeitragID</code>.
		 */
	
	public void insertBeitrag(Beitrag b) {
		
			Connection con = DBConnection.connection();

			try {
				PreparedStatement statement = con.prepareStatement(
						"INSERT INTO textbeitrag (BeitragID, inhalt, creationTimeStamp) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				statement.setInt(1, b.getId(b));
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
	 * Löschen der Daten eines Beitrag-Objekts aus der Datenbank.
	 */
	
	public void deleteBeitrag(Beitrag b) {
		
			Connection con = DBConnection.connection();
			try {
				Statement stmt = con.createStatement();
				stmt.executeUpdate("DELETE FROM textbeitrag " + "WHERE id = " + b.getId(b));
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * Löschen der Daten eines Beitrag-Objekts von einem bestimmten User aus der Datenbank.
	 */
			
			public void deleteBeitraegeOfUser(User user) {
				Connection con =DBConnection.connection();
				
				try {
					Statement stmt = con.createStatement();
				
					stmt.executeUpdate("DELETE FROM beitrag" + "WHERE User_User_ID=" + user.getId(user));
				}
				
				catch(SQLException e) {
					e.printStackTrace();
				}

	}
	
	
	/**
	 * Methode zum suchen eines Beitrags anhand der Beitrags ID
	 */
	public Beitrag getBeitragByBeitragtId(int id) {
    
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt= con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, inhalt, creationTimeStamp" + "WHERE id =" + id);
			
			if (rs.next()) {
				Beitrag b = new Beitrag();
				b.setId(rs.getInt("Beitrag_ID"));
				b.setText(rs.getString("Inhalt"));
				b.setCreationTimeStamp(rs.getDate(id));
				
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
	 * Die Methode <code> findAll </code> ermöglicht das auslesen sämtlicher User-Objekte durch einen Vektor.
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
				b.setText(rs.getString("inhalt"));
				b.setCreationTimeStamp(rs.getDate("CreationTimeStamp"));
				result.addElement(b);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		return result;
	

}
	
	public Vector <Beitrag> getAllBeitraegeOfPinnwand(int Pinnwand_PinnwandID){
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
