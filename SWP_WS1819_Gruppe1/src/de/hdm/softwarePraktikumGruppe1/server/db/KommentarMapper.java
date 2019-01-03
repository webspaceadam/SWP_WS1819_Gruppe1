
package de.hdm.softwarePraktikumGruppe1.server.db;
import java.sql.*;
import java.util.Vector;

import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Kommentar;
import de.hdm.softwarePraktikumGruppe1.shared.bo.User;



/**
 * @author GianlucaBernert, 
 * @author Ulus Serhat
 * @author Yesin Soufi
 * @author SebastianHermann
 * 
 *
 */

public class KommentarMapper {
	
	
	/* Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	   * einzige Instanz dieser Klasse.--> Singeltoneigenschaft
	   */
	 private static KommentarMapper kommentarMapper = null;
	 
	 
	 
		//Ein geschützter Konstruktor der weitere Instanzierungen von LikeMapper Objekten verhindert.
	 protected KommentarMapper() {
	 }

	 
	//Diese Methode stellt die Singelton-Eigenschaft sicher, indem sie dafür sorgt, 
	//dass nur eine einzige Instanz dieser Klasse existiert.
	 public static KommentarMapper kommentarMapper(){
		 if (kommentarMapper==null){
			 kommentarMapper= new KommentarMapper();
		 }
		 return kommentarMapper;
	 }
	 
	//Objekt -> Tupel
	 public void insertKommentar(Kommentar k){
		 
			//Aufbau der DBVerbindung
			Connection con = DBConnection.connection();
			
			try{
				
				//leeres SQL-Statement anlegen
				Statement stmt = con.createStatement();
				
		        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
		        stmt.executeUpdate("INSERT INTO kommentar (Inhalt, BeitragFK, UserFK) "
		            + "VALUES ('" + k.getInhalt() + "','" + k.getBeitragId() + "','" + k.getOwnerId()
		        	 +"')");			                 
		       
	      }
		
	    catch (SQLException e) {
	      e.printStackTrace();
	      
	    }
	 }
	 	//Objekt -> Tupel
	    public void deleteKommentar(Kommentar k){
			//Aufbau der DBVerbindung
			Connection con = DBConnection.connection();
			
			//Versuch der Abfrage
		    try {
		    	
				//leeres SQL-Statement anlegen	
		      Statement stmt = con.createStatement();
		      
		      //Lösche Beitrag mit gleicher ID aus Tabelle
		      stmt.executeUpdate("DELETE FROM kommentar WHERE KommentarID=" + k.getKommentarId());
		    }																
		    catch (SQLException e) {	
		      e.printStackTrace();
		    } 
		 }
	    
	    //Objekt -> Tupel
		 public void updateKommentar(Kommentar k){
				//Aufbau der DBVerbindung
				Connection con = DBConnection.connection();
				
					//Versuch der Abfrage
				    try {
				    	
						//leeres SQL-Statement anlegen	
				      Statement stmt = con.createStatement();
				      
				      //Aktualisieren des Inhalts
				      stmt.executeUpdate("UPDATE kommentar SET Inhalt=\"" + k.getInhalt() + "\" WHERE KommentarID=" + k.getKommentarId());
				      						
				    }	
				    catch (SQLException e) {
				      e.printStackTrace();
				    }

			 
				 
			 }
		 
			//Methode die Tupels in Objekte umwandelt
		 	public Kommentar findKommentarByKommentarID(int kommentarID){
				
		 		//Aufbau der DBVerbindung
				Connection con = DBConnection.connection();
				
				//Versuch der Abfrage
				try{
					
					//leeres SQL-Statement anlegen
					Statement stmt = con.createStatement();
					
					//Suche alle Felder der Kommentartabelle anhand von ID
					ResultSet rs = stmt.executeQuery("SELECT * FROM kommentar WHERE KommentarID=" + kommentarID );
					
					
					if (rs.next()) {
						
				        // Ergebnis in Beitragobjekt umwandeln
				        Kommentar k = new Kommentar();
				        k.setText(rs.getString("Inhalt"));
				        k.setOwnerId(rs.getInt("UserFK"));
				        k.setBeitragId(rs.getInt("BeitragFK"));
				        
				        
				        //Kommentarobjekt zurückgeben
				        return k;
					}
				}
			    catch (SQLException e) {
			      e.printStackTrace();
			     
		 }
				// falls keine gefundene Liste, dann gibst du nichts zurück
				return null;
		 }
		 
	    
//				 public int countAllKommentareFromBeitrag(Beitrag beitrag){
//					 int counter = 0; //Zähler
//					 
//					//Aufbau der DBVerbindung
//					Connection con = DBConnection.connection();
//					
//					//Versuch der Abfrage
//					try{
//						
//						//leeres SQL-Statement anlegen
//						Statement stmt = con.createStatement();
//						
//						//Suche alle Beiträge
//						ResultSet rs = stmt.executeQuery("SELECT * FROM Kommentar WHERE Beitrag_BeitragID=" +beitrag.getId());
//
//					    
//						while (rs.next()) {
//					        counter++;
//					      }
//						
//					}
//					
//				    catch (SQLException e) {
//				    		e.printStackTrace();
//				    }
//	    
//					return counter;
//					
//				 }
//	 
//		 
//		 public int countKommentareFromUser(User user){
//			 
//			 int counter = 0; //Zähler
//			 
//			//Aufbau der DBVerbindung
//			Connection con = DBConnection.connection();
//			
//			//Versuch der Abfrage
//			try{
//				
//				//leeres SQL-Statement anlegen
//				Statement stmt = con.createStatement();
//				
//				//Suche alle Beiträge
//				ResultSet rs = stmt.executeQuery("SELECT * FROM kommentar WHERE User_UserID=" +user.getId());
//													
//			    		
//				while (rs.next()) {
//			        counter++;
//			      }
//				
//			}
//			
//		    catch (SQLException e) {
//		    		e.printStackTrace();
//		    }
//
//			return counter;
//			
//		 }
		 
		 /*
		  * Methode, die einen Vector mit allen Kommentaren eines Users zurückgibt.
		  */
		 public Vector<Kommentar> getKommentareOfUser(int userId){
			//Aufbau der DBVerbindung
			Connection con = DBConnection.connection();
			// Initialisierung eines leeren Vectors welcher Kommentar-Objekte enthalten kann
			Vector<Kommentar> kommentareOfUser = new Vector<Kommentar>();
			
			try {
				Statement stmt = con.createStatement();
				
				ResultSet rs = stmt.executeQuery("SELECT * FROM kommentar"
				+"WHERE UserFK=" +"'"+ userId+ "'" + "ORDER BY KommentarID");
				
				while (rs.next()) {
					Kommentar k = new Kommentar();
					k.setBeitragId(rs.getInt("BeitragFK"));
					k.setOwnerId(rs.getInt("UserFK"));
					k.setText(rs.getString("Inhalt"));
					k.setCreationTimeStamp(rs.getTimestamp("CreationTimeStamp"));
					
					kommentareOfUser.add(k);
				}
				
			}catch (SQLException e) {
	    		e.printStackTrace();
			}

			return kommentareOfUser;
		
			 
		 }
		 
		 
		 //To be defined: getAllKommentarOfBeitrag
		 public Vector<Kommentar> getKommentareOfBeitrag(int beitragId){
			//Aufbau der DBVerbindung
				Connection con = DBConnection.connection();
				// Initialisierung eines leeren Vectors welcher Kommentar-Objekte enthalten kann
				Vector<Kommentar> kommentareOfBeitrag = new Vector<Kommentar>();
				
				try {
					Statement stmt = con.createStatement();
					
					ResultSet rs = stmt.executeQuery("SELECT * FROM kommentar"
					+"WHERE BeitragFK=" +"'"+ beitragId+ "'" + "ORDER BY KommentarID");
					
					while (rs.next()) {
						Kommentar k = new Kommentar();
						k.setBeitragId(rs.getInt("BeitragFK"));
						k.setOwnerId(rs.getInt("UserFK"));
						k.setText(rs.getString("Inhalt"));
						k.setCreationTimeStamp(rs.getTimestamp("CreationTimeStamp"));
						
						kommentareOfBeitrag.add(k);
					}
					
				}catch (SQLException e) {
		    		e.printStackTrace();
				}

				return kommentareOfBeitrag;
			
				 
		 }


//		public void deleteAllKommentareOfUser(User u) {
//			// TODO Auto-generated method stub
//			
//		}

}
