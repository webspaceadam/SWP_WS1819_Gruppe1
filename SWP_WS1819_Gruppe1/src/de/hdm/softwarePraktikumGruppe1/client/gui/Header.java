package de.hdm.softwarePraktikumGruppe1.client.gui;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

import de.hdm.softwarePraktikumGruppe1.client.gui.ProfileBox.EditProfileBoxDialogBox;

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
		}

		
		/**
		 * In dieser Methode werden die Desings der Buttons festgelegt. Auch
		 * die Kontakt-Editor und ReportGenerator-Buttons werden zum Kopfbereich
		 * des Kontaktverwaltungstools hinzugef�gt. 
		 */
		public void onLoad() {
			
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
			
			/*
			 * Logik to add the ClickHandlers
			 */
			
			meineAbos.addClickHandler(new ShowAbosClickHandler());
			
			this.add(headerLogo);
			this.add(headerLinkList);
			this.add(headerRight);
		}
		
		private class ShowAbosClickHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {
				ShowAbosDialogBox dlg = new ShowAbosDialogBox();
				dlg.center();
			}
			
		}
		
		private class ShowAbosDialogBox extends DialogBox implements ClickHandler {
			private Vector<AbonnementBox> userAbos = new Vector<AbonnementBox>();
			
			private ScrollPanel parentScrolling = new ScrollPanel();
			private FlowPanel aboParentPanel = new FlowPanel();
			
			
			
			public ShowAbosDialogBox() {
				
				for(int i = 0; i < 20; i++) {
					AbonnementBox tempAboBox = new AbonnementBox(i);
					userAbos.add(i, tempAboBox);
				}
				
				for(int i = 0; i < userAbos.size(); i++) {
					aboParentPanel.add(userAbos.elementAt(i));
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
}
