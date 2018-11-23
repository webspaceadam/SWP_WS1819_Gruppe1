/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Kommentar;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Like;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Pinnwand;
import de.hdm.softwarePraktikumGruppe1.shared.bo.User;

/**
 * @author gianluca
 * Klasse die ReportGeneratorAsync und ServiceDefTarget implementiert
 */
public class ReportGeneratorServiceProxy implements ReportGeneratorAsync, ServiceDefTarget{

	@Override
	public void getBeitragFromUser(User u, Date d, AsyncCallback<ArrayList<Beitrag>> a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getLikesFromUser(User u, Date d, AsyncCallback<ArrayList<Like>> a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getAbonnementFromPinnwand(Pinnwand p, Date d, AsyncCallback<ArrayList<User>> a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getKommentareFromBeitrag(Beitrag b, Date d, AsyncCallback<ArrayList<Kommentar>> a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getLikesFromBeitrag(Beitrag b, Date d, AsyncCallback<ArrayList<Like>> a) {
		// TODO Auto-generated method stub
		
	}

}
