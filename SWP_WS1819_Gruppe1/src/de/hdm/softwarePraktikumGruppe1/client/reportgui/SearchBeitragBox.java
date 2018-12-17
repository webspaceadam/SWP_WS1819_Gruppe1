package de.hdm.softwarePraktikumGruppe1.client.reportgui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * 
 * Mit dieser Box kann der User nach Beiträgen suchen
 * @author Jakob Benkö
 *
 */
public class SearchBeitragBox extends FlowPanel {
	private FlowPanel inputWrapper = new FlowPanel();
	private FlowPanel searchWrapper = new FlowPanel();
	
	private TextBox searchUserInput = new TextBox();
	private Button searchBtn = new Button("Suche!");
	
	
	public SearchBeitragBox() {
		this.addStyleName("grid_box box radiusless");
		inputWrapper.addStyleName("grid_box_element");
		searchWrapper.addStyleName("grid_box_element");
		searchUserInput.addStyleName("input radiusless");
		searchUserInput.getElement().setPropertyString("placeholder", "Suche nach Beiträgen!");
		searchBtn.addStyleName("button bg-primary has-text-white");
		
		inputWrapper.add(searchUserInput);
		searchWrapper.add(searchBtn);
		
		this.add(inputWrapper);
		this.add(searchWrapper);
	}
	
	public void onLoad() {
		
	}

}