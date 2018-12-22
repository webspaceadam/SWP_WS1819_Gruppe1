/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared.bo;

import java.sql.Timestamp;
import java.util.*;

/**
 * @author GianlucaBernert
 * Klasse eines Beitrag Objekts das Textbeitrag als Superklasse besitzt
 */
public class Beitrag extends Textbeitrag{
	
	private static final long serialVersionUID = 1L;
	private int beitragID;
	private int userFK;
	private int likeFK;
	private int pinnwandFK;
	private int kommentarFK;
	private int commentAmount;
	private int likeAmount;
	private String text;
	private Timestamp timestamp;

	//Getter- und Setter-Methoden
	
	public int getLike() {
		return likeFK;
	}
	public void setLike(int likeFK) {
		this.likeFK = likeFK;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public int getBeitragID() {
		return beitragID;
	}
	public void setBeitragID(int beitragID) {
		this.beitragID = beitragID;
	}
	public int getUser() {
		return userFK;
	}
	public void setUser(int userFK) {
		this.userFK = userFK;
	}
	
	public int getLikeAmount() {
		return likeAmount;
	}
	public void setLikeAmount(int likeAmount) {
		this.likeAmount = likeAmount;
	}
	public int getKommentar() {
		return kommentarFK;
	}
	public void setKommentar(int kommentarFK) {
		this.kommentarFK = kommentarFK;
	}
	public int getCommentAmount() {
		return commentAmount;
	}
	public void setCommentAmount(int commentAmount) {
		this.commentAmount = commentAmount;
	}
	public int getPinnwand() {
		return pinnwandFK;
	}

	public void setPinnwand(int pinnwandFK) {
		this.pinnwandFK = pinnwandFK;
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


