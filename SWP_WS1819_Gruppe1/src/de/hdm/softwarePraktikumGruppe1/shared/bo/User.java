/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared.bo;

import java.sql.Timestamp;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author GianlucaBernert
 * @author Yesin Soufi
 * @autor SebastianHermann
 * Klasse eines User Objekts das BusinessObject als Superklasse besitzt
 */
public class User implements IsSerializable{
	
	private static final long serialVersionUID = 1L;
	private int userId;
	private String nickname;
	private String firstName;
	private String lastName;
	private String gMail;
	private Timestamp creationTimeStamp;

	/*
	 * Leerer Konstruktor. Die Zuweisung der Attribute wird über die Setter-Methoden realisiert.
	 */
	
	public User() {
		
	}
	
	/**
	 * Methode die den Nicknamen eines Users zurück gibt
	 */
	public String getNickname() {
		return nickname;
	}
	
	/**
	 * Methode die den Nicknamen eines Users setzt
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	/**
	 * Methode die den Vornamen des Users zurück gibt
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Methode die den Vornamen eines Users setzt
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Methode die den Nachnamen eines Users zurück gibt
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Methode die den Nachnamen eines Users setzt
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Methode die die E-Mail eines Users zurück gibt
	 */
	public String getGMail() {
		return gMail;
	}
	
	/**
	 * Methode die die E-Mail eines Users setzt
	 */
	public void setGMail(String gMail) {
		this.gMail = gMail;
	}
	
	
	/**
	 * Methode um eine textuelle Dastellung der jeweiligen Instanz zu erzeugen
	 */
	public String toString() {
		return "UserID #U" + this.getUserId() + " " + this.firstName + " " + this.lastName;
	}
	
	/**
	 * Methode die die User ID zurueck gibt
	 */
	public int getUserId() {
		return userId;
	}
	
	/**
	 * Methode die die User ID setzt
	 */
	public void setUserId(int userId) {
		this.userId= userId;
	}

	public Timestamp getCreationTimeStamp() {
		return creationTimeStamp;
	}

	public void setCreationTimeStamp(Timestamp creationTimeStamp) {
		this.creationTimeStamp = creationTimeStamp;
	}

}
