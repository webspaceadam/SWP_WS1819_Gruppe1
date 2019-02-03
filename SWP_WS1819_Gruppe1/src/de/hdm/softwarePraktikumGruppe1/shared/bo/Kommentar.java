/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared.bo;

import java.sql.Timestamp;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author GianlucaBernert
 * @author SebastianHermann
 * Klasse eines Kommentar Objekts das Textbeitrag als Superklasse besitzt
 */
public class Kommentar implements IsSerializable{
	
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
	 * @return ownerId
	 */
	public int getOwnerId() {
		return ownerId;
	}
	
	/**
	 * Methode die den Autor eines Kommentars setzt
	 *@param ownerId
	 */
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	
	/**
	 * Methode die den kommentierten Beitrag zurück gitb 
	 * @return beitragId
	 */
	public int getBeitragId() {
		return beitragId;
	}
	
	/**
	 * Methode die den kommentierten Beitrag sertzt
	 * @param beitragId
	 */
	public void setBeitragId(int beitragId) {
		this.beitragId = beitragId;
	}
	
	/**
	 * Methode die den Text eines Kommentars zurück gibt
	 * @return inhalt
	 */
	public String getInhalt() {
		return inhalt;
	}
	
	/**
	 * Methode die den Text eines Kommentars setzt
	 * @param inhalt
	 */
	public void setInhalt(String inhalt) {
		this.inhalt = inhalt;
	}
	
	/**
	 * Methode die das Ibjekt Kommentar als String zurück gibt
	 */
	public String toString() {
		return "KommentarID #K" + this.getKommentarId() + " von User mit der ID #U" + this.getOwnerId();
	}

	/**
	 * Methode, die die Id eines Kommentars zurueck gibt
	 * @return kommentarId
	 */
	public int getKommentarId() {
		return kommentarId;
	}

	/**
	 * Methode, die das Setzen eines Kommentars ermöglicht
	 * @param kommentarId
	 */
	public void setKommentarId(int kommentarId) {
		this.kommentarId = kommentarId;
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
