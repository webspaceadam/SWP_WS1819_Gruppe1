
package de.hdm.softwarePraktikumGruppe1.server.db;

import java.util.ArrayList;
import java.util.Vector;
import java.sql.*;

import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Like;



/**
 * @author GianlucaBernert,Ulusserhat
 *
 */
public class LikeMapper {
	
	
	private static LikeMapper likeMapper = null;
	
	
	/** 
	* Konstruktor der Klasse
	 */
	protected LikeMapper() {
	}
	
	
	
	/**
	 * Diese Methode stellt die Singelton-Eigenschaft sicher, indem sie dafür sorgt, 
	 * dass nur eine einzige Instanz dieser Klasse existiert
	 */
	public LikeMapper likeMapper() {
		if (likeMapper == null) {
			likeMapper = new LikeMapper();
		    }
	
		return likeMapper;
	}
	
	
	/**
	 * Methode zum speichern eines Likes
	 */
	public void insertLike(Like l) {
		 Connection con = DBConnection.connection();

		    try {
				//leeres SQL-Statement anlegen
		      Statement stmt = con.createStatement();

		        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
		        stmt.executeUpdate("INSERT INTO like (LikeID,Beitrag_BeitragID, User_UserID) "
		            + "VALUES ("+ l.getBeitragId() + "','" + l.getOwnerId()+ "','" + l.getId());
		      }
		    
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
		

		    
	}
	
	/**
	 * Methode zum loeschen eines Likes
	 */
	public void deleteLike(Like l) {
		Connection con = DBConnection.connection();
		
		try {
			//leeres SQL-Statement anlegen
				Statement stmt = con.createStatement();

		      stmt.executeUpdate("DELETE FROM Like " + "WHERE LikeID=" + l.getId());
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
		  }


	
	/**
	 * Methode zum zählen aller Likes eines Beitrags
	 */
	public int countAllLikesFromBeitrag(Beitrag b, Like l) {
		

		int counter = 0;
		

		//DB-Verbindung holen
		Connection con = DBConnection.connection();
		
		try {
			//leeres SQL-Statement anlegen
			Statement stmt  = con.createStatement();
			
			// Statement ausfuellen und als Query an die DB schicken
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM like WHERE BeitragID=" + b.getCommentID() + "LikeID=" + l.getId());
					
					
					while (rs.next()) {
				        counter=rs.getInt(1);
				      }
					
					return counter;
					
				}

			    catch (SQLException e) {
			    		e.printStackTrace();
			    		

			    }
		return counter;
				
			}
	
	/**
	 * Methode zum suchen eines Beitrags anhand der User ID
	 */
	public Vector<Like> getLikeByUserId(int userID){
		
	
		Connection con = DBConnection.connection();
		Vector <Like> vector= new Vector<Like>();

		try {
			//leeres SQL-Statement anlegen
			Statement stmt = con.createStatement();
			
			// Statement ausfuellen und als Query an die DB schicken
			ResultSet rs = stmt.executeQuery("SELECT * FROM like WHERE UserID=" + userID);

			while (rs.next()) {

		        Like l = new Like();
		        l.setId(rs.getInt("Like_ID"));
		        l.setOwnerId(rs.getInt("User_UserID"));
		        l.setBeitragId(rs.getInt("Beitrag_BeitragID"));
		        vector.add(l);

		      }
			return vector;
		}

	    catch (SQLException e) {
	    		e.printStackTrace();
	    }
		return null;
	 }
}