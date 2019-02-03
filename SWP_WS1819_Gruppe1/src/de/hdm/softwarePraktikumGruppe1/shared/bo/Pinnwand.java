/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared.bo;

import java.sql.Timestamp;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author GianlucaBernert
 * @autor SebastianHermann
 * Klasse eines Pinnwand Objekts das BusinessObject als SuperKlasse besitzt
 */
public class Pinnwand implements IsSerializable{
	
	private static final long serialVersionUID = 1L;
	private int pinnwandId;
	private int ownerId;
	private Timestamp creationTimeStamp;
	
	/**
	 * Methode die die OwnerId zurückgibt
	 * @return ownerId
	 */
	
	public int getOwnerId() {
		return ownerId;
	}
	
	/**
	 * Methode die die OwnerId setzt
	 * @param ownerId
	 */
	
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/**
	 * Methode die die PinnwandId zurückgibt
	 * @return pinnwandId
	 */

	public int getPinnwandId() {
		return pinnwandId;
	}
	
	/**
	 * Methode die die PinnwandId setzt
	 * @param pinnwandId
	 */

	public void setPinnwandId(int pinnwandId) {
		this.pinnwandId = pinnwandId;
	}
	
	/**
	 * Methode die das Erstellungsdatum zurückgibt
	 * @return creationTimeStamp
	 */
	
	public Timestamp getCreationTimeStamp() {
		return creationTimeStamp;
		
	/**
	 * Methode die das Erstellungsdatum setzt
	 * @param creationTimeStamp
	 */
		
	}
	public void setCreationTimeStamp(Timestamp creationTimeStamp) {
		this.creationTimeStamp = creationTimeStamp;
	}
	
	/**
	 * Methode um eine textuelle Dastellung der jeweiligen Instanz zu erzeugen
	 */
	public String toString() {
		return super.toString() + " PinnwandID #P" + this.getPinnwandId();
	}

}
