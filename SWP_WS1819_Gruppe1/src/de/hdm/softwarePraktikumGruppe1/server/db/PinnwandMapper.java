
package de.hdm.softwarePraktikumGruppe1.server.db;

import java.sql.*;

import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Like;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Pinnwand;
import de.hdm.softwarePraktikumGruppe1.shared.bo.User;

/**
 * @author GianlucaBernert, SerhatUlus
 *
 */
	public class PinnwandMapper {
		
		// Variable die besagt ob schon nutzerMapperverbindung besteht
		private static PinnwandMapper pinnwandMapper = null;

		
		//leerer Konstruktor wegen Singleton
		protected PinnwandMapper() {
		}

		
		//Singleton "Konstruktor"-methode
		 public static PinnwandMapper pinnwandMapper(){
			if (pinnwandMapper==null){
				 pinnwandMapper= new PinnwandMapper();
			}
			 return pinnwandMapper;
			}
		 
		 
		 
		 public void insertPinnwand (Pinnwand p) {
			 
			 Connection con = DBConnection.connection();
		 
		 try{
			 
			 Statement stmt = con.createStatement();
			 
			        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
			        stmt.executeUpdate("INSERT INTO pinnwand (PinnwandID, UserID, CreationTimeStamp) "
			            + "VALUES (" + p.getId(p) + ",'" + p.getOwnerId()+ ",'" + p.getCreationTimeStamp());
		      	
		    }
			
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
			 

		 }



		public void deletePinnwand(Pinnwand p) {
			

			 Connection con = DBConnection.connection();
			 
		 try {
			 Statement stmt = con.createStatement();
			 
			 
			 stmt.executeUpdate("DELETE FROM `pinnwand` " + "WHERE `UserId`=" + p.getOwnerId() + " `pinnwandID`=" +p.getId(p));
		 }
		 
		 catch (SQLException e) {
		      e.printStackTrace();
		    }
			 

		}
		 
	
		 public Pinnwand findPinnwandByUserID(User u) {
				
				//Aufbau der DBVerbindung
				Connection con = DBConnection.connection();
						
				//Versuch der Abfrage
				try{
					Statement stmt = con.createStatement();
					//Suche alle Felder der Pinnwandtabelle anhand von ID
					ResultSet rs = stmt.executeQuery("SELECT * FROM pinnwand " + "WHERE UserID=" + u.getId(u));

					 //Maximal ein Rückgabewert da Id Primärschlüssel
					if (rs.next()) {
						// Ergebnis in Pinnwandobjekt umwandeln
						Pinnwand p = new Pinnwand();
						p.setId(rs.getInt("PinnwandID"));
						p.setCreationTimeStamp(rs.getDate("CreationTimeStamp"));
						p.setOwner(u);   	
						return p;
						}
					}
						
					catch (SQLException e) {
					   e.printStackTrace();
					   
					  }
				return null;
		 }
	}
			 
			 
			 
			 
			 
			 