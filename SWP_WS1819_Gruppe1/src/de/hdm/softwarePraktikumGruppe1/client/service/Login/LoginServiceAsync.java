package de.hdm.softwarePraktikumGruppe1.client.service.Login;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.softwarePraktikumGruppe1.shared.LoginInfo;

public interface LoginServiceAsync {
	
	public void login(String requestURL, AsyncCallback callback);

}
