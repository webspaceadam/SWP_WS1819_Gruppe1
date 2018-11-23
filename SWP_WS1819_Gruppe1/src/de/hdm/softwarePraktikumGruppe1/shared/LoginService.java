/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared;

/**
 * @author gianluca
 * Interface mit der Superklasse RemoteService und von LoginServiceImpl Implementiert wird
 */
public interface LoginService extends RemoteService{
	
	public LoginInfo login(String requestURL);

}
