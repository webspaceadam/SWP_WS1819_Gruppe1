package de.hdm.softwarePraktikumGruppe1.server;



public class testDB {

	public static void main(String[] args) {
		
		PinnwandverwaltungImpl impl = new  PinnwandverwaltungImpl();
		impl.init();
		
		impl.createSingleUserTestMethod("New", "User", "testUser");
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
