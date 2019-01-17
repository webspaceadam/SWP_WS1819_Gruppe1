package de.hdm.softwarePraktikumGruppe1.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

import de.hdm.softwarePraktikumGruppe1.shared.bo.User;

/**
 * Die Klasse <code>SearchAboBox</code> zeigt alle möglichen Ergebnisse der Suche 
 * nach einem vom User eingegebenen Keyword.  
 * @author AdamGniady
 */
public class SearchAboBox extends FlowPanel {
	String firstName;
	String lastName;
	String fullName = firstName + " " + lastName;
	String nickName;
	
	private Label accountNameLabel = new Label(fullName);
	private Label nickNameLabel = new Label(nickName);
	//private Button pinnwandBtn = new Button("Pinnwand");
	private Button getAboBtn = new Button("Abonnieren");
	
	private FlowPanel accountWrapper = new FlowPanel();
	private FlowPanel nickWrapper = new FlowPanel();
	private FlowPanel pinnwandWrapper = new FlowPanel();
	private FlowPanel aboWrapper = new FlowPanel();
	
	public SearchAboBox(User u) {
		this.firstName=u.getFirstName();
		this.lastName=u.getLastName();
		this.nickName=u.getNickname();
	}
	
	public void onLoad() {
		this.addStyleName("box grid_box radiusless");
		
		accountWrapper.addStyleName("box-item-ein-viertel");
		nickWrapper.addStyleName("box-item-ein-viertel");
		pinnwandWrapper.addStyleName("box-item-ein-viertel");
		aboWrapper.addStyleName("box-item-ein-viertel");
		
		accountNameLabel.addStyleName("title is-size-3");
		nickNameLabel.addStyleName("is-size-4");
		//pinnwandBtn.addStyleName("button bg-primary has-text-white");
		getAboBtn.addStyleName("button");
		
		accountWrapper.add(accountNameLabel);
		nickWrapper.add(nickNameLabel);
		//pinnwandWrapper.add(pinnwandBtn);
		aboWrapper.add(getAboBtn);
		
		//pinnwandBtn.addClickHandler(new ShowPinnwandClickHandler());
		
		this.add(accountWrapper);
		this.add(nickWrapper);
//		this.add(pinnwandWrapper);
		this.add(aboWrapper);
	}
//	private class ShowPinnwandClickHandler implements ClickHandler {
//		@Override
//		public void onClick(ClickEvent event) {
//			RootPanel rootPanelContainerPanel = RootPanel.get("container");
//			rootPanelContainerPanel.clear();
//			
//			FlowPanel linkeSeite = new FlowPanel();
//			ProfileBox newProfile = new ProfileBox("Adam", "Gniady", "javaGod");
//			
//			// Pinnwand Stuff
//			Vector<BeitragBox> allBeitragBoxesOfPinnwand = new Vector<BeitragBox>();
//			
//			for(int i = 0; i < 10; i++) {
//				BeitragBox tempBB = new BeitragBox();
//				
//				allBeitragBoxesOfPinnwand.add(tempBB);
//			}
//			
//			PinnwandBox newPinnwand = new PinnwandBox(allBeitragBoxesOfPinnwand);
//			
//			linkeSeite.addStyleName("linkeSeite");
//			
//			linkeSeite.add(newProfile);
//			
//			rootPanelContainerPanel.add(linkeSeite);
//			rootPanelContainerPanel.add(newPinnwand);	
//		}	
//	}

}
