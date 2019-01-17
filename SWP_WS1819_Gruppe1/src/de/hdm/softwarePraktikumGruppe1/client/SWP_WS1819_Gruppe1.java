package de.hdm.softwarePraktikumGruppe1.client;

import java.sql.Timestamp;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.softwarePraktikumGruppe1.client.gui.AuthenticationForm;
import de.hdm.softwarePraktikumGruppe1.client.gui.BeitragBox;
import de.hdm.softwarePraktikumGruppe1.client.gui.CreateBeitragBox;
import de.hdm.softwarePraktikumGruppe1.client.gui.Header;
import de.hdm.softwarePraktikumGruppe1.client.gui.KommentarBox;
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
		Window.alert("Eingeloggt mit der mail " + loginInfo.getEmailAddress());
		pinnwandVerwaltung.getUserByGmail(loginInfo.getEmailAddress(), new GetUserCallback());
		//pinnwandVerwaltung.getUserById(1, new GetUserCallback());

		// RootPanels
		RootPanel rootPanelHeader = RootPanel.get("header");
		RootPanel rootPanelContainer = RootPanel.get("container");
		RootPanel rootProfilePanel = RootPanel.get("linkeSeite");
		RootPanel rootPinnwandPanel = RootPanel.get("rechteSeite");
		
		PinnwandBox pinnwandBox = new PinnwandBox(1);
		
		FlowPanel ganzeSeite = new FlowPanel();
		FlowPanel mittlereVier = new FlowPanel();
		
		
		Button testBtn = new Button("Cool");
		Button testBtn2 = new Button("Create");
		
		testBtn.addClickHandler(new TestClickHandler());
		testBtn.addClickHandler(new TestZwei());
		
		
		// GUI Elements
		Header h1 = new Header();
		h1.setLogOutURL(loginInfo.getLogoutUrl());
		
		// Creating ProfileBox
		ProfileBox pB = new ProfileBox();

		//linkeSeite.add(testBtn);
		//linkeSeite.add(testBtn2);
		
		
		rootPanelHeader.add(h1);
		rootProfilePanel.add(pB);
		rootPinnwandPanel.add(pinnwandBox);
	}
	
	
	
	public class TestClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			pinnwandVerwaltung.getUserById(1, new GetUserCallback());
		}
		
	}
	
	public class TestZwei implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			pinnwandVerwaltung.createUser("Seb", "Hermi", "derHermi", "hermi@gmail.com", new Timestamp(System.currentTimeMillis()), new CreateUserCallback());
		}
		
	}
	
	public class CreateUserCallback implements AsyncCallback<User> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fail!" + caught.getMessage());
		}

		@Override
		public void onSuccess(User result) {
			Window.alert("Worked! Neuer User: " + result.toString());
		}
	}
	
	public class GetUserCallback implements AsyncCallback<User> {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(User result) {
			// TODO Auto-generated method stub
			u1 = result;
			//Window.alert("U1 is corrected: " + u1.toString());
		}
		
	}
	
	public class GetPinnwandCallback implements AsyncCallback<Pinnwand> {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			Window.alert(caught.toString());
		}

		@Override
		public void onSuccess(Pinnwand result) {
			// TODO Auto-generated method stub
			Window.alert("funnzt");
		}
		
	}
	
}
