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
	private User owner;
	private Beitrag beitrag;
	private String inhalt;
	
	/**
	 * Der Konstruktor nimmt drei Parameter entgegen. Einen vom Typ User um so die 
	 * Verbindung zum dazugehörigen User zu setzen. Einen Parameter des Typs Beitrag um so den dazugehörigen
	 * Beitrag zu setzen. Als letzten Parameter nimmt der Konstruktor einen String entgegen der den Inhalt
	 * des Kommentars darstellt. 
	 * 
	 * @param owner
	 * @param beitrag
	 * @param inhalt
	 */
	public Kommentar(User owner, Beitrag beitrag, String inhalt) {
		this.owner = owner;
		this.beitrag = beitrag;
		this.inhalt = inhalt;
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
		return inhalt;
	}
	
	/**
	 * Methode die den Text eines Kommentars setzt
	 */
	public void setText(String inhalt) {
		this.inhalt = inhalt;
	}
	
	/**
	 * Methode die das Ibjekt Kommentar als String zurück gibt
	 */
	public String toString() {
		return null;
	}

}
