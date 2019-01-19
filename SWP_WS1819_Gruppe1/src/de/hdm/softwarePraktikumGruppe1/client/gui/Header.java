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
 * Die <code>Header</code>-Klasse ist eine Custom-Widget-Class die daf�r verwendet wird, 
 * alle wichtigen Bereiche des Systems zu erreichen. 
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
		Vector<User> pinnwandOwner = new Vector<User>();
	
		
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
		
		// Creating Buttons
		private TextBox searchUserInput = new TextBox();
		private Button searchButton = new Button("Suche!");
		private Button logoutButton = new Button("Logout");
		private Button reportButton = new Button("Report");
		
		
		// Create Images
		Image logo = new Image();
		
		
		// Create Anchors / Links
		private Anchor meinePinnwand = new Anchor("Meine Pinnwand");
		private Anchor meineAbos = new Anchor("Meine Abos");
	

		/**
		 * Im Konstruktor dieser Klasse werden die Buttons in die Panels
		 * und zu den Buttons die ClickHandler hinzugef�gt.
		 */
		public Header() {
		}

		
		/**
		 * In dieser Methode werden die Desings der Buttons festgelegt. Auch
		 * die Kontakt-Editor und ReportGenerator-Buttons werden zum Kopfbereich
		 * des Kontaktverwaltungstools hinzugef�gt. 
		 */
		public void onLoad() {
			pinnwandVerwaltung = ClientsideSettings.getPinnwandverwaltung();
			pinnwandVerwaltung.getUserById(currentUserId, new GetUserByIdCallback());
			
			// Add Styling to this Element
			this.addStyleName("header bg-primary");
			// Adding Stylenames to divs
			headerLogo.addStyleName("header_logo");
			headerLinkList.addStyleName("header_link_list");
			headerRight.addStyleName("header_right");
			headerLeft.addStyleName("header_left");
			
			//header element
			headerLogoDiv.addStyleName("header_element");
			
			logo.setUrl("/images/pinners_with_primary_white.png");
			
			// Adding Logo to header logo div
			headerLogoDiv.add(logo);
			headerLogo.add(headerLogoDiv);
			
			// Adding style to anchors
			meinePinnwand.addStyleName("header_element has-text-white");
			meineAbos.addStyleName("header_element has-text-white");
			
			// Adding Elements to parent divs
			headerLeft.add(meinePinnwand);
			headerLeft.add(meineAbos);
			headerLinkList.add(headerLeft);
			
			
			/*
			 * Buttons
			 */
			inputDiv.addStyleName("header_element");
			searchDiv.addStyleName("header_element");
			logoutDiv.addStyleName("header_element");
			
			searchUserInput.addStyleName("input is-medium");
			searchUserInput.getElement().setPropertyString("placeholder", "Suche nach Usern!");
			searchButton.addStyleName("button bg-primary");
			logoutButton.addStyleName("button bg-primary");
			reportButton.addStyleName("button bg-primary");
			
			inputDiv.add(searchUserInput);
			searchDiv.add(searchButton);
			logoutDiv.add(logoutButton);
			logoutDiv.add(reportButton);
			
			headerRight.add(inputDiv);
			headerRight.add(searchDiv);
			headerRight.add(logoutDiv);
			
			/*
			 * Logic to add the ClickHandlers
			 */
			
			
			
			searchButton.addClickHandler(new SearchUserClickHandler(this));
			meineAbos.addClickHandler(new ShowAbosClickHandler(this));
			meinePinnwand.addClickHandler(new ShowMyPinnwandClickHandler());
			logoutButton.addClickHandler(new LogoutClickHandler());
			
			
			this.add(headerLogo);
			this.add(headerLinkList);
			this.add(headerRight);
		}
		
		private class LogoutClickHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {
				
				//Leite User zum Google LogOut weiter
				Window.Location.assign(logOutURL);
			}
			
		}
		
		private class ShowMyPinnwandClickHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {
				RootPanel rootPinnwandPanel = RootPanel.get("rechteSeite");
				rootPinnwandPanel.clear();
				
				// Go to the Pinnwand of the current User
				PinnwandBox userPinnwand = new PinnwandBox(currentUserId);
				rootPinnwandPanel.add(userPinnwand);				
			}
			
		}
		
		private class ShowAbosClickHandler implements ClickHandler {
			public ShowAbosClickHandler(Header pH) {
				this.parentHeader = pH;
			}
			
			Header parentHeader;
			@Override
			public void onClick(ClickEvent event) {
				ShowAbosDialogBox dlg = new ShowAbosDialogBox(this.parentHeader);
				dlg.center();
			}
		}
		
		private class ShowAbosDialogBox extends DialogBox implements ClickHandler {
			Header parentHeader;
						
			private Vector<AbonnementBox> userAboBoxes = new Vector<AbonnementBox>();
									
			private ScrollPanel parentScrolling = new ScrollPanel();
			private FlowPanel aboParentPanel = new FlowPanel();
			
			public ShowAbosDialogBox(Header parentHeader) {
				this.parentHeader = parentHeader;
				
//				GWT.log(parentHeader.userAbonnements.toString());
//				GWT.log(parentHeader.aboPinnwaende.toString());
//				GWT.log(parentHeader.pinnwandOwner.toString());
				
				
				for(int i = 0; i < parentHeader.userAbonnements.size(); i++) {
					AbonnementBox tempAboBox = new AbonnementBox(parentHeader.userAbonnements.elementAt(i));
					userAboBoxes.add(tempAboBox);
				}
				
				for(int i = 0; i < userAboBoxes.size(); i++) {
					aboParentPanel.add(userAboBoxes.elementAt(i));
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

			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		}
		
		private class SearchUserClickHandler implements ClickHandler {
			private Header parentHeader;			
			
			public SearchUserClickHandler(Header parentHeader) {
				this.parentHeader = parentHeader;
			}

			@Override
			public void onClick(ClickEvent event) {
				pinnwandVerwaltung.searchFunction((parentHeader.searchUserInput.getValue()) , new SearchResultCallback());
			}
			
		}
		
		private class SearchUserDialogBox extends DialogBox implements ClickHandler {
			
			private ScrollPanel parentScrolling = new ScrollPanel();
			private FlowPanel aboParentPanel = new FlowPanel();
			private int ergebnisCounter;
			
			
			
			private Vector<SearchAboBox> searchResultBoxes = new Vector<SearchAboBox>();
			
			public SearchUserDialogBox(Vector<User> searchResults) {
				this.ergebnisCounter = searchResults.size();
				
				setText("Deine Suche ergab "+ergebnisCounter + " Treffer");
				//Methode zum auslesen der vectorgröße wird hier ausgeführt.
				
				// Vector searchResult befuellen. Es wird die Methode SearchFunction aufgerufen.
				// Die Methode SearchFunction gibt ein HashSet mit Usern zurueck. Die HashSet Collection 
				// wird dann in einen Vector konvertiert.
				
				
				for(User u: searchResults) {
					SearchAboBox singleUserBox = new SearchAboBox(u);
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

			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		}
		
		public class SearchResultCallback implements AsyncCallback<Vector<User>>{

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Shit");
				
			}

			@Override
			public void onSuccess(Vector<User> result) {
				if (result.size()>0) {
					//Window.alert("Deine Suche ergab " + result.size()+ " Treffer");
					SearchUserDialogBox dlg = new SearchUserDialogBox(result);
					dlg.center();
					
				}else {
					Window.alert("Deine Suche ergab 0 Treffer");
				}
				
				
			}

			
			
		}
		
		public class GetUserByIdCallback implements AsyncCallback<User> {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Problem with the Callback!");
				
			}

			@Override
			public void onSuccess(User result) {
				user = result;
				GWT.log("ID is: " + user.getUserId());
				pinnwandVerwaltung.showAllAbonnementsByUser(user, new ShowAllAbonnementsByUserCallback());
			}
			
		}
		
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
		
		public class GetPinnwandByIdCallback implements AsyncCallback<Pinnwand> {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
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
		
		public class GetOwnerOfAbonniertePinnwand implements AsyncCallback<User> {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Problem with Owner");
			}

			@Override
			public void onSuccess(User result) {
				User tempUser = result;
				
				GWT.log(tempUser.toString());
				pinnwandOwner.add(tempUser);
			}
		}
		
		public void setLogOutURL(String logOutURL) {
			this.logOutURL = logOutURL;
		}
}
