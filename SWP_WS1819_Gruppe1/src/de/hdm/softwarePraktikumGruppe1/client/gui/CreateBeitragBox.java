package de.hdm.softwarePraktikumGruppe1.client.gui;

import com.google.gwt.user.client.ui.*;

/**
 * Die Klasse <code>CreateBeitragBox</code> enthält alle Elemente die zum Erstellen eines  
 * Beitrags benötigt werden. 
 *@author AdamGniady
 */

public class CreateBeitragBox extends FlowPanel {
	/*
	 * FlowPanels für die Box
	 */
	private FlowPanel parentWrapper = new FlowPanel();
	private FlowPanel contentWrapper = new FlowPanel();
	private FlowPanel btnWrapper = new FlowPanel();
	private FlowPanel btnWrapper2 = new FlowPanel();
	private FlowPanel btnWrapper3 = new FlowPanel();
	
	/**
	 * Widgets für die Interaktion
	 */
	private TextArea textArea = new TextArea();
	private Button submitBtn = new Button("Beitrag erstellen");
	
	/**
	 * Leerer Konstruktor
	 */
	public CreateBeitragBox() {
	}
	
	/**
	 * Innerhalb der <code>onLoad()</code>-Methode werden alle Stylings und die Zusammensetzung vollführt.
	 */
	public void onLoad() {
		this.addStyleName("post radiusless content_margin");
		parentWrapper.addStyleName("post_content");
		
		// Cpntent Wrapper
		contentWrapper.addStyleName("content_margin");
		textArea.addStyleName("control textarea is-fullwidth");
		textArea.getElement().setPropertyString("placeholder", "Erstelle einen neuen Beitrag!");
		contentWrapper.add(textArea);
		
		// Button Wrapper
		btnWrapper.addStyleName("grid_box");
		btnWrapper2.addStyleName("grid_box_links");
		btnWrapper3.addStyleName("grid_box_element");
		submitBtn.addStyleName("button bg-primary");
		
		btnWrapper3.add(submitBtn);
		btnWrapper2.add(btnWrapper3);
		btnWrapper.add(btnWrapper2);
		
		parentWrapper.add(contentWrapper);
		parentWrapper.add(btnWrapper);
		
		this.add(parentWrapper);
	}
}
