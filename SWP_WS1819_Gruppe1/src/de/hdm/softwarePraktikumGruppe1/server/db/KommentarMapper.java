
package de.hdm.softwarePraktikumGruppe1.server.db;
import java.sql.*;
import java.util.ArrayList;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Kommentar;
import de.hdm.softwarePraktikumGruppe1.shared.bo.User;



/**
 * @author GianlucaBernert, 
 * @author Yesin Soufi
 * @author Serhat Ulus
 *
 */

public class KommentarMapper {
	// Variable die besagt ob schon kommentarMapperverbindung besteht
	 private static KommentarMapper kommentarMapper = null;
	 
	 
	 //leerer Konstruktor wegen Singleton
	 protected KommentarMapper() {
	 }

	 
	 //Singleton "Konstruktor"-methode
	 public static KommentarMapper kommentarMapper(){
		 if (kommentarMapper==null){
			 kommentarMapper= new KommentarMapper();
		 }
		 return kommentarMapper;
	 }
	 
	 
	 public void insertKommentar(Kommentar k){
			//Aufbau der DBVerbindung
			Connection con = DBConnection.connection();
			
			try{
				
				Statement stmt = con.createStatement();
				
		        // Jetzt erst erfolgt die tatsï¿½chliche Einfï¿½geoperation
		        stmt.executeUpdate("INSERT INTO kommentar (KommentarID, Inhalt, UserID, CreationTimeStamp, BeitragID) "
		            + "VALUES (" + k.getId(k) + ",'"  + k.getText() + "','" + k.getOwnerId() + "','" + k.getCreationTimeStamp()
		        	+ k.getBeitragId() +"')");
		       
	      }
		
	    catch (SQLException e) {
	      e.printStackTrace();
	      
	    }
	 }
	    
	    public void deleteKommentar(Kommentar k){
			//Aufbau der DBVerbindung
			Connection con = DBConnection.connection();
			
			//Versuch der Abfrage
		    try {
		      Statement stmt = con.createStatement();
		      //Lï¿½sche Beitrag mit gleicher ID aus Tabelle
		      stmt.executeUpdate("DELETE FROM kommentar WHERE KommentarID=" + k.getId(k) + "UserID=" + k.getOwnerId() );
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    } 
		 }
	    
		 public void updateKommentar(Kommentar k){
				//Aufbau der DBVerbindung
				Connection con = DBConnection.connection();
				
					//Versuch der Abfrage
				    try {
				      Statement stmt = con.createStatement();
				      //Aktualisieren des Inhalts
				      stmt.executeUpdate("UPDATE kommentar SET Inhalt=\"" + k.getText() + "\" WHERE KommentarID=" + k.getOwnerId());
			
				    }
				    catch (SQLException e) {
				      e.printStackTrace();
				    }

			 
				 
			 }
	    
		 public Kommentar findKommentarByKommentarID(int kommentarID){
				//Aufbau der DBVerbindung
				Connection con = DBConnection.connection();
				
				//Versuch der Abfrage
				try{
					Statement stmt = con.createStatement();
					//Suche alle Felder der Kommentartabelle anhand von ID
					ResultSet rs = stmt.executeQuery("SELECT * FROM kommentar WHERE KommentarID=" + kommentarID );
					
					//Maximal ein Rückgabewert da Id Primärrschlüssel
					if (rs.next()) {
				        // Ergebnis in Beitrag- Objekt umwandeln
				        Kommentar k = new Kommentar();
				        k.setId(rs.getInt("KommentarID"));
				        k.setCreationTimeStamp(rs.getTimestamp("CreationTimeStamp"));
				        k.setText(rs.getString("inhalt"));
				        k.setOwnerId(rs.getInt("UserID"));
				        
				        
				        //Kommentar Objekt zurï¿½ckgeben
				        return k;
					}
				}
			    catch (SQLException e) {
			      e.printStackTrace();
			     
		 }
				// falls keine gefundene leere Liste
				return null;
		 }
		 
	    
				 public int countAllKommentareFromBeitrag(Beitrag beitrag){
					 int counter = 0; //Zähler
					//Aufbau der DBVerbindung
					Connection con = DBConnection.connection();
					
					//Versuch der Abfrage
					try{
						Statement stmt = con.createStatement();
						//Suche alle Beitrag
						ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Kommentar WHERE BeitragID=" +beitrag.getId(beitrag));

					    //Maximal ein Rückgabewert da Id Primärschlüssel
						while (rs.next()) {
					        counter=rs.getInt(1);
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
				Statement stmt = con.createStatement();
				//Suche alle Beitrag
				ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM User WHERE BeitragID=" +user.getId(user));

			    //Maximal ein Rükgabewert da Id Primärschlï¿½ssel
				while (rs.next()) {
			        counter=rs.getInt(1);
			      }
				
			}
			
		    catch (SQLException e) {
		    		e.printStackTrace();
		    }

			return counter;
			
		 }

}













	 