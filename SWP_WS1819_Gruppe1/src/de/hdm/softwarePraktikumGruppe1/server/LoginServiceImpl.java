/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.server;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.softwarePraktikumGruppe1.client.LoginService;
import de.hdm.softwarePraktikumGruppe1.shared.LoginInfo;


/**
 * @author GianlucaBernert
 * @author Yesin Soufi
 * @author Jakob Benkoe
 * Klasse die LoginService Implementiert und RemoteServiceServlet als Superklasse besitzt
 */
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Methode um den Login zu verwalten
	 * @param requestUri
	 * @return loginInfo
	 */

public LoginInfo login(String requestUri) {
UserService userService = UserServiceFactory.getUserService();
User user = userService.getCurrentUser();
LoginInfo loginInfo = new LoginInfo();

if (user != null) {
  loginInfo.setLoggedIn(true);
  loginInfo.setEmailAddress(user.getEmail());
  loginInfo.setNickname(user.getNickname());
  loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));
} else {
  loginInfo.setLoggedIn(false);
  loginInfo.setLoginUrl(userService.createLoginURL(requestUri));
}
return loginInfo;
}

}
