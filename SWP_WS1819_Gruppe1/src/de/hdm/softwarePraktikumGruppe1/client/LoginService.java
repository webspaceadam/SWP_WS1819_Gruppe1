package de.hdm.softwarePraktikumGruppe1.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.softwarePraktikumGruppe1.shared.LoginInfo;


@RemoteServiceRelativePath("LoginImpl")
public interface LoginService extends RemoteService {
  public LoginInfo login(String requestUri);
}
