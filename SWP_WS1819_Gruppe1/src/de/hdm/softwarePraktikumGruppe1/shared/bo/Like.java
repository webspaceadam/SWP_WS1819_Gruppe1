/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared.bo;

/**
 * @author GianlucaBernert
 * Klasse eines Like Objekts das BusinessObject als Superklasse besitzt
 */
public class Like extends BusinessObject{
	
	private static final long serialVersionUID = 1L;
	private Beitrag beitrag;
	private User owner;
	
	/**
	 * Der Konstruktor nimmt zwei Parameter entgegen. Einen vom Typ Beitrag um so die 
	 * Verbindung zum dazugehörigen Beitrag zu setzen. Und einen weiteren Parameter des Typs
	 * User um so den Besitzer des Likes zu identifizieren. 
	 * @param beitrag
	 * @param owner
	 */
	public Like(Beitrag beitrag, User owner) {
		this.beitrag = beitrag;
		this.owner = owner;
	}
	
	public Beitrag getBeitrag() {
		return beitrag;
	}
	public void setBeitrag(Beitrag beitrag) {
		this.beitrag = beitrag;
	}

	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}
