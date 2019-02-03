/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared.bo;

import java.sql.Timestamp;

import com.google.gwt.user.client.rpc.IsSerializable;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author SebastianHermann
 * @author GianlucaBernert
 * @author AdamGniady
 * @author Yesin Soufi
 * Klasse eines Beitrag Objekts das Textbeitrag als Superklasse besitzt
 */
 
public class Beitrag implements IsSerializable{
	
	private static final long serialVersionUID = 1L;
	
	// Eigenschaften der Klasse
	private int beitragId;
	private int ownerId;
	private int pinnwandId;
	private String inhalt;
	private Timestamp creationTimeStamp;
	private int likeAmount;
	private int commentAmount;
	
	
	/*
	 * Leerer Konstruktor. Die Zuweisung der Attribute wird über die Setter-Methoden realisiert.
	 */
	public Beitrag() {
		
	}

	//Getter- und Setter-Methoden
	
	/**
	 * Methode die eine OwnerId zurückgibt
	 * @return ownerId
	 */
	public int getOwnerId() {
		return ownerId;
		
	/**
	 * Methode die eine OwnerId setzt
	 * @param userId
	 * @return userId
	 */
	}
	public void setOwnerId(int userId) {
		this.ownerId = userId;
	}
	
	/**
	 * Methode die die Anzahl der Likes zurückgibt
	 * @return likeAmount
	 */

	public int getLikeAmount() {
		return likeAmount;
	}
	
	/**
	 * Methode die die Anzahl der Likes setzt
	 * @param likeAmount
	 */
	
	public void setLikeAmount(int likeAmount) {
		this.likeAmount = likeAmount;
	}
	
	/**
	 * Methode die die Anzahl der Kommentare zurückgibt
	 * @return commentAmount
	 */
	
	public int getCommentAmount() {
		return commentAmount;
	}
	
	/**
	 * Methode die die Anzahl der Kommentare setzt
	 * @param commentAmount
	 */
	public void setCommentAmount(int commentAmount) {
		this.commentAmount = commentAmount;
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
	 * Methode die den Inhalt zurückgibt
	 * @return inhalt
	 */
	
	public String getInhalt() {
		return inhalt;
	}
	
	/**
	 * Methode die den Inhalt setzt
	 * @param inhalt
	 */
	
	public void setInhalt(String inhalt) {
		this.inhalt = inhalt;
	}
	
	/**
	 * Methode die die BeitragId setzt
	 * @param beitragId
	 */

	public void setBeitragID(int beitragId) {
		this.setBeitragId(beitragId);
	}
	
	/**
	 * Methode um eine textuelle Dastellung der jeweiligen Instanz zu erzeugen
	 * @return super.toString
	 */
	public String toString() {
		return super.toString() + " Beitrag ID #" + this.getBeitragId();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/**
	 * Methode die die BeitragId setzt
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


