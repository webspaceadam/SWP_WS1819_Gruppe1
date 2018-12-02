package de.hdm.softwarePraktikumGruppe1.server.db;

import java.sql.*;
import com.google.appengine.api.rdbms.AppEngineDriver; //google play service SDK installieren

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
	private static String localUrl = "";

	/**
	 * Methode zum erzeugen einer Verbindung zur Datenbank
	 */
	public Connection connection() {
		if (con == null) {

			String url = null;

			try {
				if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {// library muss
																										// noch
																										// hinzugefügt
																										// werden
					// Load the class that provides the new
					// "jdbc:google:mysql://" prefix.
					Class.forName("com.mysql.jdbc.GoogleDriver");
					url = googleUrl;
				} else {
					// Local MySQL instance to use during development.
					Class.forName("com.mysql.jdbc.Driver");
					url = localUrl;
				}

				/**
				 * Dann erst kann uns der DriverManager eine Verbindung mit den oben in der
				 * Variable url angegebenen Verbindungsinformationen aufbauen.
				 *
				 * Diese Verbindung wird dann in der statischen Variable con abgespeichert und
				 * fortan verwendet.
				 */

				con = DriverManager.getConnection(url);
			} catch (Exception e) {
				con = null;
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}

		}

		// Zurückgegeben der Verbindung
		return con;

	}

}
