/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared;

import java.io.Serializable;

/**
 * 
 * @code LoginInfo contains the login info from the User service.
 * 
 * 
 * @author GianlucaBernert
 * @author JakobBenkoe
 *
 */
public class LoginInfo implements Serializable {

	  private boolean loggedIn = false;
	  private String loginUrl;
	  private String logoutUrl;
	  private String emailAddress;
	  private String nickname;

	  /**
	   * 
	   * @return loggedIn Information ob der User eingeloggt ist oder nicht
	   */
	  public boolean isLoggedIn() {
	    return loggedIn;
	  }
	  /**
	   * 
	   * @param loggedIn setzt den Login Status
	   */
	  public void setLoggedIn(boolean loggedIn) {
	    this.loggedIn = loggedIn;
	  }
	  /**
	   * 
	   * @return loginUrl die URL wohin der User weitergeleitet werden
	   * kann um sich einzuloggen
	   */
	  public String getLoginUrl() {
	    return loginUrl;
	  }
	  /**
	   * 
	   * @param loginUrl
	   */
	  public void setLoginUrl(String loginUrl) {
	    this.loginUrl = loginUrl;
	  }
	  /**
	   * 
	   * @return die URL wohin der User weitergeleitet werden
	   * kann um sich auszuloggen
	   */
	  public String getLogoutUrl() {
	    return logoutUrl;
	  }
	  /**
	   * 
	   * @param logoutUrl
	   */
	  public void setLogoutUrl(String logoutUrl) {
	    this.logoutUrl = logoutUrl;
	  }
	  /**
	   * 
	   * @return emailAddress gibt die  die gMail des Users zurück
	   */
	  public String getEmailAddress() {
	    return emailAddress;
	  }
	  /**
	   * 
	   * @param emailAddress setzt die gMail des Users
	   */
	  public void setEmailAddress(String emailAddress) {
	    this.emailAddress = emailAddress;
	  }
	  /**
	   * 
	   * @return nickname gibt den Google Nickname des Users zurück
	   * Nicht zu verwechseln mit dem BO Nickname @see User
	   */
	  public String getNickname() {
	    return nickname;
	  }
	  /**
	   * 
	   * @param nickname
	   */
	  public void setNickname(String nickname) {
	    this.nickname = nickname;
	  }
	}