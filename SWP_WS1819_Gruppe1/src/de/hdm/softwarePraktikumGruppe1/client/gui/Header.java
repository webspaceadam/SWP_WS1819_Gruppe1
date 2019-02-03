package de.hdm.softwarePraktikumGruppe1.client.gui;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import de.hdm.softwarePraktikumGruppe1.client.ClientsideSettings;
import de.hdm.softwarePraktikumGruppe1.shared.PinnwandverwaltungAsync;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Abonnement;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Pinnwand;
import de.hdm.softwarePraktikumGruppe1.shared.bo.User;

/**
 * Die <code>Header</code>-Klasse ist eine Custom-Widget-Class die dafür verwendet wird, 
 * alle wichtigen Bereiche des Systems zu erreichen. Es dient quasi als Navigationspfad
 * für das System.
 * 
 * @author AdamGniady
 * @version 1.0
 */
public class Header extends FlowPanel {
		PinnwandverwaltungAsync pinnwandVerwaltung = null;
		String logOutURL;
		User user = null;
		int currentUserId = Integer.parseInt(Cookies.getCookie("userId"));
		
		Vector<Abonnement> userAbonnements = new Vector<Abonnement>();
		Vector<Pinnwand> aboPinnwaende = new Vector<Pinnwand>();
		User pinnwandOwner;
		
		// Create Header Divs 
		private FlowPanel headerLogo = new FlowPanel();
		private FlowPanel headerLinkList = new FlowPanel();
		private FlowPanel headerRight = new FlowPanel();
		private FlowPanel headerLeft = new FlowPanel(); 
		private FlowPanel headerLogoDiv = new FlowPanel();
		
		// Adding ParentDivs for Buttons
		private FlowPanel searchDiv = new FlowPanel();
		private FlowPanel logoutDiv = new FlowPanel();
		private FlowPanel inputDiv = new FlowPanel();
		private FlowPanel reportDiv = new FlowPanel();
		
		// Creating Buttons
		private TextBox searchUserInput = new TextBox();
		private Button searchButton = new Button("Suche!");
		private Button logoutButton = new Button("Logout");
		private Button reportButton = new Button("Report");
		DockPanel dock;
		ShowAbosDialogBox dlg;
		
		
		// Create Images
		Image logo = new Image();
		
		
		// Create Anchors / Links
		private Anchor meinePinnwand = new Anchor("Meine Pinnwand");
		private Anchor meineAbos = new Anchor("Meine Abos");
		
		/**
		 * 
		 * 
		 * @param a
		 */
		public void removeAbonnementBox(AbonnementBox a) {
			this.clear();
			pinnwandVerwaltung.showAllAbonnementsByUser(user, new ShowAllAbonnementsByUserCallback());
			
		}
	

		/**
		 * Konstruktor der Klasse
		 */
		public Header() {
		}

		
		/**
		 * Die Methode <code>onLoad()</code> wird beim Aufrufen der Klasse Header ausgeführt. 
		 * Hier werden die Widgets erst an die Klasse hinzugefügt.
		 */
		public void onLoad() {
			pinnwandVerwaltung = ClientsideSettings.getPinnwandverwaltung();
			pinnwandVerwaltung.getUserById(currentUserId, new GetUserByIdCallback());
			
			// Styling hinzufügen
			this.addStyleName("header bg-primary");
			
			// Styling an die Divs hinzufügen
			headerLogo.addStyleName("header_logo");
			headerLinkList.addStyleName("header_link_list");
			headerRight.addStyleName("header_right");
			headerLeft.addStyleName("header_left");
			
			// Styling hinzufügen
			headerLogoDiv.addStyleName("header_element");
			
			logo.setUrl("/images/pinners_with_primary_white.png");
			
			// Logo angepasst
			headerLogoDiv.add(logo);
			headerLogo.add(headerLogoDiv);
			
			// Styling der Anchors
			meinePinnwand.addStyleName("header_element has-text-white");
			meineAbos.addStyleName("header_element has-text-white");
			
			// Div zusammensetzung
			headerLeft.add(meinePinnwand);
			headerLeft.add(meineAbos);
			headerLinkList.add(headerLeft);
			
			
			/*
			 * Zuordnung an die Divs
			 */
			inputDiv.addStyleName("header_element");
			searchDiv.addStyleName("header_element");
			logoutDiv.addStyleName("header_element");
			reportDiv.addStyleName("header_element");
			
			searchUserInput.addStyleName("input is-medium");
			searchUserInput.getElement().setPropertyString("placeholder", "Suche nach Usern!");
			searchButton.addStyleName("button bg-primary");
			logoutButton.addStyleName("button bg-primary");
			reportButton.addStyleName("button bg-primary");
			
			inputDiv.add(searchUserInput);
			searchDiv.add(searchButton);
			logoutDiv.add(logoutButton);
			reportDiv.add(reportButton);
			
			headerRight.add(inputDiv);
			headerRight.add(searchDiv);
			headerRight.add(logoutDiv);
			headerRight.add(reportDiv);
			
			/**
			 * ClickHandler werden an die Button hinzugefügt
			 */
			searchButton.addClickHandler(new SearchUserClickHandler(this));
			meineAbos.addClickHandler(new ShowAbosClickHandler(this));
			meinePinnwand.addClickHandler(new ShowMyPinnwandClickHandler());
			logoutButton.addClickHandler(new LogoutClickHandler());
			reportButton.addClickHandler(new reportGeneratorClickHandler());
			
			
			this.add(headerLogo);
			this.add(headerLinkList);
			this.add(headerRight);
		}
		
		/**
		 * Die private Klasse LogoutClickHandler implementiert das ClickHandler-Interface und ermöglicht
		 * so bei der Interaktion des Users das ausloggen aus dem System.
		 * 
		 */
		private class LogoutClickHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {
				
				//Leite User zum Google LogOut weiter
				Window.Location.assign(logOutURL);
			}
			
		}
		
		/**
		 * Private Klasse die das ClickHandler-Interface implementiert und die beim Auslösen den 
		 * Wechsel zur Pinnwand des eingeloggten Users ermöglicht.
		 *
		 */
		private class ShowMyPinnwandClickHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {
				RootPanel rootPinnwandPanel = RootPanel.get("rechteSeite");
				rootPinnwandPanel.clear();
				
				// Hier wird man zur Pinnwand des eingeloggten Users weitergeleitet
				PinnwandBox userPinnwand = new PinnwandBox(currentUserId);
				rootPinnwandPanel.add(userPinnwand);				
			}
			
		}
		
		/**
		 * Private Klasse die das ClickHandler-Interface implementiert und die beim Auslösen alle
		 * Abonnements des Users anzeigt. 
		 * Fortführend öffnet der Klick auch die DialogBox <code>ShowAbosDialogBox</code>
		 * 
		 */
		private class ShowAbosClickHandler implements ClickHandler {
			public ShowAbosClickHandler(Header pH) {
				this.parentHeader = pH;
			}
			
			Header parentHeader;
			@Override
			public void onClick(ClickEvent event) {
				pinnwandVerwaltung.showAllAbonnementsByUser(user, new ShowAllAbonnementsByUserCallback());
				
				dlg = new ShowAbosDialogBox();
				dlg.center();
				
			}
		}
		
		/**
		 * Die private Klasse <em>reportGeneratorClickHandler</em> ermöglicht das Aufrufen 
		 * des ReportGenerators über den Header.
		 */
		private class reportGeneratorClickHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {
				
				Window.Location.assign(GWT.getHostPageBaseURL() + "ReportGenerator.html");
			}
			
		}
		
		/**
		 * Die Klasse ShowAbosDialogBox erbt von der Parentklasse <em>DialogBox</em> welche 
		 * ihm die nötige Funktionalität liefert und implementiert das ClickHandler Interface,
		 * um Interaktion zu ermöglichen
		 * 
		 * @author AdamGniady
		 *
		 */
		public class ShowAbosDialogBox extends DialogBox implements ClickHandler {
			//			Header parentHeader;
						
			private Vector<AbonnementBox> userAboBoxes = new Vector<AbonnementBox>();
									
			private ScrollPanel parentScrolling = new ScrollPanel();
			private FlowPanel aboParentPanel = new FlowPanel();
			private Label noAbosLabel = new Label("Momentan hast du keine Abonnements! Reloade ggf. das System!");
			private Label newAbosLabel = new Label("Hast du eben ein neues Abo hinzugefügt und es wird nicht angezeigt? Reloade ggf. das System!");
			private Button reloadSiteBtn = new Button("Reload!");
			
			/**
			 * Der Konstruktor der DialogBox-Klasse <em>ShowAbosDialogBox</em> 
			 * wird immer dann ausgeführt, wenn die DialogBox aufgerufen wird. 
			 * Sie sucht alle aktuellen Abonnements und baut daraus die AbonnementBoxen,
			 * welche das Aufrufen der Pinnwand des Abonnements ermöglichen.
			 */
			public ShowAbosDialogBox() {				
				for(Abonnement a: userAbonnements) {
					AbonnementBox tempAboBox = new AbonnementBox(this, pinnwandOwner, a);
					
					userAboBoxes.add(tempAboBox);
				}
				
				if(userAboBoxes.size() > 0) {
					for(int i = 0; i < userAboBoxes.size(); i++) {
						aboParentPanel.add(userAboBoxes.elementAt(i));
					}
					newAbosLabel.addStyleName("label has-text-primary content_margin");
					reloadSiteBtn.addStyleName("button bg-primary has-text-white");
					reloadSiteBtn.addClickHandler(new ForceReloadClickHandler());
					
					aboParentPanel.add(newAbosLabel);
					aboParentPanel.add(reloadSiteBtn);
					
				} else {
					noAbosLabel.addStyleName("label has-text-primary content_margin");
					reloadSiteBtn.addClickHandler(new ForceReloadClickHandler());
					reloadSiteBtn.addStyleName("button bg-primary has-text-white");
					aboParentPanel.add(noAbosLabel);
					aboParentPanel.add(reloadSiteBtn);
				}
								
				parentScrolling.add(aboParentPanel);
				parentScrolling.setSize("800px", "400px");
				
				Image cancelImage = new Image("images/SVG/timesCircle.png");
				cancelImage.getElement().setPropertyString("style", "max-width: 25px;");
				cancelImage.addClickHandler(this);

				dock = new DockPanel();
				dock.setSpacing(6);
				dock.add(parentScrolling, DockPanel.CENTER);
				dock.add(cancelImage, DockPanel.EAST);
				
				//safeButton.addClickHandler();
				//dock.setCellHorizontalAlignment(safeButton, DockPanel.ALIGN_CENTER);
				dock.setWidth("900px");
				dock.setHeight("400px");
				setWidget(dock);
			}

			/**
			 * Methode die das Schließen ermöglicht, da onClick vom User die Anfrage dafür kommt.
			 */
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
			
			/**
			 * Die private Klasse <em>ForceReloadClickHandler</em> ermöglicht das Reloaden des Systems,
			 * sofern der User dies möchte. Dies passiert vor Allem dann, wenn ein neu angelegtes Abonnement
			 * nicht richtig angezeigt wird. 
			 */
			private class ForceReloadClickHandler implements ClickHandler {

				@Override
				public void onClick(ClickEvent event) {
					forceReload();
				}
				
			}
			
			/**
			 * Die Methode forceReload ermöglicht erst die wirkliche Funktionalität der <code>ForceReloadClickHandler</code>-Klasse.
			 */
			public native void forceReload() /*-{
    			$wnd.location.reload(true);
  			}-*/;
		}
		
		/**
		 * Die private Klasse <em>SearchUserClickHandler</em> implementiert das ClickHandler-Interface
		 * und ermöglicht so den Start der Suchfunktion des Systems.
		 */
		private class SearchUserClickHandler implements ClickHandler {
			private Header parentHeader;			
			
			/**
			 * Konstruktor der den ParentHeader speichert, damit man auf dessen Werte zugreifen kann.
			 * @param parentHeader
			 */
			public SearchUserClickHandler(Header parentHeader) {
				this.parentHeader = parentHeader;
			}

			/**
			 * Das Event löst die Funktion searchFunction aus, die den Suchbegriff aus dem Input-Feld 
			 * des <code>Header</code> entnimmt.
			 */
			@Override
			public void onClick(ClickEvent event) {
				pinnwandVerwaltung.searchFunction((parentHeader.searchUserInput.getValue()) , new SearchResultCallback());
			}
			
			
		}
		
		/**
		 * Die private Klasse <em>SearchUserDialogBox</em> erbt von der <code>DialogBox</code> und 
		 * implementiert das ClickHandler-Interface. Diese DialogBox ermöglicht das Anzeigen von Suchanfragen
		 * des Users. 
		 */
		private class SearchUserDialogBox extends DialogBox implements ClickHandler {
			
			private ScrollPanel parentScrolling = new ScrollPanel();
			private FlowPanel aboParentPanel = new FlowPanel();
			private int ergebnisCounter;
			
			private Vector<SearchAboBox> searchResultBoxes = new Vector<SearchAboBox>();
			
			public SearchUserDialogBox(Vector<User> searchResults) {
				this.ergebnisCounter = searchResults.size();
				
				setText("Deine Suche ergab "+ergebnisCounter + " Treffer");
				/**
				 * Methode zum auslesen der vectorgröße wird hier ausgeführt.
				
					Vector searchResult befuellen. Es wird die Methode SearchFunction aufgerufen.
					Die Methode SearchFunction gibt ein HashSet mit Usern zurueck. Die HashSet Collection 
					wird dann in einen Vector konvertiert.
				 */				
				for(User u: searchResults) {
					SearchAboBox singleUserBox = new SearchAboBox(user, u);
					searchResultBoxes.add(singleUserBox);
				}
				
				for(SearchAboBox s: searchResultBoxes) {
					aboParentPanel.add(s);
				}
				
				
				parentScrolling.add(aboParentPanel);
				parentScrolling.setSize("800px", "400px");
				
				
				Image cancelImage = new Image("images/SVG/timesCircle.png");
				cancelImage.getElement().setPropertyString("style", "max-width: 25px;");
				cancelImage.addClickHandler(this);

				DockPanel dock = new DockPanel();
				dock.setSpacing(6);
				dock.add(parentScrolling, DockPanel.CENTER);
				dock.add(cancelImage, DockPanel.EAST);
				
				//safeButton.addClickHandler();

				//dock.setCellHorizontalAlignment(safeButton, DockPanel.ALIGN_CENTER);
				dock.setWidth("900px");
				dock.setHeight("400px");
				setWidget(dock);
			}

			/**
			 * Beim Klick wird die DialogBox durch diese Methode geschlossen.
			 */
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		}
		
		/**
		 * Die private Klasse SearchResultCallback implementiert einen AsyncCallback, 
		 * der die Kommunikation mit der Applikationslogik ermöglicht. Bei einem erfolgreichen
		 * Aufruf erhält man einen Vektor mit Usern die der Suche entsprechen.
		 */
		public class SearchResultCallback implements AsyncCallback<Vector<User>>{

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler beim SearchResultCallback");
			}

			@Override
			public void onSuccess(Vector<User> result) {
				if (result.size()>0) {
					//Window.alert("Deine Suche ergab " + result.size()+ " Treffer");
					SearchUserDialogBox dlg = new SearchUserDialogBox(result);
					dlg.center();
				} else {
					Window.alert("Deine Suche ergab 0 Treffer");
				}	
			}
		}
		
		/**
		 * Die private Klasse GetUserByIdCallback implementiert einen AsyncCallback, 
		 * der die Kommunikation mit der Applikationslogik ermöglicht. Bei einem Erfolgreichen Aufruf
		 * erhält der Client den User.
		 */
		public class GetUserByIdCallback implements AsyncCallback<User> {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Problem with the Callback!");
				
			}

			@Override
			public void onSuccess(User result) {
				user = result;
				GWT.log("ID is: " + user.getUserId());
				//Clickhandler zu Button zuweisen
				
				pinnwandVerwaltung.showAllAbonnementsByUser(user, new ShowAllAbonnementsByUserCallback());
			}
			
		}
		
		/**
		 * Die private Klasse ShowAllAbonnementsByUserCallback implementiert einen AsyncCallback, 
		 * der die Kommunikation mit der Applikationslogik ermöglicht. Bei einem Erfolgreichen Aufruf
		 * wird ein Vektor mit Abonnements geschickt. 
		 */
		public class ShowAllAbonnementsByUserCallback implements AsyncCallback<Vector<Abonnement>> {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Problem with the ShowAllAboCallback()");
				
			}

			@Override
			public void onSuccess(Vector<Abonnement> result) {
				userAbonnements = result;
				for (Abonnement abonnement : userAbonnements) {
					GWT.log(abonnement.toString());
					
					pinnwandVerwaltung.getPinnwandById(abonnement.getPinnwandId(), new GetPinnwandByIdCallback());
				}
			}
			
		}
		
		/**
		 * Die private Klasse GetPinnwandByIdCallback implementiert einen AsyncCallback, 
		 * der die Kommunikation mit der Applikationslogik ermöglicht. Bei einem Erfolgreichen Aufruf
		 * wird die gesucht Pinnwand zurückgegeben. 
		 */
		public class GetPinnwandByIdCallback implements AsyncCallback<Pinnwand> {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Problem with Pinnwand");
			}

			@Override
			public void onSuccess(Pinnwand result) {
				Pinnwand tempPinnwand = result;
				GWT.log("Current Pinnwand "  +tempPinnwand.getPinnwandId());
				aboPinnwaende.add(tempPinnwand);
				pinnwandVerwaltung.getUserById(tempPinnwand.getOwnerId(), new GetOwnerOfAbonniertePinnwand());
			}
			
		}
		
		/**
		 * Die private Klasse GetOwnerOfAbonniertePinnwand implementiert einen AsyncCallback, 
		 * der die Kommunikation mit der Applikationslogik ermöglicht. Bei einem Erfolgreichen Aufruf
		 * wird der Owner der Pinnwand zurückgegeben.
		 */
		public class GetOwnerOfAbonniertePinnwand implements AsyncCallback<User> {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Problem with Owner");
			}

			@Override
			public void onSuccess(User result) {
				pinnwandOwner = result;
				
				GWT.log(pinnwandOwner.toString());
				
			}
		}
		
		/**
		 * Die Methode setzt die Url für den Logout.
		 * 
		 * @param logOutURL
		 */
		public void setLogOutURL(String logOutURL) {
			this.logOutURL = logOutURL;
		}
}
