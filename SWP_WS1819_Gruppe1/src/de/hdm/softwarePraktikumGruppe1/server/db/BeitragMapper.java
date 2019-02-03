/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import de.hdm.softwarePraktikumGruppe1.server.ReportGeneratorServiceImpl;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;


/**
 *
 * @author Yesin Soufi
 * @author SebastianHermann
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
	
	/*
	 * =============================================================================================
	 * Beginn: Standard-Mapper-Methoden. Innerhalb dieses Bereichs werden alle Methoden aufgezählt, die
	 * in allen Mapper-Klassen existieren.
	 */
	
	/*
	 * Methode, die einen Beitrag ahnand einer Id zurueckgibt
	 */
	
		public Beitrag findBeitragById(int beitragId) {
		    
			Connection con = DBConnection.connection();
			
			try {
				Statement stmt= con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM beitrag WHERE BeitragID =" + beitragId);
				
				if (rs.next()) {
					Beitrag b = new Beitrag();
					b.setBeitragId(rs.getInt("BeitragID"));
					b.setInhalt(rs.getString("Inhalt"));
					b.setCreationTimeStamp(rs.getTimestamp("CreationTimeStamp"));
					b.setOwnerId(rs.getInt("UserFK"));
					b.setPinnwandId(rs.getInt("PinnwandFK"));
					
					return b;
				}
				
			}	
			catch (SQLException e) {
				e.printStackTrace();
				
				return null;
			}
			
			return null;
			}
	
	
	/*
	 * Methode, die das Anlegen eines Beitragsobjekts in der Datenbank ermöglicht.
	 */
	
		public void insertBeitrag(Beitrag b) {
		
			Connection con = DBConnection.connection();

			try {
				PreparedStatement statement = con.prepareStatement(
						"INSERT INTO beitrag (Inhalt, PinnwandFK, UserFK) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

				statement.setString(1, b.getInhalt());
				statement.setInt(2, b.getPinnwandId());
				statement.setInt(3, b.getOwnerId());

				statement.executeUpdate();
				ResultSet rs = statement.getGeneratedKeys();
				if (rs.next()) {
					b.setBeitragId(rs.getInt(1));
					
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	/*
	 * Methode, die das Update eines Beitragobjekts in der Datenbank ermöglicht.
	 */
		public Beitrag updateBeitrag(Beitrag b) {
			
			Connection con = DBConnection.connection();

			try {
				Statement stmt = con.createStatement();

				stmt.executeUpdate("UPDATE beitrag SET Inhalt= '"+ b.getInhalt() + "' WHERE BeitragID=" + b.getBeitragId());

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return b;
		}
	

	/*
	 * Methode, die das Löschen eines Beitragobjekts aus der Datenbank ermöglicht.
	 */		
		public void deleteBeitrag(Beitrag b) {
		
			Connection con = DBConnection.connection();
			
			try {
				Statement stmt = con.createStatement();
				stmt.executeUpdate("DELETE FROM beitrag WHERE BeitragID=" + b.getBeitragId());
				System.out.println("DELETE FROM beitrag WHERE BeitragID=" + b.getBeitragId());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	/* Ende: Standard-Mapper-Methoden
	 * ================================================================================================
	 * Beginn: Foreign Key-Mapper-Methoden
	 */
	
	/*
	 * Methode, die alle Beitraege einer Pinnwand anhand der Pinnwand FKs aus der Datenbank ausliest und zurueck gibt	
	 */
	
		public Vector <Beitrag> findBeitraegeOfPinnwand(int pinnwandFK){
			Connection con = DBConnection.connection();
			Vector<Beitrag> result = new Vector<Beitrag>();
			
			try {
				Statement stmt = con.createStatement();
				
				ResultSet rs = stmt.executeQuery("SELECT * FROM beitrag WHERE PinnwandFK=" + pinnwandFK );
				
				while (rs.next()) {
					Beitrag b = new Beitrag();
					b.setBeitragId(rs.getInt("BeitragID"));
					b.setInhalt(rs.getString("Inhalt"));
					b.setCreationTimeStamp(rs.getTimestamp("CreationTimeStamp"));
					b.setPinnwandId(rs.getInt("PinnwandFK"));
					b.setOwnerId(rs.getInt("UserFK"));
					
					result.addElement(b);
				}			
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}

	/*
	 * Methode, die alle Beitraege eines Users anhand des User-FKs aus der Datenbank ausliest und zurueck gibt	
	 */		
		public Vector <Beitrag> findBeitraegeOfUser(int userFK){
			Connection con = DBConnection.connection();
			Vector<Beitrag> result = new Vector<Beitrag>();
			
			try {
				Statement stmt = con.createStatement();
				
				ResultSet rs = stmt.executeQuery("SELECT * FROM beitrag WHERE UserFK= " + userFK);
				
				while (rs.next()) {
					Beitrag b = new Beitrag();
					b.setBeitragId(rs.getInt("BeitragID"));
					b.setInhalt(rs.getString("Inhalt"));
					b.setCreationTimeStamp(rs.getTimestamp("CreationTimeStamp"));
					b.setPinnwandId(rs.getInt("PinnwandFK"));
					b.setOwnerId(rs.getInt("UserFK"));
					
					result.addElement(b);
				}			
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}
		

		
		/*
		 * Methode, die alle Beitraege eines Users innerhalb eines Datums
		 * anhand des User-FKs aus der Datenbank ausliest und zurueck gibt	
		 */		
			public Vector <Beitrag> findBeitraegeOfUserBetweenDates(int userFK, Date start, Date end){
				Connection con = DBConnection.connection();
				Vector<Beitrag> result = new Vector<Beitrag>();
				
				try {
					Statement stmt = con.createStatement();
					
					ResultSet rs = stmt.executeQuery("SELECT * FROM beitrag WHERE UserFK = " + userFK +
							" AND CreationTimeStamp >= '" + ReportGeneratorServiceImpl.sqlFormat.format(start).toString() +
							"' AND CreationTimeStamp <= '" + ReportGeneratorServiceImpl.sqlFormat.format(end).toString() + "'");
					
					while (rs.next()) {
						Beitrag b = new Beitrag();
						b.setBeitragId(rs.getInt("BeitragID"));
						b.setInhalt(rs.getString("Inhalt"));
						b.setCreationTimeStamp(rs.getTimestamp("CreationTimeStamp"));
						b.setPinnwandId(rs.getInt("PinnwandFK"));
						b.setOwnerId(rs.getInt("UserFK"));
						
						result.addElement(b);
					}			
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
				return result;
			}		
		

	/* Ende:  Foreign Key-Mapper-Methoden
	 * ================================================================================================
	 * Beginn: Spezifische Business Object Methoden
	 */	
	
	/* Ende:  Spezifische Methoden des Business Object Pinnwand
	 * ================================================================================================
	 */	
	
}
