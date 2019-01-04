/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared.bo;

/**
 * @author GianlucaBernert
 * @author Yesin Soufi
 * @autor SebastianHermann
 * Klasse eines User Objekts das BusinessObject als Superklasse besitzt
 */
public class User extends BusinessObject{
	
	private static final long serialVersionUID = 1L;
	private String nickname;
	private String firstName;
	private String lastName;
	private String gMail;
	private int likeAmount;
	private int commentAmount;
	private int userID;
	
	/**
	 * Der Konstruktor nimmt vier String Parameter entgegen um so die vollständige instanzierung 
	 * eines User-Objekts zu ermöglichen. 
	 * 
	 * @param nickname
	 * @param first
	 * @param last
	 * @param gMail
	 */
	public User(String nickname, String first, String last, String gMail) {
		this.nickname = nickname;
		this.firstName = first;
		this.lastName = last;
		this.gMail = gMail;
		
		userID += 1;
	}
	
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
		return super.toString() + " UserID #" + this.getUserId() + " " + this.firstName + " " + this.lastName;
	}
	
	/**
	 * Methode die die User ID zurueck gibt
	 */
	public int getUserId() {
		return userID;
	}
	
	/**
	 * Methode die die User ID setzt
	 */
	public void setUserId(int userId) {
		this.userID = userId;
	}


	
	



}
