/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import de.hdm.softwarePraktikumGruppe1.shared.bo.Abonnement;

/**
 * @author GianlucaBerner
 * @author Yesin Soufi
 * 
 */
public class AbonnementMapper {
	
	private static AbonnementMapper abonnementMapper;
	
	/**
	 * Ein gesch�tzter Konstruktor der weitere Instanzierungen von AbonnementMapper Objekten verhindert.
	 */
	protected AbonnementMapper() {
	}
	
	/**
	 * Stellt die Singeleton-Eigenschaft der Mapperklasse sicher
	 * @return Sie gibt den AbonnementMapper zur�ck.
	 */
	
	public static AbonnementMapper AbonnementMapper() {
		if (abonnementMapper == null) {
			abonnementMapper = new AbonnementMapper();
		} 
		
		return abonnementMapper;
	}
	
public Abonnement findById(int id) {
		
		Connection con = DBConnection.connection();
		
		
	try {
		Statement stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery ("SELECT Abonnement_ID,  FROM Abonnement " + "WHERE Abonnement_id=" + id + " ORDER BY Abonnement_ID");
	
		if(rs.next()) {
			Abonnement a = new Abonnement();
			a.setOwnerId(rs.getInt("id"));
			
			
			return a;
		
			
		}
		}catch(SQLException e){
			e.printStackTrace();
			
			}
	return null;
		}
		
		public Vector<Abonnement> findAll(Vector<Abonnement> result){
			Connection con = DBConnection.connection();
			
			try {
				Statement stmt = con.createStatement();
				
				ResultSet rs = stmt.executeQuery("SELECT Abonnement_ID" + "FROM Abonnement" + "ORDED BY Abonnement_ID");
				
				while(rs.next()) {
					Abonnement a = new Abonnement();
					a.setOwnerId(rs.getInt("id"));
					
					
					result.addElement(a);
				}
				}catch (SQLException e) {
					e.printStackTrace();
				}
				
				return result;
					
				}
			
			public Abonnement insert(Abonnement a) {
				Connection con = DBConnection.connection();

				try {
					Statement stmt = con.createStatement();

					ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM User ");

					if (rs.next()) {
						
						a.setOwnerId(rs.getInt("maxid") + 1);

						stmt = con.createStatement();
						
						stmt.executeUpdate("INSERT INTO customers (User_ID) " + "VALUES (" + a.getOwnerId());
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return a;
				
			}
			
			public Abonnement update(Abonnement a) {
				Connection con = DBConnection.connection();

				try {
					Statement stmt = con.createStatement();

					stmt.executeUpdate("UPDATE User " + "SET Abonnement_ID=\"" + a.getOwnerId()  + "WHERE id=" + a.getOwnerId());

				} catch (SQLException e) {
					e.printStackTrace();
				}

				
				return a; 
			}
			
			public void delete(Abonnement a) {
				Connection con = DBConnection.connection();

				try {
					Statement stmt = con.createStatement();

					stmt.executeUpdate("DELETE FROM Abonnement " + "WHERE id=" + a.getOwnerId());
				} catch (SQLException e) {
					e.printStackTrace();
					} 
				}
				
				
}