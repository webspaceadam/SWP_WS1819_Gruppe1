/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.server;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Vector;

import de.hdm.softwarePraktikumGruppe1.server.db.*;
import de.hdm.softwarePraktikumGruppe1.shared.Pinnwandverwaltung;
import de.hdm.softwarePraktikumGruppe1.shared.bo.*;

/**
 * @author SebastianHermann
 * Klasse die das Interface Pinnwandverwaltung Implementiert und das RemoteServiceServlet als Superklasse besitzt
 */
public class PinnwandverwaltungImpl extends RemoteServiceServlet implements Pinnwandverwaltung{
	
	private UserMapper uMapper = null;
	private PinnwandMapper pMapper = null;
	private BeitragMapper bMapper = null;
	private KommentarMapper kMapper = null;
	private LikeMapper lMapper = null;
	private AbonnementMapper aMapper = null;
	
	/**
	 * Konstruktor der Klasse PinnwandverwaltungIMpl der bei jedem erzeugten Objekt dieser Klasse ausfgerufen wird
	 */
	public PinnwandverwaltungImpl() throws IllegalArgumentException {
		
	}
	

	/* Initialisierungsmethode, welche die alle Mapper initialisiert.
	 * 
	 */
	
	public void init() throws IllegalArgumentException {
		this.uMapper = UserMapper.userMapper();
		this.pMapper = PinnwandMapper.pinnwandMapper();
		this.bMapper = BeitragMapper.beitragMapper();
		this.kMapper = KommentarMapper.kommentarMapper();
		this.lMapper = LikeMapper.likeMapper();
		this.aMapper = AbonnementMapper.abonnementMapper();
	}

	/**
	 * 
	 * Methode die alle User als ArrayList zurueck gibt
	 */
	public Vector<User> showAllUser(){
		return uMapper.findAll();
	}
	
	// TESTMETHODE
	
	/*
	 * VORSICHT TESTMETHODE
	 * 
	 * Bitte beim anfangen der richtigen implementierung entweder löschen oder in der korrekten methode
	 * den methodenkörper wiederverwenden!!!
	 */
	public User createSingleUserTestMethod(String vorname, String nachname, String nickname) {
		
		//Erstellen eines Nutzerobjekts mit Vorname, Nachname und Nachname
		User u = new User();
		
		u.setFirstName(vorname);
		u.setLastName(nachname);
		u.setNickname(nickname);
		
		//Speichern in der DB
		return this.uMapper.insert(u);
			
	}
	
	/**
	 * Methode um einen User zu erzeugen
	 */
	public void createUser(String firstName, String lastName, String nickName, String gMail, Timestamp timestamp ) throws IllegalArgumentException {
		User u = new User();
		u.setFirstName(firstName);
		u.setLastName(lastName);
		u.setNickname(nickName);
		u.seteMail(gMail);
		//u.setTimestamp(timestamp);
		uMapper.insert(u);
	
	}
	
	/**
	 * Methode um einen User zu Bearbeiten
	 */
	public void editUser(User u) {
		uMapper.update(u);
	}
	
	/**
	 * Methode um einen User zu Loeschen
	 */
	public void deleteUser() {
		//Alle Likes des Users löschen
		//Alle Abonements des Users löschen
		//Alle Kommentare des Users löschen
		//Alle Beiträge des Users löschen
		//Pinnwand des Users löschen
		//User des Users löschen
	}
	
	/**
	 * Methode zur Ueberpruefung der Zugangsberechtigung 
	 */
	public User loginCheck(String nickname, String password) {
		return null;
	}
	
	/**
	 * Methode um einen User anhand seiner ID zu suchen
	 */
	public User searchUserById(int userId) {
		return uMapper.findByUserID(userId);
	}
	
	/**
	 * Methode um einen User upzudaten
	 */
	public User updateUser(User u) {
		// ?
		return null;
	}
	
	/**
	 * Methode um einen User anhand seines Nicknamens zu suchen
	 */
	public User searchUserByNickname(String nickname) {
		return uMapper.findUserByNickname(nickname);
	}
	
	/**
	 * Methode um einen Beitrag zu erzeugen
	 */
	public void createBeitrag(String inhalt) {
	}
	
	/**
	 * Methode um alle Beiträge eines Users auszugeben
	 */
	public ArrayList<Beitrag> findAllBeitraegeOfUser(User u){
		return null;
	}
	
	/**
	 * Methode um einen Beitrag zu Loeschen
	 */
	public void deleteBeitrag(Beitrag b) {
	}
	
	/**
	 * Methode um einen Beitrag zu Bearbeiten
	 */
	public Beitrag editBeitrag(Beitrag b) {
		return null;
	}
	
	/**
	 * Methode um alle Abonnements eines Users anzuzeigen
	 */
	public ArrayList<Abonnement> showAllAbonnementsByUser(User u){
		return null;
	}
	
	/**
	 * Methode um ein neues Abonnement zu erzeugen
	 */
	public void creatAbonnement(User u1, User u2) {
	}
	
	/**
	 * Methode um ein bestehendes Abonnement zu Loeschen
	 */
	public void deleteAbonnement(Abonnement a) {
	}
	
	/**
	 * Methode um einen neues Kommentar zu erzeugen
	 */
	public void createKommentar(String inhalt) {
	}
	
	/**
	 * Methode zum Loeschen eines Kommentars
	 */
	public void deleteKommentar(Kommentar k) {
	}
	
	/**
	 * Methode zum anzeigen aller Kommentare
	 */
	public ArrayList<Kommentar> findAllKommentare(Beitrag b){
		return null;
	}
	
	/**
	 * Methode zum Bearbeiten eines Kommentars
	 */
	public Kommentar editKommentar(Kommentar k) {
		return null;
	}
	
	/**
	 * Methode zum erzeugen eines Likes
	 */
	public void createLike(Like l, Beitrag b) {
	}
	
	/**
	 * Methode zur Ueberpruefung ob der Beitrag bereits geliket ist
	 */
	public boolean likeCheck(User u, Beitrag b) {
		return true;
	}
	
	/**
	 * Methode um einen Beitrag zu entliken
	 */
	public void deleteLike(Like l) {
	}
	
	/**
	 * Methode um ein Like zu suchen
	 */
	public Like searchLike(Like l) {
		return null;
	}
	
	/**
	 * Methode um alle Likes eines Beitrags zu zaehlen
	 */
	public int countLikes(Beitrag b) {
		return 0;
	}
	
	/**
	 * Methode um Likes eines Beitrags zu entfernen
	 */
	public void deleteLikesOfBeitrag(Beitrag b) {

	}

	@Override
	public void createUser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editUser() {
		// TODO Auto-generated method stub
		
	}
	
	
}

	
	
	
	
	
	
	

