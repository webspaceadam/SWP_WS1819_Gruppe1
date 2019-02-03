package de.hdm.softwarePraktikumGruppe1.client.reportgui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * Die <code>generatorBox</code>-Klasse ist eine Custom-Widget-Class die 
 * dem User die Möglichkeit gibt, die Erstellung eines Reports anzustoßen.
 * 
 *   
 * @author JakobBenkoe
 * @version 1.0
 */
public class GeneratorBox extends FlowPanel {
	private FlowPanel searchWrapper = new FlowPanel();
	

	private Button searchBtn = new Button("Report Generieren!");
	
	
	public GeneratorBox() {
		//this.addStyleName("grid_box box radiusless");
		searchWrapper.addStyleName("grid_box_element");
		searchBtn.addStyleName("button bg-primary has-text-white");
		
		searchWrapper.add(searchBtn);
		
		this.add(searchWrapper);
	}
	
	public void onLoad() {
		
	}
	
	/**
	 * Methode um den Such-Button zurückzugeben
	 * @return searchBtn
	 */
	
	public Button getButton() {
		return searchBtn;
	}

}