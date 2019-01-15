/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.softwarePraktikumGruppe1.shared.bo.User;
import de.hdm.softwarePraktikumGruppe1.shared.report.BeitragReport;
import de.hdm.softwarePraktikumGruppe1.shared.report.UserReport;



/**
 * @author GianlucaBernert  & JakobBenkoe
 * Interface das RemoteService als Superklasse hat und von ReportGeneratorImpl Implementiert wird
 */

@RemoteServiceRelativePath("reportgenerator")
public interface ReportGeneratorService extends RemoteService{
	
	
	void init() throws IllegalArgumentException;

	
	  /**
	   * Erstellen eines <code>UserReport</code>-Reports. Dieser
	   * Report-Typ stellt Informationen über einen User per Zeitraum dar.
	 * @param userID TODO
	 * @param start TODO
	 * @param end TODO
	   * 
	   * @return das fertige Reportobjekt
	   * @throws IllegalArgumentException
	   * @see UserReport
	   */
	  public abstract UserReport createUserReport(User userID, Date start, Date end) throws IllegalArgumentException;

	  /**
	   * Erstellen eines <code>BeitragReport</code>-Reports.
	   * Dieser Report-Typ stellt Informationen über Beiträge per Zeitraum dar.
	   * 
	   * @return das fertige Reportobjekt
	   * @throws IllegalArgumentException
	   * @see BeitragReport
	   */
	  public abstract BeitragReport createBeitragReport() throws IllegalArgumentException;
	  
	  
	}

