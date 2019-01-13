/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RpcRequestBuilder;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 * @author GianlucaBernert
 *Klasse die ServiceDefTarget und LoginServiceAsync Implementiert
 */
public class LoginServiceProxy implements ServiceDefTarget, LoginServiceAsync{

	@Override
	public void login(String requestURL, AsyncCallback<LoginInfo> async) {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public String getSerializationPolicyName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServiceEntryPoint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRpcRequestBuilder(RpcRequestBuilder builder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setServiceEntryPoint(String address) {
		// TODO Auto-generated method stub
		
	}

}
