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
	private int ownerId;
	private User owner;
	
	public Beitrag getBeitrag() {
		return beitrag;
	}
	public void setBeitrag(Beitrag beitrag) {
		this.beitrag = beitrag;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
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
