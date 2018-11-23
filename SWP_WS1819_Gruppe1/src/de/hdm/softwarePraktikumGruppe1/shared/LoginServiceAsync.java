/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author gianluca
 * Interface das von LoginServiceProxy Implementiert wird
 */
public interface LoginServiceAsync {

	public LoginInfo login(String requestURL, AsyncCallback<LoginInfo> async);
	
}
