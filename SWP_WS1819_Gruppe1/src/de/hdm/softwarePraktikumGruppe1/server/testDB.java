package de.hdm.softwarePraktikumGruppe1.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.hdm.softwarePraktikumGruppe1.server.db.DBConnection;
import de.hdm.softwarePraktikumGruppe1.server.db.UserMapper;
import de.hdm.softwarePraktikumGruppe1.shared.bo.User;

public class testDB {

	private static UserMapper uMapper;

	public static void main(String[] args) {
		Connection con = DBConnection.connection();
		
		User user = new User();
		
		user.setFirstName("Adam");
		user.setLastName("gniady");
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
	}

}
