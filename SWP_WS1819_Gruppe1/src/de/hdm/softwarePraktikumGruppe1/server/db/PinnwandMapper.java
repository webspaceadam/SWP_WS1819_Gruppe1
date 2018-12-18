
package de.hdm.softwarePraktikumGruppe1.server.db;

import java.sql.*;

import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Like;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Pinnwand;
import de.hdm.softwarePraktikumGruppe1.shared.bo.User;

/**
 * @author GianlucaBernert 
 * @author Ulus Serhat
 * @author Yesin Soufi
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
		 
		 
		//Objekt -> Tupel
		 public void insertPinnwand (Pinnwand p) {
			 
			//leeres SQL-Statement anlegen
			 Connection con = DBConnection.connection();
		 
		 try{
			 
				//leeres SQL-Statement anlegen
			 Statement stmt = con.createStatement();
			 
			        // Jetzt erst erfolgt die tats�chliche Einf�geoperation
			        stmt.executeUpdate("INSERT INTO pinnwand (User_UserID) "
			            + "VALUES (" + p.getOwnerId());
		      	
		    }
			
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
			 

		 }


		 //Objekt -> Tupel
		public void deletePinnwand(Pinnwand p) {
			
			
			 Connection con = DBConnection.connection();
			 
		 try {
			 
			//leeres SQL-Statement anlegen
			 Statement stmt = con.createStatement();
			 
			 
			 stmt.executeUpdate("DELETE FROM pinnwand WHERE  PinnwandID=" +p.getId());
		 }
		 
		 catch (SQLException e) {
		      e.printStackTrace();
		    }
			 

		}
		 
		//Methode die Tupels -> Objekte umwandelt
		 public Pinnwand findPinnwandByUserID(User u) {
				
			
				Connection con = DBConnection.connection();
						
				//Versuch der Abfrage
				try{
					
					//leeres SQL-Statement anlegen
					Statement stmt = con.createStatement();
					
					//Suche alle Felder der Pinnwandtabelle anhand von ID
					ResultSet rs = stmt.executeQuery("SELECT * FROM pinnwand WHERE User_UserID=" + u.getId());

					 
					if (rs.next()) {
						// Ergebnis in Pinnwandobjekt umwandeln
						Pinnwand p = new Pinnwand();	
						p.setOwnerId(u.getId());
						return p;
						}
					}
						
					catch (SQLException e) {
					   e.printStackTrace();
					   
					  }
				return null;
		 }
	}