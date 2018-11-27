package de.hdm.softwarePraktikumGruppe1.client.gui;

import com.google.gwt.user.client.ui.*;

/**
 * @author AdamGniady
 *
 */
public class SearchAboBox extends FlowPanel {
	private Label accountName = new Label("Gianluca Bernert");
	private Label nickName = new Label("@derLucker");
	private Button pinnwandBtn = new Button("Pinnwand");
	private Button getAboBtn = new Button("Abonnieren");
	
	private FlowPanel accountWrapper = new FlowPanel();
	private FlowPanel nickWrapper = new FlowPanel();
	private FlowPanel pinnwandWrapper = new FlowPanel();
	private FlowPanel aboWrapper = new FlowPanel();
	
	public SearchAboBox() {
		this.addStyleName("box grid_box radiusless");
		
		accountWrapper.addStyleName("box-item-ein-viertel");
		nickWrapper.addStyleName("box-item-ein-viertel");
		pinnwandWrapper.addStyleName("box-item-ein-viertel");
		aboWrapper.addStyleName("box-item-ein-viertel");
		
		accountName.addStyleName("title is-size-3");
		nickName.addStyleName("is-size-4");
		pinnwandBtn.addStyleName("button bg-primary has-text-white");
		getAboBtn.addStyleName("button");
		
		accountWrapper.add(accountName);
		nickWrapper.add(nickName);
		pinnwandWrapper.add(pinnwandBtn);
		aboWrapper.add(getAboBtn);
		
		this.add(accountWrapper);
		this.add(nickWrapper);
		this.add(pinnwandWrapper);
		this.add(aboWrapper);
		
	}
	
	public void onLoad() {
		
	}

}
