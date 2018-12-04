
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
		      Statement stmt = con.createStatement();

		       
		        stmt = con.createStatement();

		        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
		        stmt.executeUpdate("INSERT INTO like (LikeID, creationTimeStamp,UserID,KommentarID) "
		            + "VALUES (" + l.getOwnerId() + ",'" + l.getCreationTimeStamp() + "','" + l.getOwnerId() + ",'" + l.getBeitragId() );
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
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("DELETE FROM Like " + "WHERE id=" + l.getOwnerId());
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
			
			//Suche alle Likes zu einem Beitrag
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM like WHERE BeitragID=" + b.getId(b) + "LikeID=" + l.getId(l));
					
					
					while (rs.next()) {
				        counter=rs.getInt(1);
				      }

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
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM like WHERE UserID=" + userID);

			while (rs.next()) {

		        Like l = new Like();
		        l.setOwnerId(rs.getInt("Like_ID"));
		        l.setCreationTimeStamp(rs.getTimestamp("CreationTimeStamp"));
		        l.setOwner(UserMapper.UserMapper().findeUserByUserId(rs.getInt("UserID")));
		        vector.add(l);

		      }
			return vector;
		}

	    catch (SQLException e) {
	    		e.printStackTrace();
	    }
		return vector;
	 }
}