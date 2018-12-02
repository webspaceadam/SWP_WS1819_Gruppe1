package de.hdm.softwarePraktikumGruppe1.server.db;

import java.sql.*;
//import com.google.appengine.api.rdbms.AppEngineDriver; //google play service SDK installieren

/**
 * @author GianlucaBernert, ulusserhat Wird von allen Mappern benutzt um die
 *         Verbindung zur Datenbank zu erstellen
 */
public class DBConnection {

	/**
	 * Sie speichert die einzige Instanz dieser Klasse. Sie ist durch static nur
	 * einmal für alle sämtlichen Instanzen dieser Klasse vorhanden
	 */
	private static Connection con;

	/**
	 * Die URL, mit deren Hilfe die Datenbank angesprochen wird
	 * 
	 */
	private static String googleUrl = "";
	

	/**
	 * Methode zum erzeugen einer Verbindung zur Datenbank
	 */
	public static Connection connection() {
		// Wenn es bisher keine Conncetion zur DB gab, ... 
		if ( con == null ) {
			try {
				// Ersteinmal muss der passende DB-Treiber geladen werden
				DriverManager.registerDriver(new AppEngineDriver());

				/*
				 * Dann erst kann uns der DriverManager eine Verbindung mit den oben
				 * in der Variable url angegebenen Verbindungsinformationen aufbauen.
				 * 
				 * Diese Verbindung wird dann in der statischen Variable con 
				 * abgespeichert und fortan verwendet.
				 */
				con = DriverManager.getConnection(googleUrl);
			} 
			catch (SQLException e1) {
				con = null;
				e1.printStackTrace();
			}
		}
		
		// Zurückgegeben der Verbindung
		return con;
	}

}
