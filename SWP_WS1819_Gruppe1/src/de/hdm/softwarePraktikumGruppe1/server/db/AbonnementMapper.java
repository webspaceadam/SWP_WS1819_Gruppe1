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
import de.hdm.softwarePraktikumGruppe1.shared.bo.Pinnwand;
import de.hdm.softwarePraktikumGruppe1.shared.bo.User;

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
	
	public static AbonnementMapper abonnementMapper() {
		if (abonnementMapper == null) {
			abonnementMapper = new AbonnementMapper();
		} 
		
		return abonnementMapper;
	}
	
	public Abonnement findById(int abonnementId) {
		
		Connection con = DBConnection.connection();
		
		
		try {
			Statement stmt = con.createStatement();
		
			ResultSet rs = stmt.executeQuery ("SELECT*  FROM abonnement WHERE AbonnementID=" + abonnementId + " ORDER BY Abonnement_ID");
	
				if(rs.next()) {
				Abonnement a = new Abonnement();
				a.setOwnerId(rs.getInt("AbonnementID"));
				a.setOwnerId(rs.getInt("UserFK"));
				a.setPinnwandId(rs.getInt("PinnwandFK"));
				
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
				
			ResultSet rs = stmt.executeQuery("SELECT *" + "FROM Abonnement" + "ORDER BY AbonnementID");
				
			while(rs.next()) {
				Abonnement a = new Abonnement();
				a.setAbonnementId(rs.getInt("AbonnementID"));
				a.setOwnerId(rs.getInt("UserFK"));
				a.setPinnwandId(rs.getInt("PinnwandFK"));
				
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

					ResultSet rs = stmt.executeQuery("SELECT MAX(AbonnementID) AS maxid " + "FROM abonnement ");

					if (rs.next()) {
						
						a.setOwnerId(rs.getInt("maxid") + 1);

						stmt = con.createStatement();
						
						stmt.executeUpdate("INSERT INTO abonnement (AbonnementID, PinnwandFK, UserFK) " + "VALUES (" + 
								"'" + a.getAbonnementId() + "'," + 
								"'" + a.getPinnwandId() + "'," + 
								"'" + a.getOwnerId() + "'");
						
								
					
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return a;
				
			}
			
			//?????????
			public Abonnement update(Abonnement a) {
				Connection con = DBConnection.connection();

				try {
					Statement stmt = con.createStatement();

					stmt.executeUpdate("UPDATE User " + "SET AbonnementID=\"" + a.getAbonnementId()  + "WHERE id=" + a.getOwnerId());

				} catch (SQLException e) {
					e.printStackTrace();
				}

				
				return a; 
			}
			
			public void deleteAbonnement(Abonnement a) {
				Connection con = DBConnection.connection();

				try {
					Statement stmt = con.createStatement();

					stmt.executeUpdate("DELETE FROM abonnement " + "WHERE AbonnementID=" + a.getAbonnementId());
				} catch (SQLException e) {
					e.printStackTrace();
					} 
				}
			
			public Vector<Abonnement> getAbonnementsOfUser(int userId){
				
				Connection con= DBConnection.connection();
				Vector <Abonnement> result = new Vector <Abonnement>();
				
			try {
				
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM abonnement" + "WHERE UserFK=" + userId);
						
						while(rs.next()) {
							
							Abonnement a = new Abonnement();
							
							a.setAbonnementId(rs.getInt("AbonnementID"));
							a.setPinnwandId(rs.getInt("PinnwandFK"));
							a.setOwnerId(rs.getInt("UserFK"));
							result.addElement(a);
						}
						
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return result;
			
				
			
			}
			
			public Vector<Abonnement> getAbonnementsOfPinnwand(int pinnwandId){
				
				Connection con= DBConnection.connection();
				Vector <Abonnement> result = new Vector <Abonnement>();
				
			try {
				
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM abonnement" + "WHERE PinnwandFK=" + pinnwandId);
						
						while(rs.next()) {
							
							Abonnement a = new Abonnement();
							a.setAbonnementId(rs.getInt("AbonnementID"));
							a.setPinnwandId(rs.getInt("PinnwandFK"));
							a.setOwnerId(rs.getInt("UserFK"));
							result.addElement(a);
						}
						
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return result;
			
}

			
				
}