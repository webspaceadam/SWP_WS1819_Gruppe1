/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared.bo;

import java.sql.Timestamp;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author GianlucaBernert
 * Klasse eines Like Objekts das BusinessObject als Superklasse besitzt
 */
public class Like implements IsSerializable{
	
	private static final long serialVersionUID = 1L;
	private int likeId;
	private int beitragId;
	private int ownerId;
	private Timestamp creationTimeStamp;
	
	/*
	 * Leerer Konstruktor. Die Zuweisung der Attribute wird über die Setter-Methoden realisiert.
	 */
	public Like(){
		
	}
	
	public int getBeitragId() {
		return beitragId;
	}
	public void setBeitragId(int beitragId) {
		this.beitragId = beitragId;
	}

	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int userId) {
		this.ownerId = userId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getLikeId() {
		return likeId;
	}

	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}
	
	/**
	 * Methode um eine textuelle Dastellung der jeweiligen Instanz zu erzeugen
	 */
	public String toString() {
		return super.toString() + " LikeID #L" + this.getLikeId() + " Referenzbeitrag " + this.getBeitragId();
	}
	
	public Timestamp getCreationTimeStamp() {
		return creationTimeStamp;
	}

	public void setCreationTimeStamp(Timestamp creationTimeStamp) {
		this.creationTimeStamp = creationTimeStamp;
	}

	
	

}
