package de.hdm.softwarePraktikumGruppe1.server.db;

import java.sql.*;

/**
 * @author GianlucaBernert, ulusserhat Wird von allen Mappern benutzt um die
 *         Verbindung zur Datenbank zu erstellen
 **/
public class DBConnection {

	/**
	 * Sie speichert die einzige Instanz dieser Klasse. Sie ist durch static nur
	 * einmal f�r alle s�mtlichen Instanzen dieser Klasse vorhanden
	 */
	private static Connection con = null;

	/**
	 * Die URL, mit deren Hilfe die Datenbank angesprochen wird
	 * 
	 */
	private static String googleUrl = "";
	private static String localUrl = "jdbc:mysql://localhost/pinners?user=root&password=********"; //useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	/**
	 * Methode zum erzeugen einer Verbindung zur Datenbank
	 */
	public static Connection connection() {
		// Wenn es bisher keine Conncetion zur DB gab, ...
		if (con == null) {
			try {

				Class.forName("com.mysql.cj.jdbc.Driver"); //  JDBC -Treiber für Mysql
				con = DriverManager.getConnection(localUrl);
				/*
				 * die Verbindung zur Datenbank wird in der Variable con gespeichert
				 * 
				 */

				Statement stmt = con.createStatement();
				System.out.println("DB Connection!");
				/*
				 * Wird angezeigt, ob die Verbindung zur Datenbank funktioniert hat
				 * 
				 */

			} catch (Exception e) {
				con = null;
				e.printStackTrace();
			}
		}

		// Zurückgegeben der Verbindung
		return con;
	}

}
//OK