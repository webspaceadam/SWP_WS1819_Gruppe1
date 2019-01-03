/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared.bo;

import java.util.Vector;

/**
 * @author GianlucaBernert
 * Klasse eines Pinnwand Objekts das BusinessObject als SuperKlasse besitzt
 */
public class Pinnwand extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	private int pinnwandId;
	private int ownerId;
	
	/**
	 * Der Konstruktor nimmt einen Parameter des Typs User entgegen, um ihn so als <em>owner</em>
	 * der Klasse zu setzen. 
	 * 
	 * @param owner
	 */
	public Pinnwand(int userId) {
		this.ownerId = userId;
	}
	
	/*
	 * Leerer Konstruktor. Die Zuweisung der Attribute wird über die Setter-Methoden realisiert.
	 */
	public Pinnwand() {
		
	}

	private Vector<Beitrag> alleBeitraege;
	private Vector<Abonnement> alleAbonnements;

	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public Vector<Beitrag> getAlleBeitraege() {
		return alleBeitraege;
	}
	public void setAlleBeitraege(Vector<Beitrag> alleBeitraege) {
		this.alleBeitraege = alleBeitraege;
	}
	public Vector<Abonnement> getAlleAbonnements() {
		return alleAbonnements;
	}
	public void setAlleAbonnements(Vector<Abonnement> alleAbonnements) {
		this.alleAbonnements = alleAbonnements;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getPinnwandId() {
		return pinnwandId;
	}

	public void setPinnwandId(int pinnwandId) {
		this.pinnwandId = pinnwandId;
	}
	
	
	
	

}
