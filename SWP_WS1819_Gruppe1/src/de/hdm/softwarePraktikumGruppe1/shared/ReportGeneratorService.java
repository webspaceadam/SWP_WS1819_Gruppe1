/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared;

import com.google.gwt.user.client.rpc.RemoteService;


/**
 * @author GianlucaBernert  & JakobBenkoe
 * Interface das RemoteService als Superklasse hat und von ReportGeneratorImpl Implementiert wird
 */
public interface ReportGeneratorService extends RemoteService{
	
	void init();

}
