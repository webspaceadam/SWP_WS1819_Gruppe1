/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.softwarePraktikumGruppe1.client.service.Login.LoginService;
import de.hdm.softwarePraktikumGruppe1.shared.LoginInfo;

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
