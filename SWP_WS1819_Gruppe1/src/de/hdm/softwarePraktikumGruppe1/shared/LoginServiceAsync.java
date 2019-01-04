/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author GianlucaBernert
 * Interface das von LoginServiceProxy Implementiert wird
 */
public interface LoginServiceAsync {

	public void login(String requestURL, AsyncCallback<LoginInfo> async);
	
}
