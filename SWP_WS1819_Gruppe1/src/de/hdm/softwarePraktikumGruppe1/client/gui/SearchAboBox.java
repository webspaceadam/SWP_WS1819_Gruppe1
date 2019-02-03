package de.hdm.softwarePraktikumGruppe1.client.gui;

import java.sql.Timestamp;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import de.hdm.softwarePraktikumGruppe1.client.ClientsideSettings;
import de.hdm.softwarePraktikumGruppe1.shared.PinnwandverwaltungAsync;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Abonnement;
import de.hdm.softwarePraktikumGruppe1.shared.bo.User;

/**
 * Die Klasse <code>SearchAboBox</code> zeigt alle möglichen Ergebnisse der Suche 
 * nach einem vom User eingegebenen Keyword.  
 * @author AdamGniady
 * @author SebastianHermann
 */
public class SearchAboBox extends FlowPanel {
	
	private String accountNameOfShownUser;
	private String nicknameOfShownUser;
	private User shownUser;
	private User loggedInUser;
	PinnwandverwaltungAsync pinnwandverwaltung;
	private Abonnement abonnementbetweenShownUserAndLoggedInUser;
	private static Timestamp timestamp;
	
	private Label accountNameLabel;
	private Label nicknameLabel;
	
	private FlowPanel accountWrapper;
	private FlowPanel nickWrapper;
	private FlowPanel aboWrapper;
	
	/*
	 * Konstruktor, der das Übergeben des aktiven Users und des Suchergebnisses ermöglicht.
	 */
	public SearchAboBox(User activeUser, User resultUser) {
		this.loggedInUser = activeUser;
		this.shownUser = resultUser;
		this.accountNameOfShownUser = resultUser.getFirstName() + " " + resultUser.getLastName();
		this.nicknameOfShownUser=resultUser.getNickname();	
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Widget#onLoad()
	 */
	public void onLoad() {
		
		pinnwandverwaltung = ClientsideSettings.getPinnwandverwaltung();
		pinnwandverwaltung.getAbonnementBetweenUsers(loggedInUser, shownUser, new AbonnementCallBack());
		
		accountNameLabel = new Label();
		nicknameLabel = new Label();
		accountWrapper = new FlowPanel();
		nickWrapper = new FlowPanel();
		aboWrapper = new FlowPanel();
		accountNameLabel.setText(accountNameOfShownUser);
		nicknameLabel.setText(nicknameOfShownUser);
				
		/*
		 * Style der Dialogbox und alle dazugehörigen Widgets & Panels
		 */
		this.addStyleName("box grid_box radiusless");
		accountWrapper.addStyleName("box-item-ein-viertel");
		nickWrapper.addStyleName("box-item-ein-viertel");
		aboWrapper.addStyleName("box-item-ein-viertel");
		accountNameLabel.addStyleName("title is-size-3");
		nicknameLabel.addStyleName("is-size-4");
		
		/*
		 *  Hinzufuegen der Widgets und Panels zu den einzelnen Wrappern
		 */
		accountWrapper.add(accountNameLabel);
		nickWrapper.add(nicknameLabel);
	
		/*
		 * Hinzufuegen der Wrapper zum Parent Panel
		 */
		this.add(accountWrapper);
		this.add(nickWrapper);
		this.add(aboWrapper);
	}
	
	/**
	 * Die Nested-Class <code>SetAbonnementClickHandler</code> implementiert das ClickHandler-Interface,
	 * welches es ermöglicht auf den Klick auf Create Abo zu reagieren.
	 */
	class SetAbonnementClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {	
			timestamp = new Timestamp(System.currentTimeMillis());
			pinnwandverwaltung.createAbonnement(loggedInUser, shownUser, timestamp, new SetAbonnementCallback());
		}	
	}
	
	/** 
	 * Die Nested-Class <code>DeleteAbonnementClickHandler</code> implementiert das ClickHandler-Interface,
	 * welches es ermöglicht auf den Klick zum Deabonnieren zu reagieren.
	 */
	class DeleteAbonnementClickHandler implements ClickHandler {
		@Override 
		public void onClick(ClickEvent event) {
			pinnwandverwaltung.deleteAbonnement(abonnementbetweenShownUserAndLoggedInUser, new DeleteAbonnementCallBack());
		}
	}
	
	
	/*
	 * Beginn: Initialisierung der Nested-Callback-Klassen
	 */
	
	/**
	 * Abonnement-Callback. Überprüft ob ein Abonnement-Objekt zwischen aktivem User und dem User des jeweiligen Suchergebnisses existiert. 
	 * Existiert ein Abonnement, so wird dieses lokal gespeichert.
	 */
	class AbonnementCallBack implements AsyncCallback<Abonnement>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Abonnement RPC fehlgeschlagen");	
		}

		@Override
		public void onSuccess(Abonnement result) {
			//Es wird ein lokaler Button erstellt.
			Button getAboBtn = new Button();
			getAboBtn.addStyleName("button");
			
			//Die If-Anweisung prüft ob das vom Call-Back erhaltene Objekt eine leer ist. Die GUI wird entsprechend angepasst.
			if(result.getAbonnementId()==0) {
				//Existiert noch kein Abonnement-Objekt, wird ein SetAbonnementClickHandler erstellt, der das Erstellen eines Abonnement-Objekts ermöglicht.
				getAboBtn.addClickHandler(new SetAbonnementClickHandler());
				getAboBtn.setText("Abonnieren");
//				getAboBtn.addStyleName("abonnementbutton");
				
			} else {
				//Existiert ein Abonnement-Objekt, so wird ein DeleteAbonnementClickHandler erstellt, der das Löschen des Abonnement-Objekts ermöglicht.
				abonnementbetweenShownUserAndLoggedInUser=result;
				getAboBtn.addClickHandler(new DeleteAbonnementClickHandler());
				getAboBtn.setText("Deabonnieren");
//				getAboBtn.addStyleName("deabonnementbutton");
			}
			aboWrapper.add(getAboBtn);
			
		}
			
	}
	
	/**
	 * SetAbonnement-Callback. Erzeugt ein Abonnement-Objekt zwischen aktivem User und dem User des jeweiligen Suchergebnisses. 
	 * 
	 */
	class SetAbonnementCallback implements AsyncCallback<Abonnement> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Abonnement RPC fehlgeschlagen");	
		}

		@Override
		public void onSuccess(Abonnement result) {
			//Clear Child-Widgets
			aboWrapper.clear();
			//Speichern des bestehenden Abonnement-Objekts
			abonnementbetweenShownUserAndLoggedInUser=result;
			//Aktualisierung der ShowAboBox. Es wird erneut die Methode aufgerufen, die prüft ob ein Abonnement zwischen den beiden Instanzen bereits besteht. 
			pinnwandverwaltung.getAbonnementBetweenUsers(loggedInUser, shownUser, new AbonnementCallBack());
			
		}
	}
	
	/**
	 * SetAbonnement-Callback. Löscht das Abonnement-Objekt zwischen aktivem User und dem User des jeweiligen Suchergebnisses. 
	 * 
	 */
	class DeleteAbonnementCallBack implements AsyncCallback<Void>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Delete RPC fehlgeschlagen");
		}
	
		@Override
		public void onSuccess(Void result) {
			//Clear Child-Widgets
			aboWrapper.clear();
			//Das bestehende Abonnement-Objekt wird auf "null" gesetzt. 
			abonnementbetweenShownUserAndLoggedInUser=null;
			//Aktualisierung der ShowAboBox. Es wird erneut die Methode aufgerufen, die prüft ob ein Abonnement zwischen den beiden Instanzen bereits besteht. 
			pinnwandverwaltung.getAbonnementBetweenUsers(loggedInUser, shownUser, new AbonnementCallBack());
		}
			
	}
	
	/*
	 * Ende: Initialisierung der Nested-Callback-Klassen
	 */
	
}


