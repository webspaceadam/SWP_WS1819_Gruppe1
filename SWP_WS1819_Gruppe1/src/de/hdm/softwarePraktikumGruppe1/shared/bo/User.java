/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared.bo;

/**
 * @author GianlucaBernert
 * @author Yesin Soufi
 * Klasse eines User Objekts das BusinessObject als Superklasse besitzt
 */
public class User extends BusinessObject{
	
	private static final long serialVersionUID = 1L;
	private String nickname;
	private String firstName;
	private String lastName;
	private String password;
	private String eMail;
	private int likeAmount;
	private int commentAmount;
	
	/**
	 * Methode die den Nicknamen eines Users zurück gibt
	 */
	public static String getNickname() {
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
	 * Methode die das Passwort eines Users zurück gibt
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Methode die das Passwort eines Users setzt
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Methode die die E-Mail eines Users zurück gibt
	 */
	public String geteMail() {
		return eMail;
	}
	
	/**
	 * Methode die die E-Mail eines Users setzt
	 */
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	
	/**
	 * Methode die die Anzahl der Likes eines Useres zurück gibt
	 */
	public int getLikeAmount() {
		return likeAmount;
	}
	
	/**
	 * Methode die die Anzahl der Likes eines Users setzt
	 */
	public void setLikeAmount(int likeAmount) {
		this.likeAmount = likeAmount;
	}
	
	/**
	 * Methode die die Anzhal der Kommentare eines Users zurück gibt
	 */
	public int getCommentAmount() {
		return commentAmount;
	}
	
	/**
	 * Methode die die Anzahl der Kommentare eines Users setzt
	 */
	public void setCommentAmount(int commentAmount) {
		this.commentAmount = commentAmount;
	}
	
	/**
	 * Methode um eine textuelle Dastellung der jeweiligen Instanz zu erzeugen
	 */
	public String toString() {
		return super.toString() + " " + this.firstName + " " + this.lastName;
	}



}
