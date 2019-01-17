package de.hdm.softwarePraktikumGruppe1.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.softwarePraktikumGruppe1.shared.LoginInfo;

public interface LoginServiceAsync {
	  public void login(String requestUri, AsyncCallback<LoginInfo> async);
	}
