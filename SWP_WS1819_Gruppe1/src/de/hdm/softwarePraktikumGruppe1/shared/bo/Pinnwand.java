/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared.bo;

import java.util.Vector;

/**
 * @author GianlucaBernert
 * Klasse eines Pinnwand Objekts das BusinessObject als SuperKlasse besitzt
 */
public class Pinnwand extends BusinessObject{
	
	private static final long serialVersionUID = 1L;
	private User owner;
	private Vector<Beitrag> alleBeitraege;
	private Vector<Abonnement> alleAbonnements;
	
	
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
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
	
	
	
	

}
