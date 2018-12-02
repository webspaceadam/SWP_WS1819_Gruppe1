package de.hdm.softwarePraktikumGruppe1.server.db;

import java.sql.Connection;
import java.sql.DriverManager;

import com.gooogle.appengine.api.utils.SystemProperty; //google play services SDK installieren

/**
 * @author gianluca, ulusserhat
 * 
 *         Wird von allen Mappern benutzt um die Verbindung zur Datenbank zu
 *         erstellen. Diese Klasse wird nur einmal instanziiert Dies ist das
 *         Singelton-Prinzip.
 */
public class DBConnection {

	private static Connection con = null;

	/**
	 * Mithilfe der Url wird die Datenbank angesprochen.
	 * 
	 */
	private static String googleUrl = "";
	private static String localUrl = "";

	/**
	 * Methode zum erzeugen einer Verbindung zur Datenbank. Durch
	 * DBConnection.connection kann diese statische Methode aufgerufen werden.
	 * stellt die Singelton-Eigenschaft sicher, weil sie dafür sorgt, dass nur eine
	 * einzige Instanz von DbVerbindung existiert.
	 */
	public static Connection connection() {

		if (con == null) {

			String url = null;

			try {
				if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {// Library noch
																										// hinzufügen(wegen
																										// SystemProperty)
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

		/**
		 * Zurückgegeben der Verbindung
		 */
		return con;

	}
}
