/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared.bo;

import java.sql.Timestamp;

/**
 * @author GianlucaBernert
 * @author SebastianHermann
 * Klasse eines Kommentar Objekts das Textbeitrag als Superklasse besitzt
 */
public class Kommentar {
	
	private static final long serialVersionUID = 1L;
	private int kommentarId;
	private int ownerId;
	private int beitragId;
	private String inhalt;
	private Timestamp creationTimeStamp;
	
	
	/*
	 * Leerer Konstruktor. Die Zuweisung der Attribute wird über die Setter-Methoden realisiert.
	 */
	public Kommentar() {
		
	}
	
	/**
	 * Methode die den Autor eines Kommentars zurück gibt
	 */
	public int getOwnerId() {
		return ownerId;
	}
	
	/**
	 * MEthode die den Autor eines Kommentars setzt
	 */
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	
	/**
	 * Methode die den kommentierten Beitrag zurück gitb 
	 */
	public int getBeitragId() {
		return beitragId;
	}
	
	/**
	 * Methode die den kommentierten Beitrag sertzt
	 */
	public void setBeitragId(int beitragId) {
		this.beitragId = beitragId;
	}
	
	/**
	 * Methode die den Text eines Kommentars zurück gibt
	 */
	public String getInhalt() {
		return inhalt;
	}
	
	/**
	 * Methode die den Text eines Kommentars setzt
	 */
	public void setInhalt(String inhalt) {
		this.inhalt = inhalt;
	}
	
	/**
	 * Methode die das Ibjekt Kommentar als String zurück gibt
	 */
	public String toString() {
		String infoAboutKommentar = "Kommentar ID '" + this.getKommentarId() + "/ Inhalt: " + this.getInhalt() + "/ ID des Verfassers #" + this.getOwnerId();
		return infoAboutKommentar;
	}

	/*
	 * Methode, die die Id eines Kommentars zurueck gibt
	 */
	public int getKommentarId() {
		return kommentarId;
	}

	/*
	 * Methode, die das Setzen eines Kommentars ermöglicht
	 */
	public void setKommentarId(int kommentarId) {
		this.kommentarId = kommentarId;
	}
	
	public Timestamp getCreationTimeStamp() {
		return creationTimeStamp;
	}

	public void setCreationTimeStamp(Timestamp creationTimeStamp) {
		this.creationTimeStamp = creationTimeStamp;
	}


}
