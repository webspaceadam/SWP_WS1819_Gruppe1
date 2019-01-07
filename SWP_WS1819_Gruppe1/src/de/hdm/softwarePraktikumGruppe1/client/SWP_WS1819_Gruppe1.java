package de.hdm.softwarePraktikumGruppe1.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.softwarePraktikumGruppe1.client.gui.BeitragBox;
import de.hdm.softwarePraktikumGruppe1.client.gui.CreateBeitragBox;
import de.hdm.softwarePraktikumGruppe1.client.gui.Header;
import de.hdm.softwarePraktikumGruppe1.client.gui.KommentarBox;
import de.hdm.softwarePraktikumGruppe1.client.gui.PinnwandBox;
import de.hdm.softwarePraktikumGruppe1.client.gui.ProfileBox;
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
	User u1 = null;
	
	ProfileBox pB = new ProfileBox();
	
	
	public void onModuleLoad() {
		// RootPanels
		RootPanel rootPanelHeader = RootPanel.get("header");
		RootPanel rootPanelContainer = RootPanel.get("container");
		
		FlowPanel linkeSeite = new FlowPanel();
		PinnwandBox rechteSeite = new PinnwandBox();
		
		FlowPanel ganzeSeite = new FlowPanel();
		FlowPanel mittlereVier = new FlowPanel();
		
		linkeSeite.addStyleName("linkeSeite");
		ganzeSeite.addStyleName("ganzeSeite");
		mittlereVier.addStyleName("mittlereVier");
		
		Button testBtn = new Button("Cool");
		
		testBtn.addClickHandler(new TestClickHandler());
		
		
		// GUI Elements
		Header h1 = new Header();
		
		
		linkeSeite.add(pB);
		linkeSeite.add(testBtn);
		
		rootPanelHeader.add(h1);
		rootPanelContainer.add(linkeSeite);
		rootPanelContainer.add(rechteSeite);
	}
	
	public class TestClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			pinnwandVerwaltung.getUserById(1, new GetUserCallback());
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
			pB.setVorname(result.getFirstName());
			pB.setNachname(result.getLastName());
			pB.setNickname(result.getNickname());
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
