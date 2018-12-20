package de.hdm.softwarePraktikumGruppe1.client.service.Login;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.softwarePraktikumGruppe1.shared.LoginInfo;

@RemoteServiceRelativePath("loginService")
public interface LoginService extends RemoteService{
	
	public LoginInfo login(String requestURL);

}
