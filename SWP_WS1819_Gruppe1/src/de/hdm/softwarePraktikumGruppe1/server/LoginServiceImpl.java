/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.softwarePraktikumGruppe1.shared.LoginInfo;
import de.hdm.softwarePraktikumGruppe1.shared.LoginService;

/**
 * @author GianlucaBernert
 * Klasse die LoginService Implementiert und RemoteServiceServlet als Superklasse besitzt
 */
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService{

	/**
	 * 
	 */
	public LoginInfo login(String requestURL) {
		return null;
	}
	

}
