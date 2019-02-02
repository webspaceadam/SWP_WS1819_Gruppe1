package de.hdm.softwarePraktikumGruppe1.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.softwarePraktikumGruppe1.shared.LoginInfo;

/*
 * The @code LoginServiceAsync Iterface will be implemented
 * on the client client-side
 * @see ReportGenerator
 * 
 * @Author JakobBenkoe
 */
public interface LoginServiceAsync {
	  public void login(String requestUri, AsyncCallback<LoginInfo> async);
	}
