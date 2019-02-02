/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import de.hdm.softwarePraktikumGruppe1.server.ReportGeneratorServiceImpl;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Abonnement;
import de.hdm.softwarePraktikumGruppe1.shared.bo.User;


/**
 * @author GianlucaBerner
 * @author Yesin Soufi
 * @author SebastianHermann
 * 
 */
public class AbonnementMapper {
	
	private static AbonnementMapper abonnementMapper;
	
	/**
	 * Ein gesch�tzter Konstruktor der weitere Instanzierungen von AbonnementMapper Objekten verhindert.
	 */
	protected AbonnementMapper() {
	}
	
	/**
	 * Stellt die Singeleton-Eigenschaft der Mapperklasse sicher
	 * @return Sie gibt den AbonnementMapper zur�ck.
	 */
	
	public static AbonnementMapper abonnementMapper() {
		if (abonnementMapper == null) {
			abonnementMapper = new AbonnementMapper();
		} 
		
		return abonnementMapper;
	}
	
	/*
	 * =============================================================================================
	 * Beginn: Standard-Mapper-Methoden. Innerhalb dieses Bereichs werden alle Methoden aufgezählt, die
	 * in allen Mapper-Klassen existieren.
	 */	
		
	/*
	 * Methode, die einen Abonnement anhand einer Id zurueck gibt
	 */
		public Abonnement findById(int abonnementId) {
		
		Connection con = DBConnection.connection();
		
		
		try {
			Statement stmt = con.createStatement();
		
			ResultSet rs = stmt.executeQuery ("SELECT*  FROM abonnement WHERE AbonnementID=" + abonnementId );
	
				if(rs.next()) {
				Abonnement a = new Abonnement();
				a.setAbonnementId(rs.getInt("AbonnementID"));
				a.setOwnerId(rs.getInt("UserFK"));
				a.setPinnwandId(rs.getInt("PinnwandFK"));
				
				return a;
				}
		}catch(SQLException e){
			e.printStackTrace();
			
			}
			return null;
		}
	
	
	/*
	 * Methode, die das Anlegen eines Abonnement-Objekts ermöglicht
	 */
		
	public Abonnement insert(Abonnement a) {
		Connection con = DBConnection.connection();

		try {
			PreparedStatement statement = con.prepareStatement(
					"INSERT INTO abonnement (PinnwandFK, UserFK) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

			statement.setInt(1, a.getPinnwandId());
			statement.setInt(2, a.getOwnerId());

			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				a.setAbonnementId(rs.getInt(1));
				return a;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Methode, die das Updaten eines Abonnement-Objekts in der Datenbank ermöglicht	
	 */
		public Abonnement update(Abonnement a) {
			Connection con = DBConnection.connection();

			try {
				Statement stmt = con.createStatement();

				stmt.executeUpdate("UPDATE Abonnement SET UserFK="+ a.getOwnerId() + ", PinnwandFK="+ a.getPinnwandId() + "WHERE AbonnementID=" + a.getAbonnementId() );

			} catch (SQLException e) {
				e.printStackTrace();
			}

			
			return a; 
		}
	/*
	 * Methode, die das Loeschen eines Abonnement-Objekts aus der Datenbank ermöglicht
	 */
		public void deleteAbonnement(Abonnement a) {
			Connection con = DBConnection.connection();

			try {
				Statement stmt = con.createStatement();

				stmt.executeUpdate("DELETE FROM abonnement " + "WHERE AbonnementID=" + a.getAbonnementId());
			} catch (SQLException e) {
				e.printStackTrace();
				} 
			}
		
	/* Ende: Standard-Mapper-Methoden
	 * ================================================================================================
	 * Beginn: Foreign Key-Mapper-Methoden
	 */
		
	/*
	 * Methode, die alle Abonnements eines Users zurueck gibt
	 */
		public Vector<Abonnement> findAbonnementsOfUser(int userId){
			
			Connection con= DBConnection.connection();
			Vector <Abonnement> result = new Vector <Abonnement>();
			
		try {
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM abonnement " + "WHERE UserFK=" + userId);
					
				while(rs.next()) {
					
					Abonnement a = new Abonnement();
					
					a.setAbonnementId(rs.getInt("AbonnementID"));
					a.setPinnwandId(rs.getInt("PinnwandFK"));
					a.setOwnerId(rs.getInt("UserFK"));
					a.setCreationTimeStamp(rs.getTimestamp("CreationTimeStamp"));
					
					result.addElement(a);
				}
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
			
		}

		
		/*
		 * Methode, die alle Abonnenten einer Pinnwand
		 *  innerhalb eines Zeitraums zurueck gibt
		 */
			public Vector<Abonnement> findAbonnementsOfUserBetweenDates(int userId, Date start, Date end){
				
				Connection con= DBConnection.connection();
				Vector <Abonnement> result = new Vector <Abonnement>();
				
			try {
				
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM abonnement " + "WHERE PinnwandFK=" + userId +
						" AND CreationTimeStamp >= '" + ReportGeneratorServiceImpl.sqlFormat.format(start).toString() +
						"' AND CreationTimeStamp <= '" + ReportGeneratorServiceImpl.sqlFormat.format(end).toString() + "'");
				
					while(rs.next()) {
						
						Abonnement a = new Abonnement();
						
						a.setAbonnementId(rs.getInt("AbonnementID"));
						a.setPinnwandId(rs.getInt("PinnwandFK"));
						a.setOwnerId(rs.getInt("UserFK"));
						a.setCreationTimeStamp(rs.getTimestamp("CreationTimeStamp"));
							
						result.addElement(a);
					}
						
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return result;
				
			}		
	/*
	 * Methode, die alle Abonnements einer Pinnwand zurueck gibt
	 */
	
		public Vector<Abonnement> findAbonnementsOfPinnwand(int pinnwandId){
			
			Connection con= DBConnection.connection();
			Vector <Abonnement> result = new Vector <Abonnement>();
			
			try {
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM abonnement WHERE PinnwandFK=" + pinnwandId);
					
					while(rs.next()) {
						
						Abonnement a = new Abonnement();
						a.setAbonnementId(rs.getInt("AbonnementID"));
						a.setPinnwandId(rs.getInt("PinnwandFK"));
						a.setOwnerId(rs.getInt("UserFK"));
						result.addElement(a);
					}
			return result;
					
			} catch (SQLException e) {
			e.printStackTrace();
			}
		return null;
			
		}

		public Abonnement findAbonnementsOfPinnwandAndUser(int userFk, int pinnwandFk) {
			// TODO Auto-generated method stub
			Connection con= DBConnection.connection();
			Abonnement a = new Abonnement();
			try {
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM abonnement WHERE UserFK=" + userFk + " AND PinnwandFK=" + pinnwandFk);
					
					if(rs.next()) {
						
						a.setAbonnementId(rs.getInt("AbonnementID"));
						a.setPinnwandId(rs.getInt("PinnwandFK"));
						a.setOwnerId(rs.getInt("UserFK"));

						
					}
					return a;
					
			} catch (SQLException e) {
			e.printStackTrace();
			return null;
			}
			
		}

	/* Ende:  Foreign Key-Mapper-Methoden
	 * ================================================================================================
	 * Beginn: Spezifische Business Object Methoden
	 */	
		
	/* Ende:  Spezifische Methoden des Business Object Pinnwand
	 * ================================================================================================
	 */
				
}