/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared;

import java.util.*;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.softwarePraktikumGruppe1.shared.bo.*;

/**
 * @author GianlucaBernert
 *Interface das von ReportGeneratorServiceProxy Implementiert wird
 */
public interface ReportGeneratorAsync {
	
	public void getBeitragFromUser(User u, Date d, AsyncCallback<ArrayList<Beitrag>> a);
	
	public void getLikesFromUser(User u, Date d, AsyncCallback<ArrayList<Like>> a);
	
	public void getAbonnementFromPinnwand(Pinnwand p, Date d, AsyncCallback<ArrayList<User>> a);
	
	public void getKommentareFromBeitrag(Beitrag b, Date d, AsyncCallback<ArrayList<Kommentar>> a);
	
	public void getLikesFromBeitrag(Beitrag b, Date d, AsyncCallback<ArrayList<Like>> a);

	

}
