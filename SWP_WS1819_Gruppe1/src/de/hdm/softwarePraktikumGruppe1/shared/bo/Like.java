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
	private int beitragId;
	private int ownerId;
	private User owner;
	
	/**
	 * Methode die die ID des gelikten Beitrags zurück gibt
	 */
	public int getBeitragId() {
		return beitragId;
	}
	
	/**
	 * ID die die ID des gelikten Beitrags setzt
	 */
	public void setBeitragId(int beitragId) {
		this.beitragId = beitragId;
	}
	
	/**
	 *Methode die die ID des Users zurück gibt dem der Beitrag gefaellt
	 */
	public int getOwnerId() {
		return ownerId;
	}
	
	/**
	 * Methode die die ID des Users setzt dem der Beitrag gefaellt
	 */
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	
	/**
	 * Methode die den User zurück gibt dem der Beitrag gefaellt
	 */
	public User getOwner() {
		return owner;
	}
	
	/**
	 * Mehtode die den Users setzt dem der Beitrag gefaellt
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	

}
