package de.hdm.softwarePraktikumGruppe1.client.gui;
import com.google.gwt.user.client.ui.*;

/**
 * Die <code>Header</code>-Klasse ist eine Custom-Widget-Class die daf�r verwendet wird, 
 * alle wichtigen Bereiche des Systems zu erreichen. 
 * 
 * @author AdamGniady
 * @version 1.0
 */

public class Header extends FlowPanel {
	
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
			
			inputDiv.add(searchUserInput);
			searchDiv.add(searchButton);
			logoutDiv.add(logoutButton);
			
			headerRight.add(inputDiv);
			headerRight.add(searchDiv);
			headerRight.add(logoutDiv);
			
			this.add(headerLogo);
			this.add(headerLinkList);
			this.add(headerRight);
			
		}

		
		/**
		 * In dieser Methode werden die Desings der Buttons festgelegt. Auch
		 * die Kontakt-Editor und ReportGenerator-Buttons werden zum Kopfbereich
		 * des Kontaktverwaltungstools hinzugef�gt. 
		 */
		public void onLoad() {
		}
}
