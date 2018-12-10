
package de.hdm.softwarePraktikumGruppe1.server.db;
import java.sql.*;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Kommentar;
import de.hdm.softwarePraktikumGruppe1.shared.bo.User;



/**
 * @author GianlucaBernert, 
 * @author Yesin Soufi
 * @author SerhatUlus
 *
 */

public class KommentarMapper {
	
	// Variable die besagt ob schon kommentarMapperverbindung besteht
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
		        stmt.executeUpdate("INSERT INTO kommentar (Inhalt,Beitrag_BeitragID, User_UserID) "
		            + "VALUES (" + k.getText() + "','" + k.getBeitragId() + "','" + k.getOwnerId()
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
		      stmt.executeUpdate("DELETE FROM kommentar WHERE KommentarID=" + k.getId());
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
				      stmt.executeUpdate("UPDATE kommentar SET Inhalt=\"" + k.getText() + "\" WHERE KommentarID=" + k.getId());
				      						
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
				        k.setText(rs.getString("inhalt"));
				        k.setOwnerId(rs.getInt("User_UserID"));
				        k.setBeitragId(rs.getInt("Beitrag_BeitragID"));
				        
				        
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
		 
	    
				 public int countAllKommentareFromBeitrag(Beitrag beitrag){
					 int counter = 0; //Zähler
					 
					//Aufbau der DBVerbindung
					Connection con = DBConnection.connection();
					
					//Versuch der Abfrage
					try{
						
						//leeres SQL-Statement anlegen
						Statement stmt = con.createStatement();
						
						//Suche alle Beiträge
						ResultSet rs = stmt.executeQuery("SELECT * FROM Kommentar WHERE Beitrag_BeitragID=" +beitrag.getId());

					    
						while (rs.next()) {
					        counter++;
					      }
						
					}
					
				    catch (SQLException e) {
				    		e.printStackTrace();
				    }
	    
					return counter;
					
				 }
	 
		 
		 public int countKommentareFromUser(User user){
			 
			 int counter = 0; //Zähler
			 
			//Aufbau der DBVerbindung
			Connection con = DBConnection.connection();
			
			//Versuch der Abfrage
			try{
				
				//leeres SQL-Statement anlegen
				Statement stmt = con.createStatement();
				
				//Suche alle Beiträge
				ResultSet rs = stmt.executeQuery("SELECT * FROM kommentar WHERE User_UserID=" +user.getId());
													
			    		
				while (rs.next()) {
			        counter++;
			      }
				
			}
			
		    catch (SQLException e) {
		    		e.printStackTrace();
		    }

			return counter;
			
		 }
}
	 