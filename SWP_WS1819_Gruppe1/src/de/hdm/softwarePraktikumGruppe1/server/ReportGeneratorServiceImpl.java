/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.server;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.softwarePraktikumGruppe1.shared.ReportGeneratorService;
import de.hdm.softwarePraktikumGruppe1.shared.report.BeitragReport;
import de.hdm.softwarePraktikumGruppe1.shared.report.UserReport;



/**
 * @author GianlucaBernert
 * Klasse die RemoveServerServlet als Superklasse besitzt und das Interface ReportGenerator implementiert
 */
public class ReportGeneratorServiceImpl extends RemoteServiceServlet implements ReportGeneratorService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void init() throws IllegalArgumentException {

	  }

	
	//Hier wird der UserReport erstellt
	@Override
	public UserReport createUserReport() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	//Hier wird der BeitragReport erstellt
	@Override
	public BeitragReport createBeitragReport() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

}
