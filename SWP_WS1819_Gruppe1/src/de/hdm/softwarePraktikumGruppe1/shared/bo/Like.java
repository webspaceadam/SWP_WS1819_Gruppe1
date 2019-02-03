/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared.bo;

import java.sql.Timestamp;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author GianlucaBernert
 * @author Yesin Soufi
 * Klasse eines Like Objekts das BusinessObject als Superklasse besitzt
 */
public class Like implements IsSerializable{
	
	private static final long serialVersionUID = 1L;
	private int likeId;
	private int beitragId;
	private int ownerId;
	private Timestamp creationTimeStamp;
	
	/*
	 * Leerer Konstruktor. Die Zuweisung der Attribute wird über die Setter-Methoden realisiert.
	 */
	public Like(){
		
	}
	
	/**
	 * Methode die die BeitragId zurückgibt
	 * @return beitragId
	 */
	
	public int getBeitragId() {
		return beitragId;
	}
	
	/**
	 * Methode die die BeitragId setzt
	 * @param beitragId
	 */
	
	public void setBeitragId(int beitragId) {
		this.beitragId = beitragId;
	}
	
	/**
	 * Methode die die OwnerId zurückgibt
	 * @return ownerId
	 */

	public int getOwnerId() {
		return ownerId;
		
	/**
	 * Methode die die OwnerId setzt
	 * @param userId
	 */
	
	}
	public void setOwnerId(int userId) {
		this.ownerId = userId;
		

	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/**
	 * Mehtode die die LikeId zurückgibt
	 * @return likeId
	 */

	public int getLikeId() {
		return likeId;
	}
	
	/**
	 * Methode die die LikeId setzt
	 * @param likeId
	 */

	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}
	
	/**
	 * Methode um eine textuelle Dastellung der jeweiligen Instanz zu erzeugen
	 */
	public String toString() {
		return super.toString() + " LikeID #L" + this.getLikeId() + " Referenzbeitrag " + this.getBeitragId();
	}
	
	/**
	 * Methode die das Erstellungsdatum zurückgibt
	 * @return creationTimeStamp
	 */
	
	public Timestamp getCreationTimeStamp() {
		return creationTimeStamp;
	}
	
	/**
	 * Methode die das Erstellungsdatum setzt
	 * @param creationTimeStamp
	 */

	public void setCreationTimeStamp(Timestamp creationTimeStamp) {
		this.creationTimeStamp = creationTimeStamp;
	}

	
	

}
