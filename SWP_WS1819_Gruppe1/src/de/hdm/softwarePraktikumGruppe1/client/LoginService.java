package de.hdm.softwarePraktikumGruppe1.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.softwarePraktikumGruppe1.shared.LoginInfo;

/**
 * <code>LoginService</code> will be implemented on the server-side
 * @see LoginServicImpl
 * 
 * @RemoteServiceRelativePath associates the service with a default path relative to the module base URL
 * 
 * @author JakobBenkoe
 **/
@RemoteServiceRelativePath("LoginImpl")
public interface LoginService extends RemoteService {
  public LoginInfo login(String requestUri);
}
