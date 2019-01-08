/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared.bo;

import java.io.Serializable;
import java.sql.Timestamp;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author GianlucaBernert, AdamGniady
 * Klasse eines Abonnement Objekts das BusinessObject als Superklasse besitzt
 */
public class Abonnement implements IsSerializable{
	
	private static final long serialVersionUID = 1L;
	private int abonnementId;
	private int ownerId;
	private int pinnwandId;
	private Timestamp creationTimeStamp;

	
	/*
	 * Leerer Konstruktor. Die Zuweisung der Attribute wird über die Setter-Methoden realisiert.
	 */
	public Abonnement() {
	}
	
	/*
	 * Methode, die die AbonnementId zurueck gibt
	 */
	public int getAbonnementId() {
		return abonnementId;
	}

	/*
	 * Methode, die das Setzen einer vorläufigen Id ermöglicht
	 */
	public void setAbonnementId(int abonnementId) {
		this.abonnementId = abonnementId;
	}
	
	/**
	 * Methode die den Besitzer des Abonnements zurueck gibt
	 */
	public int getOwnerId() {
		return ownerId;
	}
	
	/**
	 * Methode doe den Besitzer des Abonnements setzt
	 */
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	
	/**
	 * Methode die die abonnierte Pinnwand zurueck gibt
	 */
	public int getPinnwandId() {
		return pinnwandId;
	}
	
	/**
	 * Methode die die zu abonierende Pinnwand setzt
	 */
	public void setPinnwandId(int pinnwandId) {
		this.pinnwandId = pinnwandId;
	}
	
	public Timestamp getCreationTimeStamp() {
		return creationTimeStamp;
	}

	public void setCreationTimeStamp(Timestamp creationTimeStamp) {
		this.creationTimeStamp = creationTimeStamp;
	}
	
	/**
	 * Methode um eine textuelle Dastellung der jeweiligen Instanz zu erzeugen
	 */
	public String toString() {
		return super.toString() + " AbonnementID #A" + this.getAbonnementId();
	}
	
}
