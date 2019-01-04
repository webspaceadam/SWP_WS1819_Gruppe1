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
	private int likeID;
	private int beitragId;
	private int ownerId;
	
	/**
	 * Der Konstruktor nimmt zwei Parameter entgegen. Einen vom Typ Beitrag um so die 
	 * Verbindung zum dazugehörigen Beitrag zu setzen. Und einen weiteren Parameter des Typs
	 * User um so den Besitzer des Likes zu identifizieren. 
	 * @param beitrag
	 * @param owner
	 */
	public Like(int beitrag, int owner) {
		this.beitragId = beitrag;
		this.ownerId = owner;
		
		likeID += 1;
	}
	
	/*
	 * Leerer Konstruktor. Die Zuweisung der Attribute wird über die Setter-Methoden realisiert.
	 */
	public Like(){
		
	}
	
	public int getBeitragId() {
		return beitragId;
	}
	public void setBeitragId(int beitragId) {
		this.beitragId = beitragId;
	}

	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int userId) {
		this.ownerId = userId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getLikeID() {
		return likeID;
	}

	public void setLikeID(int likeID) {
		this.likeID = likeID;
	}
	
	public String toString() {
		return "Like ID: " + this.getLikeID() + " references Beitrag: #" + this.getBeitragId();
	}
	
	

}
