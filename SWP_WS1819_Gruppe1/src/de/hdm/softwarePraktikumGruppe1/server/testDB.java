package de.hdm.softwarePraktikumGruppe1.server;

import java.sql.Timestamp;

import de.hdm.softwarePraktikumGruppe1.shared.bo.Abonnement;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Kommentar;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Like;
import de.hdm.softwarePraktikumGruppe1.shared.bo.User;

public class testDB {
	
	private static Timestamp timestamp = new Timestamp(System.currentTimeMillis());

	public static void main(String[] args) {
		
		PinnwandverwaltungImpl impl = new  PinnwandverwaltungImpl();
		impl.init();
		
		Beitrag b1 = new Beitrag();
		
		b1.setBeitragID(3);
		
		
		Like l1 = new Like();
		l1.setLikeID(2);
		l1.setBeitragId(3);
		l1.setOwnerId(2);
		
		System.out.println(l1.toString());
		
		impl.deleteLike(l1);
	}

}


/*
 * Connection con = DBConnection.connection();
		
		User user = new User();
		
		user.setFirstName("Adam");
		user.setLastName("Schwarz");
		user.setNickname("adam");

		System.out.println("INSERT INTO user (FirstName, LastName, Nickname) " + "VALUES (" + 
						"'" + user.getFirstName() + "'," + 
						"'" + user.getLastName() + "'," + 
						"'" + user.getNickname() + "'");
		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(UserID) AS maxid " + "FROM user ");

			if (rs.next()) {
				
				user.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();
				
				
				stmt.executeUpdate("INSERT INTO user (FirstName, LastName, Nickname) " + "VALUES (" + 
						"'" + user.getFirstName() + "'," + 
						"'" + user.getLastName() + "'," + 
						"'" + user.getNickname() + "'" +
						");"
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
