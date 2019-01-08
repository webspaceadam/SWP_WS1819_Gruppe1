/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared.bo;

import java.sql.Timestamp;

/**
 * @author GianlucaBernert
 * @autor SebastianHermann
 * Klasse eines Pinnwand Objekts das BusinessObject als SuperKlasse besitzt
 */
public class Pinnwand extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	private int pinnwandId;
	private int ownerId;
	private Timestamp creationTimeStamp;

	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getPinnwandId() {
		return pinnwandId;
	}

	public void setPinnwandId(int pinnwandId) {
		this.pinnwandId = pinnwandId;
	}
	
	public Timestamp getCreationTimeStamp() {
		return creationTimeStamp;
	}
	public void setCreationTimeStamp(Timestamp creationTimeStamp) {
		this.creationTimeStamp = creationTimeStamp;
	}
	
	

}
