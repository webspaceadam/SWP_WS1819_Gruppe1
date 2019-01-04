/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared.bo;

import java.io.Serializable;
import java.util.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author GianlucaBernert
 * BusinessObject Klasse als Superklasse für Abonnement, Like, Pinnwand, Textbeitrag und User
 */
public abstract class BusinessObject implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id = 1;
	private Timestamp creationTimeStamp;
	
//	/**
//	 * Konstruktor der Klasse BusinessObject der die ID bei bei jeder Erzeugung einer Instanz dieser Klasse um eins erhöht
//	 */
//	public BusinessObject() {
//		id += 1;
//	}
	
	/**
	 * Methode um die ID des BusinessObjects zurueck zu bekommen
	 */
	public int getId() {//Argument rausgenommen, weil ich nur id des objektes haben moechte.
		return this.id;
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
	public Timestamp getCreationTimeStamp() {
		return creationTimeStamp;
	}
	
	/**
	 * Methode um den Erstellungszeitpunkt des BusinessIbjects zu setzen
	 */
	public void setCreationTimeStamp(Timestamp timestamp) {
		this.creationTimeStamp = new Timestamp(System.currentTimeMillis());
	}
	
	/**
	 * Methode um den Klassennamen + die ID des Objekts zurueck zu geben
	 */
	public String toString() {
		return this.getClass().getName();
	}
	
	/**
	 * Methode um zu prüfen ob ein Objekt ungleich NULL ist und ob es eine Instanz von BusinessObject ist
	 */
	public boolean equals(Object o) {
		if (o != null && o instanceof BusinessObject) {
			BusinessObject bo = (BusinessObject) o;
			try {
				if (bo.getId() == this.id)
					return true;
			}
			catch (IllegalArgumentException e) {
				return false;
			}
		
			}
			return false;
		}
	
	/**
	 * Methode zum erzeugen einer Charakteristischen ganzen Zahl die für das BusinessOject steht
	 */
	public int hashCode() {
		return this.id;
	}

}
