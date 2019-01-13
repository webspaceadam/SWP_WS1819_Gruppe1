package de.hdm.softwarePraktikumGruppe1.client.gui;

import com.google.gwt.user.client.ui.*;

/**
 * Die Klasse <code>SearchBox</code> ermöglicht die Suche nach Usern. 
 * 
 * @author AdamGniady
 *
 */
public class SearchBox extends FlowPanel {
	private FlowPanel inputWrapper = new FlowPanel();
	private FlowPanel searchWrapper = new FlowPanel();
	
	private TextBox searchUserInput = new TextBox();
	private Button searchBtn = new Button("Suche!");
	
	
	public SearchBox() {
		this.addStyleName("grid_box box radiusless");
		inputWrapper.addStyleName("grid_box_element");
		searchWrapper.addStyleName("grid_box_element");
		searchUserInput.addStyleName("input radiusless");
		searchUserInput.getElement().setPropertyString("placeholder", "Suche nach Usern!");
		searchBtn.addStyleName("button bg-primary has-text-white");
		
		inputWrapper.add(searchUserInput);
		searchWrapper.add(searchBtn);
		
		this.add(inputWrapper);
		this.add(searchWrapper);
	}
	
	public void onLoad() {
		
	}
	
	
	public String getUser() {
		return searchUserInput.getText();
	}

}
