
package de.hdm.softwarePraktikumGruppe1.server.db;

import java.util.Vector;
import java.sql.*;

import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Like;
import de.hdm.softwarePraktikumGruppe1.shared.bo.User;



/**
 * @author GianlucaBernert
 * @autor Ulus Serhat
 * @author Yesin Soufi
 *
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
	
	
	/**
	 * Methode zum speichern eines Likes
	 */
	//Objekt -> Tupel
	public void insertLike(Like l) {
		 Connection con = DBConnection.connection();

		    try {
				//leeres SQL-Statement anlegen
		      Statement stmt = con.createStatement();

		        // Jetzt erst erfolgt die tats�chliche Einf�geoperation
		        stmt.executeUpdate("INSERT INTO like (BeitragFK, UserFK) "
		            + "VALUES (" + l.getBeitragId()+ ", " + l.getOwnerId()+")");
		      }
		    
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
		

		    
	}
	
	/**
	 * Methode zum loeschen eines Likes
	 */
	//Objekt -> Tupel
	public void deleteLike(Like l) {
		Connection con = DBConnection.connection();
		
		try {
				//leeres SQL-Statement anlegen
				Statement stmt = con.createStatement();
				stmt.executeUpdate("DELETE FROM like WHERE LikeID=" + l.getLikeID());
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
		  }
	
	
	/**
	 * Methode zum suchen eines Beitrags anhand der User ID
	 */
	public Vector<Like> getLikesOfUser(int userID){
		
	
		Connection con = DBConnection.connection();
		Vector <Like> vector= new Vector<Like>();

		try {
			//leeres SQL-Statement anlegen
			Statement stmt = con.createStatement();
			
			// Statement ausfuellen und als Query an die DB schicken
			ResultSet rs = stmt.executeQuery("SELECT * FROM like WHERE UserID=" + userID);

			while (rs.next()) {

		        Like l = new Like();	        
		        l.setOwnerId(rs.getInt("UserFK"));
		        l.setBeitragId(rs.getInt("BeitragFK"));
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
	/**
	 * Methode zum zählen aller Likes eines Beitrags
	 */
//	public int countAllLikesFromBeitrag(Beitrag b) {
//		
//
//		int counter = 0;
//		
//
//		//DB-Verbindung holen
//		Connection con = DBConnection.connection();
//		
//		try {
//			//leeres SQL-Statement anlegen
//			Statement stmt  = con.createStatement();
//			
//			// Statement ausfuellen und als Query an die DB schicken
//			ResultSet rs = stmt.executeQuery("SELECT * FROM like WHERE BeitragID=" + b.getId());
//					
//					
//					while (rs.next()) {
//				        counter++;
//				      }
//					
//					return counter;
//					
//				}
//
//			    catch (SQLException e) {
//			    		e.printStackTrace();
//			    		
//
//			    }
//		return counter;
//				
//	}
//			
	
//	/**
//	 * Methode zum suchen eines Beitrags anhand der User ID
//	 */
//	public Vector<Like> getLikeByUserId(int userID){
//		
//	
//		Connection con = DBConnection.connection();
//		Vector <Like> vector= new Vector<Like>();
//
//		try {
//			//leeres SQL-Statement anlegen
//			Statement stmt = con.createStatement();
//			
//			// Statement ausfuellen und als Query an die DB schicken
//			ResultSet rs = stmt.executeQuery("SELECT * FROM like WHERE UserID=" + userID);
//
//			while (rs.next()) {
//
//		        Like l = new Like();	        
//		        l.setOwnerId(rs.getInt("User_UserID"));
//		        l.setBeitragId(rs.getInt("Beitrag_BeitragID"));
//		        vector.add(l);
//
//		      }
//			return vector;
//		}
//
//	    catch (SQLException e) {
//	    		e.printStackTrace();
//	    }
//		return null;
//	 }


//		// Methode, die alle Likes eines Beitrags löscht
//
//	public void deleteAllLikesFromBeitrag(Beitrag b) {
//		
//		
//	}
//	
	//Methode, die alle Likes eines Beitrags zurückgibt
	public Vector<Like> getLikesOfBeitrag(int beitragId){
		Connection con = DBConnection.connection();
		Vector<Like> likesOfBeitrag = new Vector<Like>();
		
		try {
			//leeres SQL-Statement anlegen
			Statement stmt = con.createStatement();
			
			// Statement ausfuellen und als Query an die DB schicken
			ResultSet rs = stmt.executeQuery("SELECT * FROM like WHERE BeitragFK=" + beitragId);

			while (rs.next()) {
		        Like l = new Like();	        
		        l.setOwnerId(rs.getInt("UserFK"));
		        l.setBeitragId(rs.getInt("BeitragFK"));
		        l.setCreationTimeStamp(rs.getTimestamp("CreationTimeStamp"));
		        likesOfBeitrag.add(l);
		        }
		}catch(SQLException e) {
				e.printStackTrace();
				return null;
			}
		
		return likesOfBeitrag;
	}
	
		// Methode, die eine LikeBeziehung zwischen Nutzer und Beitrag prüft
	
	public Like likeCheck(User u, Beitrag b) {
		
		Connection con = DBConnection.connection();
		
		
			try {
				Statement stmt = con.createStatement();
				
				// Statement ausfuellen und als Query an die DB schicken
				ResultSet rs = stmt.executeQuery("SELECT * FROM like WHERE UserFK=" + u.getUserId() + " & BeitragFK=" + b.getBeitragId());
				
					if (rs.next()) {
						Like l = new Like();
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
//
//	public void deleteAllLikesFromUser(User u) {
//		// TODO Auto-generated method stub
//		
//	}
}