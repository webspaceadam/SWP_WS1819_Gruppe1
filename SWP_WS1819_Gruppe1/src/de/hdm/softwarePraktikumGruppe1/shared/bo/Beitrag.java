/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared.bo;

import java.util.*;

/**
 * @author SebastianHermann, GianlucaBernert, AdamGniady
 * Klasse eines Beitrag Objekts das Textbeitrag als Superklasse besitzt
 */
public class Beitrag extends Textbeitrag{
	
	private static final long serialVersionUID = 1L;
	
	// Eigenschaften der Klasse
	private int beitragId;
	private int ownerId;
	private int pinnwandId;
	private String inhalt;
	
	
	// Applikationslogikseitig erstellte Eigenschaften der Klasse
	private Vector<Kommentar> kommentare;
	private Vector<Like> likes;
	
	private int likeAmount;
	private int commentAmount;

	/**
	 * Der Konstruktor nimmt drei Parameter entgegen. Einen vom Typ User um so die 
	 * Verbindung zum dazugehörigen User zu setzen. Einen Parameter des Typs Pinnwand um so die dazugehörige
	 * Pinnwand zu setzen. Als letzten Parameter nimmt der Konstruktor einen String entgegen der den Inhalt
	 * des Beitrags darstellt. 
	 * 
	 * @param owner
	 * @param pinnwand
	 * @param inhalt
	 */
	public Beitrag(int ownerId, int pinnwand, String inhalt) {
		this.ownerId = ownerId;
		this.pinnwandId = pinnwand;
		this.inhalt = inhalt;
	}
	
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
	public Vector<Kommentar> getKommentare() {
		return kommentare;
	}
	public void setKommentare(Vector<Kommentar> kommentare) {
		this.kommentare = kommentare;
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
		return super.toString() + " User ID #" + this.getOwnerId();
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
}


