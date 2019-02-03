package de.hdm.softwarePraktikumGruppe1.client;

import java.sql.Timestamp;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.softwarePraktikumGruppe1.client.gui.AuthenticationForm;
import de.hdm.softwarePraktikumGruppe1.client.gui.Header;
import de.hdm.softwarePraktikumGruppe1.client.gui.PinnwandBox;
import de.hdm.softwarePraktikumGruppe1.client.gui.ProfileBox;
import de.hdm.softwarePraktikumGruppe1.shared.LoginInfo;
import de.hdm.softwarePraktikumGruppe1.shared.PinnwandverwaltungAsync;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Pinnwand;
import de.hdm.softwarePraktikumGruppe1.shared.bo.User;

/**
 * Die Klasse <code>SWP_WS1819_Gruppe1</code> enth�lt alle Elemente zur Darstellung des
 * Dashboards f�r das KontaktSystem bzw. den Systemteil des <em>Editor</em>.
 * Dieses ist in mehrere Bereiche aufgeteilt.
 * Dabei ist es aufgeteilt in einen Header-Bereich und einen ein- bis zweiteiligen 
 * Dashboardteil.  
 *@author Adam Gniady
 */
public class SWP_WS1819_Gruppe1 implements EntryPoint {
	PinnwandverwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandverwaltung();
	User u1 = new User();
	private LoginInfo loginInfo = null;
	
	private Label nickName = new Label("Nickname");
	private Label lastName = new Label("Nachname");
	private Label firstName = new Label("Vorname");
	
	private TextBox nickInput = new TextBox();
	private TextBox lastInput = new TextBox();
	private TextBox firstInput = new TextBox();

	
	/**
	 * Die <code>onModuleLoad()</code> implementiert die "Main"-Methode des Systems.
	 */
	public void onModuleLoad() {
		// Check login status using login service.
	    LoginServiceAsync loginService = GWT.create(LoginService.class);
	    loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
	      public void onFailure(Throwable error) {
	    	  RootPanel.get().add(new HTML(error.toString()));
	      }

	      public void onSuccess(LoginInfo result) {
	        loginInfo = result;
	        if(loginInfo.isLoggedIn()) {
	          loadEditor(result);
	        } else {
	          loadLogin();
	        }
	      }
	    });
	}
	
	
	//Login Panel anzeigen
	public void loadLogin(){		
	    RootPanel.get().add(new AuthenticationForm(loginInfo.getLoginUrl()));
	}
	
	
	//wird erst nach Erfolgreichem Login geladen
	public void loadEditor(LoginInfo loginInfo){
		//Window.alert("Eingeloggt mit der mail " + loginInfo.getEmailAddress());
		pinnwandVerwaltung.getUserByGmail(loginInfo.getEmailAddress(), new GetUserCallback());
		//Window.alert(loginInfo.getEmailAddress());
		//pinnwandVerwaltung.getUserById(1, new GetUserCallback());
	}
	
	/**
	 * Die Methode <code>loadPinnwand()</code> setzt die Pinnwand korret zusammen.
	 */
	public void loadPinnwand() {
		// RootPanels
		RootPanel rootPanelHeader = RootPanel.get("header");
		RootPanel rootPanelContainer = RootPanel.get("container");
		RootPanel rootProfilePanel = RootPanel.get("linkeSeite");
		RootPanel rootPinnwandPanel = RootPanel.get("rechteSeite");
		
		PinnwandBox pinnwandBox = new PinnwandBox(Integer.parseInt(Cookies.getCookie("userId")));
		
		FlowPanel ganzeSeite = new FlowPanel();
		FlowPanel mittlereVier = new FlowPanel();
		
		
		Button testBtn = new Button("Cool");
		Button testBtn2 = new Button("Create");
		
		
		// GUI Elements
		Header h1 = new Header();
		h1.setLogOutURL(loginInfo.getLogoutUrl());
		
		// Creating ProfileBox
		ProfileBox pB = new ProfileBox();
		pB.setLogOutURL(loginInfo.getLogoutUrl());

		//linkeSeite.add(testBtn);
		//linkeSeite.add(testBtn2);
		
		
		rootPanelHeader.add(h1);
		rootProfilePanel.add(pB);
		rootPinnwandPanel.add(pinnwandBox);
	}
	
	/**
	 * <b>Nested Class einer Registrierungsform</b>
	 * 
	 * Abfrage ob der User sich registrieren moechte
	 */
	class RegistrationFormDialogBox extends DialogBox {
	
		/**
		 * Instantiierung der notwendigen GUI Objekte
		 */
		private Label abfrage = new Label("Du bist noch nicht registriert!"
				+ " Fülle dieses Formular aus, um deinen User anzulegen.");
		private Button yesBtn = new Button("Registrieren");
		private Button noBtn = new Button("Abbrechen");
		private VerticalPanel vPanel = new VerticalPanel();
		private HorizontalPanel btnPanel = new HorizontalPanel();
	
		/**
		 * Ein String der die E-Mail Adresse speichert
		 */
		private String googleMail = "";
		
		/**
		 * Aufruf des Konstruktors
		 * @param mail
		 */
		public RegistrationFormDialogBox(String mail) {
			// Adding Styles to Interaction Fields
			nickInput.addStyleName("control input content_margin");
			firstInput.addStyleName("control input content_margin");
			lastInput.addStyleName("control input content_margin");
			
			yesBtn.addStyleName("button bg-primary has-text-white");
			noBtn.addStyleName("button");
			
			abfrage.addStyleName("label has-text-primary content_margin");
			nickName.addStyleName("label has-text-primary content_margin");
			firstName.addStyleName("label has-text-primary content_margin");
			lastName.addStyleName("label has-text-primary content_margin");
			
			googleMail = mail;
			yesBtn.addClickHandler(new CreateUserClickHandler(this));
			noBtn.addClickHandler(new DontCreateUserClickHandler(this));
			vPanel.add(abfrage);
			vPanel.add(nickName);
			vPanel.add(nickInput);
			vPanel.add(firstName);
			vPanel.add(firstInput);
			vPanel.add(lastName);
			vPanel.add(lastInput);
			btnPanel.add(yesBtn);
			btnPanel.add(noBtn);
			vPanel.add(btnPanel);
			this.add(vPanel);
			this.setWidth("300px");
		}
	}
	
	/**
	 * Die Nested-Class <code>CreateUserClickHandler</code> implementiert das ClickHandler-Interface
	 * welches es ermöglicht auf den Wunsch des Users zu reagieren, dass er einen User anlegen möchte.
	 */
	private class CreateUserClickHandler implements ClickHandler {
		RegistrationFormDialogBox parentRFDB;
		
		public CreateUserClickHandler(RegistrationFormDialogBox rfdb) {
			this.parentRFDB = rfdb;
		}
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		@Override
		public void onClick(ClickEvent event) {
			pinnwandVerwaltung.createUser(firstInput.getText(), lastInput.getText(), nickInput.getText(), loginInfo.getEmailAddress(), timestamp, new CreateUserCallback(parentRFDB));
		}
		
	}
	
	/**
	 * Die Nested-Class <code>CreateUserCallback</code> implementiert den AsyncCallback, 
	 * welcher bei erfolgreicher Vollführung einen User zurückgibt.
	 */
	private class CreateUserCallback implements AsyncCallback<User> {
		RegistrationFormDialogBox parentRFDB;
		
		public CreateUserCallback(RegistrationFormDialogBox rfdb) {
			this.parentRFDB = rfdb;
		}
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		@Override
		public void onFailure(Throwable caught) {
		}

		/**
		 * Cookie Setzung für das gesamte System
		 */
		@Override
		public void onSuccess(User result) {
			Window.alert("Ihr Nutzer wurde angelegt");
			Cookies.setCookie("gmail", result.getGMail());
			Cookies.setCookie("userId", result.getUserId() + "");
			GWT.log("UserId created: " + result.getUserId());
			Cookies.setCookie("firstName", result.getFirstName());
			Cookies.setCookie("lastName", result.getLastName());
			Cookies.setCookie("nickName", result.getNickname());
			// hide(); - funktioniert nicht
			this.parentRFDB.hide();
			
			pinnwandVerwaltung.createPinnwand(result, timestamp, new CreatePinnwandCallback());
		}
		
	}
	
	/**
	 * Die Nested Class <code>CreatePinnwandCallback</code> implementiert den AsyncCallback, 
	 * welcher bei einem erfolgreichen durchführen eine Pinnwand zurückgibt.
	 */
	private class CreatePinnwandCallback implements AsyncCallback<Pinnwand> {

		@Override
		public void onFailure(Throwable caught) {
		}

		@Override
		public void onSuccess(Pinnwand result) {
			//loadEditor();
			loadPinnwand();
		}
		
	}
	
	/**
	 * Die Nested-Class <code>DontCreateUserClickHandler</code> implementiert das ClickHandler-Interface
	 * welches es ermöglicht das anlegen eines Users abbricht.
	 */
	private class DontCreateUserClickHandler implements ClickHandler {
		RegistrationFormDialogBox parentRFDB;
		
		public DontCreateUserClickHandler(RegistrationFormDialogBox rfdb) {
			this.parentRFDB = rfdb;
		}

		@Override
		public void onClick(ClickEvent event) {
			this.parentRFDB.hide();
		}
		
	}
		
	/**
	 * Die Nested-Class implementiert einen AsyncCallback, welcher bei einem erfolgreichen
	 * durchführen einen User zurückgibt.
	 */
	public class GetUserCallback implements AsyncCallback<User> {

		@Override
		public void onFailure(Throwable caught) {
			
		}

		@Override
		public void onSuccess(User result) {
			
			if(result != null) {
				//Window.alert("U1 is corrected: " + u1.toString());
				Cookies.setCookie("gmail", result.getGMail());
				Cookies.setCookie("userId", result.getUserId() + "");
				Cookies.setCookie("firstName", result.getFirstName());
				Cookies.setCookie("lastName", result.getLastName());
				Cookies.setCookie("nickName", result.getNickname());
				loadPinnwand();
			} else {
				RegistrationFormDialogBox dlgBox = new RegistrationFormDialogBox(loginInfo.getEmailAddress());
				dlgBox.center();
			}
		}
		
	}
	
	/**
	 * Die Nested-Class <code>GetPinnwandCallback</code> implementiert einen AsyncCallback,
	 * welcher bei erfolgreicher durchführung eine Pinnwand zurückgibt.
	 */
	public class GetPinnwandCallback implements AsyncCallback<Pinnwand> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert(caught.toString());
		}

		@Override
		public void onSuccess(Pinnwand result) {
			Window.alert("funnzt");
		}
		
	}
	
}
