
package de.hdm.softwarePraktikumGruppe1.server.db;

import java.util.Date;
import java.util.Vector;
import java.sql.*;

import de.hdm.softwarePraktikumGruppe1.server.ReportGeneratorServiceImpl;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Like;




/**
 * @author GianlucaBernert
 * @autor Ulus Serhat
 * @author Yesin Soufi
 * @autor SebastianHermann
 */
public class LikeMapper {
	
	
	/* Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	   * einzige Instanz dieser Klasse.--> Singeltoneigenschaft
	   */
	private static LikeMapper likeMapper = null;
	
	
	//Ein geschützter Konstruktor der weitere Instanzierungen von LikeMapper Objekten verhindert.
	protected LikeMapper() {
	}
	
	 //Diese Methode stellt die Singelton-Eigenschaft sicher, indem sie dafür sorgt, 
	 //dass nur eine einzige Instanz dieser Klasse existiert.
	public static LikeMapper likeMapper() {
		if (likeMapper == null) {
			likeMapper = new LikeMapper();
		    }
	
		return likeMapper;
	}
	
	/*
	 * =============================================================================================
	 * Beginn: Standard-Mapper-Methoden. Innerhalb dieses Bereichs werden alle Methoden aufgezählt, die
	 * in allen Mapper-Klassen existieren.
	 */	
	
	/*
	 * Methode, die einen Like anhand einer Id zurueck gibt
	 */
		public Like findLikeById(int likeId) {
		//Aufbau der DBVerbindung
		Connection con = DBConnection.connection();
			
		//Versuch der Abfrage
		try{
				
			//leeres SQL-Statement anlegen
			Statement stmt = con.createStatement();
				
			//Suche alle Felder der Kommentartabelle anhand von ID
			ResultSet rs = stmt.executeQuery("SELECT * FROM `like` WHERE LikeID=" + likeId );
							
			if (rs.next()) {		
				// Ergebnis in Beitragobjekt umwandeln
				Like l = new Like();
				l.setLikeId(rs.getInt("LikeID"));
				l.setOwnerId(rs.getInt("UserFK"));
				l.setBeitragId(rs.getInt("BeitragFK"));
				        
				//Kommentarobjekt zurückgeben
				return l;
				}
			}
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
			return null;
		} 
	
	/*
	 * Methode, die das Anlegen eines Like-Objekts ermöglicht
	 */
		public void insertLike(Like l) {
			 Connection con = DBConnection.connection();
	
			try {
				
			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO `like`(BeitragFK, UserFK) VALUES (" + l.getBeitragId()+ ", " + l.getOwnerId()+")");
			}
			    
			catch (SQLException e) {
			e.printStackTrace();
			}
		}
		
	/*
	 * Methode, die das Updaten eines Like-Objekts in der Datenbank ermöglicht	
	 */
		public void updateLike(Like l){
			//Aufbau der DBVerbindung
			Connection con = DBConnection.connection();
				
			//Versuch der Abfrage
			try {
			//leeres SQL-Statement anlegen	
			Statement stmt = con.createStatement();
			//statement befuellen
			stmt.executeUpdate("UPDATE `like` SET BeitragFK=\"" + l.getBeitragId() + ", UserFK=\""+ l.getOwnerId() + "\" WHERE LikeID=" + l.getLikeId());
				      						
			}	
			catch (SQLException e) {
				e.printStackTrace();
			} 
		}
	
	/*
	 * Methode, die das Loeschen eines Like-Objekts aus der Datenbank ermöglicht
	 */
		public void deleteLike(Like l) {
			//Aufbau der DBVerbindung
			Connection con = DBConnection.connection();
			
			//Versuch der Abfrage
			try {
			//leeres SQL-Statement anlegen
			Statement stmt = con.createStatement();
			//statement befuellen
			stmt.executeUpdate("DELETE FROM `like` WHERE LikeID=" + l.getLikeId());
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
	 * Methode, die alle Likes eines Users zurueck gibt
	 */
		public Vector<Like> findLikesOfUser(int userId){
			
			Connection con = DBConnection.connection();
			Vector <Like> vector= new Vector<Like>();
	
			try {
				//leeres SQL-Statement anlegen
				Statement stmt = con.createStatement();
				
				// Statement ausfuellen und als Query an die DB schicken
				ResultSet rs = stmt.executeQuery("SELECT * FROM `like` WHERE UserFK=" + userId);
	
				while (rs.next()) {
	
			        Like l = new Like();	        
			        l.setOwnerId(rs.getInt("UserFK"));
			        l.setBeitragId(rs.getInt("BeitragFK"));
			        l.setLikeId(rs.getInt("LikeID"));
			        l.setCreationTimeStamp(rs.getTimestamp("CreationTimeStamp"));
			        vector.add(l);
	
			      }
				return vector;
			}
	
		    catch (SQLException e) {
		    		e.printStackTrace();
		    }
			return null;
		 }

	/*
	 * Methode, die alle Likes eines Users innerhalb eines Zeitraums zurueck gibt
	 */
		public Vector<Like> findLikesOfUserBetweenDates(int userId, Date start, Date end){
				
			Connection con = DBConnection.connection();
			Vector <Like> result = new Vector<Like>();
		
			try {
				//leeres SQL-Statement anlegen
				Statement stmt = con.createStatement();
					
				// Statement ausfuellen und als Query an die DB schicken
				ResultSet rs = stmt.executeQuery("SELECT * FROM `like` WHERE UserFK = " + userId +
						" AND CreationTimeStamp >= '" + ReportGeneratorServiceImpl.yearMonthDayFormat.format(start).toString() +
						"' AND CreationTimeStamp <= '" + ReportGeneratorServiceImpl.yearMonthDayFormat.format(end).toString() + "'");
		
				while (rs.next()) {
		
			        Like l = new Like();	        
			        l.setOwnerId(rs.getInt("UserFK"));
			        l.setBeitragId(rs.getInt("BeitragFK"));
			        l.setLikeId(rs.getInt("LikeID"));
			        l.setCreationTimeStamp(rs.getTimestamp("CreationTimeStamp"));
			        result.add(l);
		
			      }
			}
		    catch (SQLException e) {
		    		e.printStackTrace();
		    }
			return result;
		 }		

	/*
	 * Methode, die alle Likes eines Beitrags zurueck gibt
	 */
		public Vector<Like> findLikesOfBeitrag(int beitragId){
			Connection con = DBConnection.connection();
			Vector<Like> result = new Vector<Like>();
			
			try {
				//leeres SQL-Statement anlegen
				Statement stmt = con.createStatement();
				
				// Statement ausfuellen und als Query an die DB schicken
				ResultSet rs = stmt.executeQuery("SELECT * FROM `like` WHERE BeitragFK=" + beitragId);
	
				while (rs.next()) {
			        Like l = new Like();
			        l.setLikeId(rs.getInt("LikeID"));
			        l.setOwnerId(rs.getInt("UserFK"));
			        l.setBeitragId(rs.getInt("BeitragFK"));
			        l.setCreationTimeStamp(rs.getTimestamp("CreationTimeStamp"));
			        result.add(l);
			        }
			}catch(SQLException e) {
					e.printStackTrace();
					return null;
				}
			
			return result;
		}
	

	/*
	 * Methode, die alle Likes eines Beitrags innerhalb eines Zeitraums zurueck gibt
	 */
		public Vector<Like> findLikesOfBeitragBetweenDates(int beitragId, Date start, Date end){
			Connection con = DBConnection.connection();
			Vector<Like> result = new Vector<Like>();
				
			try {
				//leeres SQL-Statement anlegen
				Statement stmt = con.createStatement();
					
				// Statement ausfuellen und als Query an die DB schicken
				ResultSet rs = stmt.executeQuery("SELECT * FROM `like` WHERE BeitragFK=" + beitragId +
						" AND CreationTimeStamp >= '" + ReportGeneratorServiceImpl.yearMonthDayFormat.format(start).toString() +
						"' AND CreationTimeStamp <= '" + ReportGeneratorServiceImpl.yearMonthDayFormat.format(end).toString() + "'");
				
				while (rs.next()) {
		   	        Like l = new Like();
			        l.setLikeId(rs.getInt("LikeID"));
			        l.setOwnerId(rs.getInt("UserFK"));
			        l.setBeitragId(rs.getInt("BeitragFK"));
			        l.setCreationTimeStamp(rs.getTimestamp("CreationTimeStamp"));
				    result.add(l);
				   }
			}catch(SQLException e) {
					e.printStackTrace();
					return null;
				}
				
			return result;
		}		
	/*
	 *  Methode, die den Like eines Users von einem bestimmten Beitrag zurueck gibt
	 */
		public Like findLikeOfUserAndBeitrag(int userId, int beitragId) {
			
			Connection con = DBConnection.connection();
			
				try {
				Statement stmt = con.createStatement();
					
				// Statement ausfuellen und als Query an die DB schicken
				ResultSet rs = stmt.executeQuery("SELECT * FROM `like` WHERE UserFK=" + userId + " & BeitragFK=" + beitragId);
					
				if (rs.next()) {
					Like l = new Like();
					l.setLikeId(rs.getInt("LikeID"));
					l.setBeitragId(rs.getInt("BeitragFK"));
					l.setOwnerId(rs.getInt("UserFK"));		
					return l;
				}
				else {
					return null;
				}
				}
				catch(SQLException e) {
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