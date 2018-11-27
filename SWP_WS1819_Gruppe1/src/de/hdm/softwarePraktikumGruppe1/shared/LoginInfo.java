/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared;

/**
 * @author GianlucaBernert
 *
 */
public class LoginInfo {
	
	private boolean loggedIn;
	private String loginURL;
	private String logoutURL;
	private String eMailAdress;
	private String nickname;
	
	/**
	 * Methode zum überprüfen ob der User eingeloggt ist
	 */
	public boolean isLoggedIn() {
		return true;
	}

	/**
	 * Methode zum einloggen eines Users
	 */
	public void setLoggedIn(boolean loggedIn) {
		
	}
	
	/**
	 * Methode die die Login URL zurück gibt
	 */
	public String getLoginURL() {
		return loginURL;
	}

	/**
	 * Methode die die Login URL setzt
	 */
	public void setLoginURL(String loginURL) {
		this.loginURL = loginURL;
	}

	/**Methode die die Logout URL zurück gibt
	 */
	public String getLogoutURL() {
		return logoutURL;
	}

	/**
	 * Methode die die Logout URL setzt
	 */
	public void setLogoutURL(String logoutURL) {
		this.logoutURL = logoutURL;
	}

	/**
	 * Methode die die E-Mail Adresse zurück gibt
	 */
	public String geteMailAdress() {
		return eMailAdress;
	}

	/**
	 * Methode die die E-Mail Adresse setzt
	 */
	public void seteMailAdress(String eMailAdress) {
		this.eMailAdress = eMailAdress;
	}

	/**
	 * Methode die den Nicknamen zurück gibt
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * Methode die den Nicknamen setzt
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
	
	

}
