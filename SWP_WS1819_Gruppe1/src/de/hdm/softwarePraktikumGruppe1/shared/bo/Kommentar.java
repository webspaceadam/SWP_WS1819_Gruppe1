/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared.bo;

/**
 * @author GianlucaBernert
 * Klasse eines Kommentar Objekts das Textbeitrag als Superklasse besitzt
 */
public class Kommentar extends Textbeitrag{
	
	private static final long serialVersionUID = 1L;
	private int ownerId;
	private int beitragId;
	private User owner;
	private Beitrag beitrag;
	private String text;
	
	/**
	 * Methode die die ID des Autors eines Kommentars zurück gibt
	 */
	public int getOwnerId() {
		return ownerId;
	}
	
	/**
	 * Methode die die ID des Autors eines Kommentars setzt
	 */
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	
	/**
	 * Methode die die ID des kommentierten Beitrags zurück gibt
	 */
	public int getBeitragId() {
		return beitragId;
	}
	
	/**
	 * Methode die die ID des kommentierten Beitrags setzt
	 */
	public void setBeitragId(int beitragId) {
		this.beitragId = beitragId;
	}
	
	/**
	 * Methode die den Autor eines Kommentars zurück gibt
	 */
	public User getOwner() {
		return owner;
	}
	
	/**
	 * MEthode die den Autor eines Kommentars setzt
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	/**
	 * Methode die den kommentierten Beitrag zurück gitb 
	 */
	public Beitrag getBeitrag() {
		return beitrag;
	}
	
	/**
	 * Methode die den kommentierten Beitrag sertzt
	 */
	public void setBeitrag(Beitrag beitrag) {
		this.beitrag = beitrag;
	}
	
	/**
	 * Methode die den Text eines Kommentars zurück gibt
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * Methode die den Text eines Kommentars setzt
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Methode die das Ibjekt Kommentar als String zurück gibt
	 */
	public String toString() {
		return null;
	}

}
