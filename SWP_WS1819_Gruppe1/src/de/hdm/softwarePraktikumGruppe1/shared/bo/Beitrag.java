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
	private User owner;
	private Pinnwand pinnwand;
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
	public Beitrag(User owner, Pinnwand pinnwand, String inhalt) {
		this.owner = owner;
		this.pinnwand = pinnwand;
		this.inhalt = inhalt;
	}
	
	/*
	 * Leerer Konstruktor. Die Zuweisung der Attribute wird über die Setter-Methoden realisiert.
	 */
	public Beitrag() {
		
	}

	//Getter- und Setter-Methoden
	public User getOwner() {
		return owner;
	}
	public void setOwner(User user) {
		this.owner = user;
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
	
	public Pinnwand getPinnwand() {
		return pinnwand;
	}

	public void setPinnwand(Pinnwand pinnwand) {
		this.pinnwand = pinnwand;
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
	public void setText(String inhalt) {
		this.inhalt = inhalt;
	}

	public void setBeitragID(int beitragID) {
		beitragID = beitragID;
	}
	
	/**
	 * Methode um eine textuelle Dastellung der jeweiligen Instanz zu erzeugen
	 */
	public String toString() {
		return super.toString() + " User ID #" + this.owner.getId();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}


