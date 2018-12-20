package de.hdm.softwarePraktikumGruppe1.client.service.Login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;



public class LoginServiceClientImpl implements LoginServiceClientInt{
	
	private LoginServiceAsync service;
	//private MainGUI maingui;
	
	public LoginServiceClientImpl(String url) {
		System.out.println(url);
		this.service = GWT.create(LoginService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(url);
		
		//this.maingui = new MainGUI(this);
	}
	
	public void login(String requestURL) {
		this.service.login(requestURL, new DefaultCallback());
	}

	private class DefaultCallback implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof String)	{
				String url = (String) result;
				//maingui.updateLabel(url);
				
			}
			
		}
	
	}
}

