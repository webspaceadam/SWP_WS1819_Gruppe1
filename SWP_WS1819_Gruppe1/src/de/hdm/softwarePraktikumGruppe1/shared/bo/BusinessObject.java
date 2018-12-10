/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared.bo;

import java.io.Serializable;
import java.util.*;

/**
 * @author GianlucaBernert
 * BusinessObject Klasse als Superklasse für Abonnement, Like, Pinnwand, Textbeitrag und User
 */
public abstract class BusinessObject implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id = 0;
	private Date creationTimeStamp;
	
	/**
	 * Methode um die ID des BusinessObjects zurueck zu bekommen
	 */
	public int getId(BusinessObject bo) {
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
	 * Methode um den Klassennamen + die ID des Objekts zurueck zu geben
	 */
	public String toString() {
		return this.getClass().getName() + " BO ID #" + this.id;
	}
	
	/**
	 * Methode um zu prüfen ob ein Objekt ungleich NULL ist und ob es eine Instanz von BusinessObject ist
	 */
	public boolean equals(Object o) {
		if (o != null && o instanceof BusinessObject) {
			BusinessObject bo = (BusinessObject) o;
			try {
				if (bo.getId(bo) == this.id)
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
