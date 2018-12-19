/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared.bo;

import java.util.*;

/**
 * @author GianlucaBernert
 * Klasse eines Beitrag Objekts das Textbeitrag als Superklasse besitzt
 */
public class Beitrag extends Textbeitrag{
	
	private static final long serialVersionUID = 1L;
	private int beitragID;
	private User user;
	private Vector<Like> likes;
	private int likeAmount;
	private Kommentar kommentar;
	private int commentAmount;
	private Pinnwand pinnwand;
	private Vector<Kommentar> kommentare;
	private String text;
	
	
	
	
	//Getter- und Setter-Methoden
	
	public int getBeitragID() {
		return beitragID;
	}
	public void setBeitragID(int beitragID) {
		this.beitragID = beitragID;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Like getLike() {
		return like;
	}
	public void setLike(Like like) {
		this.like = like;
	}
	public int getLikeAmount() {
		return likeAmount;
	}
	public void setLikeAmount(int likeAmount) {
		this.likeAmount = likeAmount;
	}
	public Kommentar getKommentar() {
		return kommentar;
	}
	public void setKommentar(Kommentar kommentar) {
		this.kommentar = kommentar;
	}
	public int getCommentAmount() {
		return commentAmount;
	}
	public void setCommentAmount(int commentAmount) {
		this.commentAmount = commentAmount;
	}
	public Pinnwand getPinnwand() {
		return pinnwand;
	}
	public void setPinnwand(Pinnwand pinnwand) {
		this.pinnwand = pinnwand;
	}
	public Vector<Kommentar> getKommentare() {
		return kommentare;
	}
	public void setKommentare(Vector<Kommentar> kommentare) {
		this.kommentare = kommentare;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
