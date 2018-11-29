/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.server.db;

import java.sql.*;

/**
 * @author GianlucaBernert
 * Wird von allen Mappern benutzt um die Verbindung zur Datenbank zu erstellen
 */
public class DbVerbindung {
	
	private Connection con;
	private String URL;
	
	/**
	 * Methode zum erzeugen einer Verbindung zur Datenbank
	 */
	public Connection connection() {
		return con;
		
	}
	

}
