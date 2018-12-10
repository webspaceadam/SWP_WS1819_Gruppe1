package de.hdm.softwarePraktikumGruppe1.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

/**
 * @author AdamGniady
 *
 */
public class AbonnementBox extends FlowPanel {
	private Label accountName = new Label("Sebastian Hermann");
	private Label nickName = new Label("@sebmeister");
	private Button pinnwandBtn = new Button("Pinnwand");
	private Button deaboBtn = new Button("Deabbonieren");
	
	private FlowPanel accountWrapper = new FlowPanel();
	private FlowPanel nickWrapper = new FlowPanel();
	private FlowPanel pinnwandWrapper = new FlowPanel();
	private FlowPanel deaboWrapper = new FlowPanel();
	
	public AbonnementBox() {
	}
	
	public AbonnementBox(int aboCount) {
		this.accountName.setText("Abonnement" + aboCount);
	}
	
	public void onLoad() {
		this.addStyleName("box grid_box radiusless");
		accountWrapper.addStyleName("box-item-ein-viertel");
		nickWrapper.addStyleName("box-item-ein-viertel");
		pinnwandWrapper.addStyleName("box-item-ein-viertel");
		deaboWrapper.addStyleName("box-item-ein-viertel");
		
		accountName.addStyleName("title is-size-4");
		nickName.addStyleName("is-size-5");
		pinnwandBtn.addStyleName("button bg-primary has-text-white");
		deaboBtn.addStyleName("button is-danger");
		
		accountWrapper.add(accountName);
		nickWrapper.add(nickName);
		pinnwandWrapper.add(pinnwandBtn);
		deaboWrapper.add(deaboBtn);
		
		// Adding ClickHandlers to Buttons
		pinnwandBtn.addClickHandler(new ShowPinnwandClickHandler());
		
		this.add(accountWrapper);
		this.add(nickWrapper);
		this.add(pinnwandWrapper);
		this.add(deaboWrapper);
	}
	
	private class ShowPinnwandClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			RootPanel rootPanelContainerPanel = RootPanel.get("container");
			rootPanelContainerPanel.clear();
			
			FlowPanel linkeSeite = new FlowPanel();
			ProfileBox newProfile = new ProfileBox("Adam Gniady", "javaGod");
			
			// Pinnwand Stuff
			Vector<BeitragBox> allBeitragBoxesOfPinnwand = new Vector<BeitragBox>();
			
			for(int i = 0; i < 10; i++) {
				BeitragBox tempBB = new BeitragBox();
				
				allBeitragBoxesOfPinnwand.add(tempBB);
			}
			
			PinnwandBox newPinnwand = new PinnwandBox(allBeitragBoxesOfPinnwand);
			
			
			
			linkeSeite.addStyleName("linkeSeite");
			
			linkeSeite.add(newProfile);
			
			rootPanelContainerPanel.add(linkeSeite);
			rootPanelContainerPanel.add(newPinnwand);
			
		}
		
	}

}
