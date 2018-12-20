package de.hdm.softwarePraktikumGruppe1.client.service.ReportGenerator;

import java.util.ArrayList;
import java.util.Date;

import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Kommentar;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Like;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Pinnwand;
import de.hdm.softwarePraktikumGruppe1.shared.bo.User;

public class ReportGeneratorServiceClientImpl implements ReportGeneratorServiceInt{
	
	/**
	 * Methode die die Beitraege eines Users zurück gibt
	 */
	public ArrayList<Beitrag> getBeitraegeFromUser(User u, Date d){
		return null;
	}
	
	/**
	 * Methode die die Likes eines Users zurück gibt
	 */
	public ArrayList<Like> getLikesFromUser(User u, Date d){
		return null;
	}
	
	/**
	 * Methode die das Abonnement eines Users zurück gibt
	 */
	public ArrayList<User> getAbonnementFromPinnwand(Pinnwand p, Date d){
		return null;
	}
	
	/**
	 * Methode die den Kommentar eines Beitrags zurück gibt
	 */
	public ArrayList<Kommentar> getKommentarFromBeitrag(Beitrag b, Date d){
		return null;
	}
	
	/**
	 * Methode die die Likes eines Beitrags zurück gibt
	 */
	public ArrayList<Like> getLikesFromBeitrag(Beitrag b, Date d){
		return null;
	}


}
