/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared.bo;

import java.util.*;

/**
 * @author Gianluca
 * BusinessObject Klasse als Superklasse für Abonnement, Like, Pinnwand, Textbeitrag und User
 */
public class BusinessObject {
	
	private long serialVersionUID;
	private int id;
	private Date creationTimeStamp;
	
	/**
	 * Methode um die ID des BusinessObjects zurueck zu bekommen
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Methode um die ID des BusinessObjects zu setzen
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Methode um den Erstellungszeitpunkt des BusinessObject zurueck zu bekommen
	 */
	public Date getCreationTimeStamp() {
		return creationTimeStamp;
	}
	
	/**
	 * Methode um den Erstellungszeitpunkt des BusinessIbjects zu setzen
	 */
	public void setCreationTimeStamp(Date creationTimeStamp) {
		this.creationTimeStamp = creationTimeStamp;
	}
	
	/**
	 * Methode um einen String zu erzeugen
	 */
	public String toString() {
		return null;
		
	}

}
