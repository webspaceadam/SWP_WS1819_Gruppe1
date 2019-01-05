
package de.hdm.softwarePraktikumGruppe1.server.db;

import java.sql.*;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Pinnwand;


/**
 * @author GianlucaBernert 
 * @author Ulus Serhat
 * @author Yesin Soufi
 * @author SebastianHermann
 */
	public class PinnwandMapper {
		
		
		/* Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
		   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
		   * einzige Instanz dieser Klasse.--> Singeltoneigenschaft
		   */
		private static PinnwandMapper pinnwandMapper = null;

		
		//Ein geschützter Konstruktor der weitere Instanzierungen von PinnwandMapper Objekten verhindert.
		protected PinnwandMapper() {
		}

		
		//Diese Methode stellt die Singelton-Eigenschaft sicher, indem sie dafür sorgt, 
		 //dass nur eine einzige Instanz dieser Klasse existiert.
		 public static PinnwandMapper pinnwandMapper(){
			if (pinnwandMapper==null){
				 pinnwandMapper= new PinnwandMapper();
			}
			 return pinnwandMapper;
			}
		 
		 /*
		  * =============================================================================================
		  * Beginn: Standard-Mapper-Methoden. Innerhalb dieses Bereichs werden alle Methoden aufgezählt, die
		  * in allen Mapper-Klassen existieren.
		  */
		 
		 
		 /*
		  * Methode, die eine Pinnwand anhand einer ID zurueckgibt
		  */
		 
		 public Pinnwand findPinnwandById(int pinnwandId) {
				
				
				Connection con = DBConnection.connection();
				
				try{
					
					//leeres SQL-Statement anlegen
					Statement stmt = con.createStatement();
					
					//Suche alle Felder der Pinnwandtabelle anhand von ID
					ResultSet rs = stmt.executeQuery("SELECT * FROM pinnwand WHERE PinnwandID=" + pinnwandId);

					 
					if (rs.next()) {
						// Ergebnis in Pinnwandobjekt umwandeln
						Pinnwand p = new Pinnwand();	
						p.setPinnwandId(rs.getInt("PinnwandID"));
						p.setOwnerId(rs.getInt("UserFK"));
						p.setCreationTimeStamp(rs.getTimestamp("CreationTimeStamp"));
						
						return p;

						}
					}
						
					catch (SQLException e) {
					   e.printStackTrace();
					   
					  }
				return null;
		 }
		 
		 /*
		  * Methode, die ein Pinnwand-Objekt in die Datenbank lädt.
		  */
		 public void insertPinnwand (Pinnwand p) {
			 
			//leeres SQL-Statement anlegen
			 Connection con = DBConnection.connection();
		 
		 try{
			
			//leeres SQL-Statement anlegen
			Statement stmt = con.createStatement();
			 
			//Ausführen eines Insert-Statements um das Objekt in die Datenbank zu laden.
			stmt.executeUpdate("INSERT INTO pinnwand (UserFk) VALUES (" + p.getOwnerId()+")");
		    }
			catch (SQLException e) {
		      e.printStackTrace();
		    } 
		 }
		 
		 /*
		  * Methode, die ein bestehendes Pinnwand-Objekt in der Datenbank updated.
		  */
		 public void updatePinnwand (Pinnwand p) {
			 
			//Aufbau der DBVerbindung
			Connection con = DBConnection.connection();
					
			//Versuch der Abfrage
			try {
					    	
				//leeres SQL-Statement anlegen	
				Statement stmt = con.createStatement();
					      
				//Aktualisieren des Inhalts
				stmt.executeUpdate("UPDATE pinnwand SET UserFK=\"" + p.getOwnerId() + "\" WHERE PinnwandID=" + p.getPinnwandId());
					      						
			}
			catch (SQLException e) {
				e.printStackTrace();
			}

		 }
		 
		 /*
		  * Methode, die ein bestehendes Pinnwand-Objekt in der Datenbank löscht.
		  */

		 public void deletePinnwand(Pinnwand p) {
			
			//Aufbau der DBVerbindung
			Connection con = DBConnection.connection();
						
			//Versuch der Abfrage
			try {
						    	
				//leeres SQL-Statement anlegen	
				Statement stmt = con.createStatement();
			
			 stmt.executeUpdate("DELETE FROM pinnwand WHERE PinnwandID=" +p.getPinnwandId());
		 }
		 
		 catch (SQLException e) {
		      e.printStackTrace();
		    }
		}
		/* Ende: Standard-Mapper-Methoden
		 * ================================================================================================
		 * Beginn: Foreign Key-Mapper-Methoden
		 */
		 
		 /*
		  * Methode, die die Pinnwand eines Users anhand der UserId zurückgibt.
		  */
		 public Pinnwand findPinnwandByUserId(int userId) {
				
			Connection con = DBConnection.connection();
						
			//Versuch der Abfrage
			try{
					
				//leeres SQL-Statement anlegen
				Statement stmt = con.createStatement();
		
				//Suche alle Felder der Pinnwandtabelle anhand von ID
				ResultSet rs = stmt.executeQuery("SELECT * FROM pinnwand WHERE UserFK=" + userId);
 
					if (rs.next()) {
						// Ergebnis in Pinnwandobjekt umwandeln
						Pinnwand p = new Pinnwand();	
						p.setPinnwandId(rs.getInt("PinnwandID"));
						p.setOwnerId(rs.getInt("UserFK"));
						p.setCreationTimeStamp(rs.getTimestamp("CreationTimeStamp"));
						
						return p;

						}
					}
						
					catch (SQLException e) {
					   e.printStackTrace();
					   
					  }
				return null;
		 }
		 
	}


	/* Ende: Foreign Key-Mapper-Methoden
	 * ================================================================================================
	 * Beginn: Spezifische Methoden des Business Object Pinnwand
	 */		 

	/* Ende:  Spezifische Methoden des Business Object Pinnwand
	 * ================================================================================================
	 */	
//		Methode überflüsslig
//		public Vector<Pinnwand> findAllPinnwaende(){
//	    	Connection con = DBConnection.connection();
//	    	
//	    	Vector<Pinnwand> result = new Vector<Pinnwand>();
//	    	
//	    	try {
//	    		
//	    		Statement stmt = con.createStatement();
//	    		ResultSet rs = stmt.executeQuery("SELECT * FROM pinnwand" + "ORDED BY PinnwandID");
//	    		
//	    		while(rs.next()) {
//	    			Pinnwand p = new Pinnwand();
//	    			p.setId(rs.getInt("PinnwandID"));
//	    			p.setOwnerId(rs.getInt("UserFk"));
//	    			p.setCreationTimeStamp(rs.getTimestamp("CreationTimeStamp"));
//	    			
//	    			result.addElement(p);
//	    			
//	    		}
//	    		
//	    	}catch (SQLException e1) {
//	    		e1.printStackTrace();
//	    			
//	    			
//	    		}
//	    	return result;
//	    	
//	    }}
