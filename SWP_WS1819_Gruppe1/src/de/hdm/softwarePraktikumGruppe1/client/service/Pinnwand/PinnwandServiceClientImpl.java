package de.hdm.softwarePraktikumGruppe1.client.service.Pinnwand;

import java.sql.Timestamp;
import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import de.hdm.softwarePraktikumGruppe1.server.db.AbonnementMapper;
import de.hdm.softwarePraktikumGruppe1.server.db.BeitragMapper;
import de.hdm.softwarePraktikumGruppe1.server.db.KommentarMapper;
import de.hdm.softwarePraktikumGruppe1.server.db.LikeMapper;
import de.hdm.softwarePraktikumGruppe1.server.db.PinnwandMapper;
import de.hdm.softwarePraktikumGruppe1.server.db.UserMapper;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Abonnement;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Kommentar;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Like;
import de.hdm.softwarePraktikumGruppe1.shared.bo.User;

public class PinnwandServiceClientImpl implements PinnwandServiceClientInt{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserMapper uMapper = null;
	private PinnwandMapper pMapper = null;
	private BeitragMapper bMapper = null;
	private KommentarMapper kMapper = null;
	private LikeMapper lMapper = null;
	private AbonnementMapper aMapper = null;
	private PinnwandServiceAsync service;
	//private MainGUI maingui;
	
	public PinnwandServiceClientImpl(String url) throws IllegalArgumentException{
		System.out.println(url);
		this.service = GWT.create(PinnwandService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(url);
		
		//this.maingui = new MainGUI(this);
	}

	/* 
	 * Initialisierungsmethode, welche die alle Mapper initialisiert.
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
	public void showAllUser(){
		this.service.showAllUser(new DefaultCallback1());
	}
	
	private class DefaultCallback1 implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof User)	{
				User userVector = (User) result;
				//maingui.showAllUser(userVector);
				
			}
		}
	}
	
	// TESTMETHODE
	
	/*
	 * VORSICHT TESTMETHODE
	 * 
	 * Bitte beim anfangen der richtigen implementierung entweder löschen oder in der korrekten methode
	 * den methodenkörper wiederverwenden!!!
	 */
	public void createSingleUserTestMethod(String vorname, String nachname, String nickname) {
		
		//Erstellen eines Nutzerobjekts mit Vorname, Nachname und Nachname
		User u = new User();
		
		u.setFirstName(vorname);
		u.setLastName(nachname);
		u.setNickname(nickname);
			
		this.service.createSingleUserTestMethod(vorname, nachname, nickname, new DefaultCallback2());
	}
	
	private class DefaultCallback2 implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof String)	{
				String user = (String) result;
				//maingui.createSingleUserTestMethod(user);
				
			}
		}
	}
	
	/**
	 * Methode um einen User zu erzeugen
	 */
	public void createUser(String firstName, String lastName, String nickname, String gMail, Timestamp timestamp ) throws IllegalArgumentException {
		User u = new User();
		u.setFirstName(firstName);
		u.setLastName(lastName);
		u.setNickname(nickname);
		u.seteMail(gMail);
		//u.setTimestamp(timestamp);
		uMapper.insert(u);
		this.service.createUser(firstName, lastName, nickname, gMail, timestamp, new DefaultCallback3());
	}
	
	private class DefaultCallback3 implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof String)	{
				String user = (String) result;
				//maingui.updateLabel(user);
			}else if(result instanceof Timestamp) {
				Timestamp timestamp = (Timestamp) result;
				//maingui.createUser(timestamp);
			}
		}
	}
	
	/**
	 * Methode um einen User zu Bearbeiten
	 */
	public void editUser(User u) {
		uMapper.update(u);
		this.service.editUser(u, new DefaultCallback4());
	}
	
	private class DefaultCallback4 implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof User)	{
				User user = (User) result;
				//maingui.editUser(user);
				
			}
		}
	}
	
	/**
	 * Methode um einen User zu Loeschen
	 */
	public void deleteUser(User u) {
		//Alle Likes des Users löschen
		//Alle Abonements des Users löschen
		//Alle Kommentare des Users löschen
		//Alle Beiträge des Users löschen
		//Pinnwand des Users löschen
		//User des Users löschen
		this.service.deleteUser(new DefaultCallback5());
	}
	
	private class DefaultCallback5 implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof User)	{
				User user = (User) result;
				//maingui.deleteUser(user);
				
			}
		}
	}
	
	/**
	 * Methode zur Ueberpruefung der Zugangsberechtigung 
	 */
	public void loginCheck(String nickname, String password) {
		this.service.loginCheck(nickname, password, new DefaultCallback6());
	}
	
	private class DefaultCallback6 implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof String)	{
				String login = (String) result;
				//maingui.loginCheck(login);
				
			}
		}
	}
	
	/**
	 * Methode um einen User anhand seiner ID zu suchen
	 */
	public void searchUserById(int userId) {
		this.service.searchUserById(userId, new DefaultCallback7());
	}
	
	private class DefaultCallback7 implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof Integer)	{
				Integer id = (Integer) result;
				//maingui.searchUserById(id);
				
			}
		}
	}
	
	/**
	 * Methode um einen User upzudaten
	 */
	public void updateUser(User u) {
		this.service.updateUser(u, new DefaultCallback8());
	}
	
	private class DefaultCallback8 implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof User)	{
				User user = (User) result;
				//maingui.updateUser(user);
				
			}
		}
	}
	
	/**
	 * Methode um einen User anhand seines Nicknamens zu suchen
	 */
	public void searchUserByNickname(String nickname) {
		this.service.searchUserByNickname(nickname, new DefaultCallback9());
	}
	
	private class DefaultCallback9 implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof String)	{
				String nickname = (String) result;
				//maingui.searchUserByNickname(nickname);
				
			}
		}
	}
	
	/**
	 * Methode um einen Beitrag zu erzeugen
	 */
	public void createBeitrag(String inhalt) {
		this.service.createBeitrag(inhalt, new DefaultCallback10());
	}
	
	private class DefaultCallback10 implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof String)	{
				String inhalt = (String) result;
				//maingui.createBeitrag(inhalt);
				
			}
		}
	}
	
	/**
	 * Methode um alle Beiträge eines Users auszugeben
	 */
	public void findAllBeitraegeOfUser(User u){
		this.service.findAllBeitraegeOfUser(u, new DefaultCallback11());
	}
	
	private class DefaultCallback11 implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof User)	{
				User user = (User) result;
				//maingui.findAllBeitraegeOfUser(user);
				
			}
		}
	}
	
	/**
	 * Methode um einen Beitrag zu Loeschen
	 */
	public void deleteBeitrag(Beitrag b) {
		this.service.deleteBeitrag(b, new DefaultCallback12());
	}
	
	private class DefaultCallback12 implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof Beitrag)	{
				Beitrag beitrag = (Beitrag) result;
				//maingui.deleteBeitrag(beitrag);
				
			}
		}
	}
	
	/**
	 * Methode um einen Beitrag zu Bearbeiten
	 */
	public void editBeitrag(Beitrag b) {
		this.service.editBeitrag(b, new DefaultCallback13());
	}
	
	private class DefaultCallback13 implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof Beitrag)	{
				Beitrag beitrag = (Beitrag) result;
				//maingui.editBeitrag(beitrag);
				
			}
		}
	}
	
	/**
	 * Methode um alle Abonnements eines Users anzuzeigen
	 */
	public void showAllAbonnementsByUser(User u){
		this.service.showAllAbonnementsByUser(u, new DefaultCallback14());
	}
	
	private class DefaultCallback14 implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof User)	{
				User user = (User) result;
				//maingui.showAllAbonnementsByUser(user);
				
			}
		}
	}
	
	/**
	 * Methode um ein neues Abonnement zu erzeugen
	 */
	public void createAbonnement(User u1, User u2) {
		this.service.createAbonnement(u1, u2, new DefaultCallback15());
	}
	
	private class DefaultCallback15 implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof User)	{
				User user = (User) result;
				//maingui.createAbonnement(user);
				
			}
		}
	}
	
	/**
	 * Methode um ein bestehendes Abonnement zu Loeschen
	 */
	public void deleteAbonnement(Abonnement a) {
		this.service.deleteAbonnement(a, new DefaultCallback16());
	}
	
	private class DefaultCallback16 implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof Abonnement)	{
				Abonnement abo = (Abonnement) result;
				//maingui.deleteAbonnement(abo);
				
			}
		}
	}
	
	/**
	 * Methode um einen neues Kommentar zu erzeugen
	 */
	public void createKommentar(String inhalt) {
		this.service.createKommentar(inhalt, new DefaultCallback17());
	}
	
	private class DefaultCallback17 implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof String)	{
				String inhalt = (String) result;
				//maingui.createKommentar(inhalt);
				
			}
		}
	}
	
	/**
	 * Methode zum Loeschen eines Kommentars
	 */
	public void deleteKommentar(Kommentar k) {
		this.service.deleteKommentar(k, new DefaultCallback18());
	}
	
	private class DefaultCallback18 implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof Kommentar)	{
				Kommentar kommentar = (Kommentar) result;
				//maingui.deleteKommentar(kommentar);
				
			}
		}
	}
	
	/**
	 * Methode zum anzeigen aller Kommentare
	 */
	public void findAllKommentare(Beitrag b){
		this.service.findAllKommentare(b, new DefaultCallback19());
	}
	
	private class DefaultCallback19 implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof Beitrag)	{
				Beitrag beitrag = (Beitrag) result;
				//maingui.findAllKommentare(beitrag);
				
			}
		}
	}
	
	/**
	 * Methode zum Bearbeiten eines Kommentars
	 */
	public void editKommentar(Kommentar k) {
		this.service.editKommentar(k, new DefaultCallback20());
	}
	
	private class DefaultCallback20 implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof Kommentar)	{
				Kommentar kommentar = (Kommentar) result;
				//maingui.editKommentar(kommentar);
				
			}
		}
	}
	
	/**
	 * Methode zum erzeugen eines Likes
	 */
	public void createLike(Like l, Beitrag b) {
		this.service.createLike(l, b, new DefaultCallback21());
	}
	
	private class DefaultCallback21 implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof Like)	{
				Like like = (Like) result;
				//maingui.createLike(like);
			}else if(result instanceof Beitrag) {
				Beitrag beitrag = (Beitrag) result;
				//maingui.createLike(beitrag);
			}
		}
	}
	
	/**
	 * Methode zur Ueberpruefung ob der Beitrag bereits geliket ist
	 */
	public void likeCheck(User u, Beitrag b) {
		this.service.likeCheck(u, b, new DefaultCallback22());
	}
	
	private class DefaultCallback22 implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof User)	{
				User user = (User) result;
				//maingui.likeCheck(user);
			}else if(result instanceof Beitrag) {
				Beitrag beitrag = (Beitrag) result;
				//maingui.likeCheck(beitrag);
			}
		}
	}
	
	/**
	 * Methode um einen Beitrag zu entliken
	 */
	public void deleteLike(Like l) {
		this.service.deleteLike(l, new DefaultCallback23());
	}
	
	private class DefaultCallback23 implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof Like)	{
				Like like = (Like) result;
				//maingui.deleteLike(like);
				
			}
		}
	}
	
	/**
	 * Methode um ein Like zu suchen
	 */
	public void searchLike(Like l) {
		this.service.searchLike(l, new DefaultCallback24());
	}
	
	private class DefaultCallback24 implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof Like)	{
				Like like = (Like) result;
				//maingui.searchLike(like);
				
			}
		}
	}
	
	/**
	 * Methode um alle Likes eines Beitrags zu zaehlen
	 */
	public void countLikes(Beitrag b) {
		this.service.countLikes(b, new DefaultCallback25());
	}
	
	private class DefaultCallback25 implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof Beitrag)	{
				Beitrag beitrag = (Beitrag) result;
				//maingui.countLikes(beitrag);
				
			}
		}
	}
	
	/**
	 * Methode um Likes eines Beitrags zu entfernen
	 */
	public void deleteLikesOfBeitrag(Beitrag b) {
	this.service.deleteLikesOfBeitrag(b, new DefaultCallback26());
	}
	
	private class DefaultCallback26 implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has occured");
			
		}

		@Override
		public void onSuccess(Object result) {
			if(result instanceof Beitrag)	{
				Beitrag beitrag = (Beitrag) result;
				//maingui.deleteLikesOfBeitrag(beitrag);
				
			}
		}
	}
}
