/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared.bo;

import java.sql.Timestamp;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author GianlucaBernert
 * @author AdamGniady
 * @author Yesin Soufi
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
	
	/**
	 * Methode, die die AbonnementId zurueck gibt
	 * @return abonnementId
	 */
	public int getAbonnementId() {
		return abonnementId;
	}

	/**
	 * Methode, die das Setzen einer vorläufigen Id ermöglicht
	 * @param abonnementId
	 * @return abonnementId
	 */
	public void setAbonnementId(int abonnementId) {
		this.abonnementId = abonnementId;
	}
	
	/**
	 * Methode die den Besitzer des Abonnements zurueck gibt
	 * @return ownerId
	 */
	public int getOwnerId() {
		return ownerId;
	}
	
	/**
	 * Methode doe den Besitzer des Abonnements setzt
	 * @param ownerId
	 * @return ownerId
	 */
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	
	/**
	 * Methode die die abonnierte Pinnwand zurueck gibt
	 * @return pinnwandId
	 */
	public int getPinnwandId() {
		return pinnwandId;
	}
	
	/**
	 * Methode die die zu abonierende Pinnwand setzt
	 * @param pinnwandId
	 * @return pinnwandId
	 */
	public void setPinnwandId(int pinnwandId) {
		this.pinnwandId = pinnwandId;
	}
	
	/**
	 * Methode die das Datum zurückgibt
	 * @return creationTimeStamp
	 */
	
	public Timestamp getCreationTimeStamp() {
		return creationTimeStamp;
	}
	
	/**
	 * Methode die das Datum setzt
	 * @param creationTimeStamp
	 * @return creationTimeStamp
	 */

	public void setCreationTimeStamp(Timestamp creationTimeStamp) {
		this.creationTimeStamp = creationTimeStamp;
	}
	
	/**
	 * Methode um eine textuelle Dastellung der jeweiligen Instanz zu erzeugen
	 * @return super.toString
	 */
	public String toString() {
		return super.toString() + " AbonnementID #A" + this.getAbonnementId();
	}
	
}
