/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared.bo;

/**
 * @author GianlucaBernert
 * Klasse eines Pinnwand Objekts das BusinessObject als SuperKlasse besitzt
 */
public class Pinnwand extends BusinessObject{
	
	private static final long serialVersionUID = 1L;
	private User owner;
	private int abonnementId;
	private int ownerId;
	private int User_UserID;
	
	public int getUser_UserID() {
		return User_UserID;
	}

	public void setUser_UserID(int user_UserID) {
		User_UserID = user_UserID;
	}

	/**
	 * Methode die den Besitzer der Pinnwand zurück gibt
	 */
	public User getOwner() {
		return owner;
	}
	
	/**
	 * Methode die den Besitzer einer Pinnwand setzt
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	/**
	 * Methode die die ID der Abonnements zurück gibt
	 */
	public int getAbonnementId() {
		return abonnementId;
	}
	
	/**
	 * Methode die die ID der Abonnements setzt
	 */
	public void setAbonnementId(int abonnementId) {
		this.abonnementId = abonnementId;
	}
	
	/**
	 * Methode die die ID des Besitzers zurück gibt
	 */
	public int getOwnerId() {
		return ownerId;
	}
	
	/**
	 * Methode die die ID des Besitzers setzt
	 */
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * Methode um eine textuelle Dastellung der jeweiligen Instanz zu erzeugen
	 */
	public String toString() {
		return super.toString() + " " + this.owner;
	}

	
	

}
