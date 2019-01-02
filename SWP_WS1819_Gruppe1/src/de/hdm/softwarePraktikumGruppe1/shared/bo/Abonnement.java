/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared.bo;

/**
 * @author GianlucaBernert, AdamGniady
 * Klasse eines Abonnement Objekts das BusinessObject als Superklasse besitzt
 */
public class Abonnement {
	
	private static final long serialVersionUID = 1L;
	private User owner;
	private Pinnwand pinnwand;

	/**
	 * Der Konstruktor nimmt zwei Parameter entgegen. Zum einen den Owner des
	 * Abonnements und zum anderen die zu abonnierende Pinnwand. 
	 * 
	 * @param owner
	 * @param pinnwand
	 */
	public Abonnement(User owner, Pinnwand pinnwand) {
		this.owner = owner;
		this.pinnwand = pinnwand;
	}
	
	/*
	 * Leerer Konstruktor. Die Zuweisung der Attribute wird über die Setter-Methoden realisiert.
	 */
	public Abonnement() {
		
	}
	
	/**
	 * Methode die den Besitzer des Abonnements zurueck gibt
	 */
	public User getOwner() {
		return owner;
	}
	
	/**
	 * Methode doe den Besitzer des Abonnements setzt
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	/**
	 * Methode die die abonnierte Pinnwand zurueck gibt
	 */
	public Pinnwand getPinnwand() {
		return pinnwand;
	}
	
	/**
	 * Methode die die zu abonierende Pinnwand setzt
	 */
	public void setPinnwand(Pinnwand pinnwand) {
		this.pinnwand = pinnwand;
	}
	
	/*
	 * Methode die den PinnwandUserString abbildet
	 */
	public String pinnwandUserString() {
		return null;
	}
}
