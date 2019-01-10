/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared.bo;

import java.sql.Timestamp;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author SebastianHermann, GianlucaBernert, AdamGniady
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
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int userId) {
		this.ownerId = userId;
	}

	public int getLikeAmount() {
		return likeAmount;
	}
	
	public void setLikeAmount(int likeAmount) {
		this.likeAmount = likeAmount;
	}
	
	public int getCommentAmount() {
		return commentAmount;
	}
	public void setCommentAmount(int commentAmount) {
		this.commentAmount = commentAmount;
	}
	
	public int getPinnwandId() {
		return pinnwandId;
	}

	public void setPinnwandId(int pinnwandId) {
		this.pinnwandId = pinnwandId;
	}
	
	public String getInhalt() {
		return inhalt;
	}
	public void setInhalt(String inhalt) {
		this.inhalt = inhalt;
	}

	public void setBeitragID(int beitragId) {
		this.setBeitragId(beitragId);
	}
	
	/**
	 * Methode um eine textuelle Dastellung der jeweiligen Instanz zu erzeugen
	 */
	public String toString() {
		return super.toString() + " Beitrag ID #" + this.getBeitragId();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getBeitragId() {
		return beitragId;
	}

	public void setBeitragId(int beitragId) {
		this.beitragId = beitragId;
	}
	
	public Timestamp getCreationTimeStamp() {
		return creationTimeStamp;
	}

	public void setCreationTimeStamp(Timestamp creationTimeStamp) {
		this.creationTimeStamp = creationTimeStamp;
	}
}


