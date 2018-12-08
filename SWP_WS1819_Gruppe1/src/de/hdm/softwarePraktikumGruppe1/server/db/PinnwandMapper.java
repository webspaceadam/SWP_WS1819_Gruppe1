
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
		
		// Variable die besagt, ob schon userMapperverbindung besteht
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
		 
		 
		//Objekt -> Tupel
		 public void insertPinnwand (Pinnwand p) {
			 
			//leeres SQL-Statement anlegen
			 Connection con = DBConnection.connection();
		 
		 try{
			 
			 Statement stmt = con.createStatement();
			 
			        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
			        stmt.executeUpdate("INSERT INTO pinnwand (PinnwandID, User_UserID) "
			            + "VALUES (" + p.getId() + ",'" + p.getOwnerId());
		      	
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
			 
			 
			 stmt.executeUpdate("DELETE FROM pinnwand WHERE User_UserID =" + p.getOwnerId() + " PinnwandID=" +p.getId());
		 }
		 
		 catch (SQLException e) {
		      e.printStackTrace();
		    }
			 

		}
		 
		//Tupel -> Objekt
		 public Pinnwand findPinnwandByUserID(User u) {
				
			
				Connection con = DBConnection.connection();
						
				//Versuch der Abfrage
				try{
					
					//leeres SQL-Statement anlegen
					Statement stmt = con.createStatement();
					
					//Suche alle Felder der Pinnwandtabelle anhand von ID
					ResultSet rs = stmt.executeQuery("SELECT * FROM pinnwand " + "WHERE User_UserID=" + u.getId());

					 
					if (rs.next()) {
						// Ergebnis in Pinnwandobjekt umwandeln
						Pinnwand p = new Pinnwand();
						p.setId(rs.getInt("PinnwandID"));
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
			 
			 
			 
			 
			 
			 