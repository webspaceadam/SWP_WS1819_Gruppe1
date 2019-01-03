package de.hdm.softwarePraktikumGruppe1.server;

import java.sql.Timestamp;

import de.hdm.softwarePraktikumGruppe1.shared.bo.User;

public class testDB {
	
	private static Timestamp timestamp = new Timestamp(System.currentTimeMillis());

	public static void main(String[] args) {
		
		PinnwandverwaltungImpl impl = new  PinnwandverwaltungImpl();
		impl.init();
		
		//impl.createUser("Adam", "Gniady", "geilerDude", "a@hot,de", timestamp);
		
		User u1 = impl.searchUserById(1);
		
		System.out.println(u1.getGMail());
	
		
		u1.setGMail("adam1995@gmail.com");
		
		
		impl.editUser(u1);
		
		
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
