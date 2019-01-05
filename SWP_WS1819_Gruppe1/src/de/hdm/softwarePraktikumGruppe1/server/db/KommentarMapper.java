
package de.hdm.softwarePraktikumGruppe1.server.db;
import java.sql.*;
import java.util.Vector;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Kommentar;




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
	 
	 /*
	  * =============================================================================================
	  * Beginn: Standard-Mapper-Methoden. Innerhalb dieses Bereichs werden alle Methoden aufgezählt, die
	  * in allen Mapper-Klassen existieren.
	  */
	 
	 /*
	  * Methode, die einen Kommentar ahnand einer Id zurueckgibt
	  */
	 	public Kommentar findKommentarById(int kommentarID){
			
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
				k.setInhalt(rs.getString("Inhalt"));
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
	
	 /*
	  * Methode, die das Anlegen eines Kommentarobjekts in der Datenbank ermöglicht.
	  */
	 	public void insertKommentar(Kommentar k){
	
			//Aufbau der DBVerbindung
			Connection con = DBConnection.connection();
			
			try{
				
				//leeres SQL-Statement anlegen
				Statement stmt = con.createStatement();
		        stmt.executeUpdate("INSERT INTO kommentar (Inhalt,BeitragFK, UserFK) VALUES ('" + k.getInhalt() + "', " + k.getBeitragId() + "," + k.getOwnerId()+")");			                 
 
			}
		
			catch (SQLException e) {
				e.printStackTrace();
	      
			}
	 	}
	 	
	 /*
	  * Methode, die das Updaten eines Kommentarobjekts in der Datenbank ermöglicht.
	  */
	 	 public void updateKommentar(Kommentar k){
			//Aufbau der DBVerbindung
			Connection con = DBConnection.connection();
				
			//Versuch der Abfrage
			try {
				//leeres SQL-Statement anlegen	
				Statement stmt = con.createStatement();
				stmt.executeUpdate("UPDATE kommentar SET Inhalt=\"" + k.getInhalt() + "\" WHERE KommentarID=" + k.getKommentarId());
				      						
				    }	
				    catch (SQLException e) {
				      e.printStackTrace();
				    } 
			 }
	 	
	 /*
	  * Methode, die das Löschen eines Beitragobjekts aus der Datenbank ermöglicht.
	  */		 	
	 	public void deleteKommentar(Kommentar k){
			//Aufbau der DBVerbindung
			Connection con = DBConnection.connection();
			
		    try {	
		      Statement stmt = con.createStatement();
		      stmt.executeUpdate("DELETE FROM kommentar WHERE KommentarID=" + k.getKommentarId());
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
		 * Methode, die einen Vector mit allen Kommentaren eines Users zurückgibt.
		 */
		 public Vector<Kommentar> findKommentareOfUser(int userId){
			//Aufbau der DBVerbindung
			Connection con = DBConnection.connection();
			// Initialisierung eines leeren Vectors welcher Kommentar-Objekte enthalten kann
			Vector<Kommentar> result = new Vector<Kommentar>();
			
			try {
				Statement stmt = con.createStatement();
				
				ResultSet rs = stmt.executeQuery("SELECT * FROM kommentar WHERE UserFK=" +"'"+ userId+ "'" + "ORDER BY KommentarID");
				
				while (rs.next()) {
					Kommentar k = new Kommentar();
					k.setBeitragId(rs.getInt("BeitragFK"));
					k.setOwnerId(rs.getInt("UserFK"));
					k.setText(rs.getString("Inhalt"));
					k.setCreationTimeStamp(rs.getTimestamp("CreationTimeStamp"));
					
					result.add(k);
				}
				
			}catch (SQLException e) {
	    		e.printStackTrace();
			}

			return result;
	 
		 }
		 /*
		  * Methode, die einen Vector mit allen Kommentaren eines Users zurückgibt.
		 */ 
		 public Vector<Kommentar> findKommentareOfBeitrag(int beitragId){
			//Aufbau der DBVerbindung
			Connection con = DBConnection.connection();
			// Initialisierung eines leeren Vectors welcher Kommentar-Objekte enthalten kann
			Vector<Kommentar> result = new Vector<Kommentar>();
				
				try {
					Statement stmt = con.createStatement();
					
					ResultSet rs = stmt.executeQuery("SELECT * FROM kommentar WHERE BeitragFK=" +"'"+ beitragId+ "'" + "ORDER BY KommentarID");
					
					while (rs.next()) {
						Kommentar k = new Kommentar();
						k.setBeitragId(rs.getInt("BeitragFK"));
						k.setOwnerId(rs.getInt("UserFK"));
						k.setText(rs.getString("Inhalt"));
						k.setCreationTimeStamp(rs.getTimestamp("CreationTimeStamp"));
						
						result.add(k);
					}
					
				}catch (SQLException e) {
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
