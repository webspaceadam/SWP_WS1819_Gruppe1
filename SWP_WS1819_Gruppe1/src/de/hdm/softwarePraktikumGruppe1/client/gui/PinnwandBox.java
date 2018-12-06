package de.hdm.softwarePraktikumGruppe1.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

/**
 * The <code>PinnwandBox</code> is a class to display the 
 * Pinnwand of a User. It contains every <code>Beitrag</code> of the User. 
 * 
 * @author AdamGniady
 *
 */
public class PinnwandBox extends FlowPanel {
	private Vector<BeitragBox> allBeitragBoxesOfPinnwand = new Vector<BeitragBox>();
	private FlowPanel createBeitragBox = new FlowPanel();
	
	
	// Elements to create a Beitrag
	private FlowPanel parentWrapper = new FlowPanel();
	private FlowPanel contentWrapper = new FlowPanel();
	private FlowPanel btnWrapper = new FlowPanel();
	private FlowPanel btnWrapper2 = new FlowPanel();
	private FlowPanel btnWrapper3 = new FlowPanel();
	
	private TextArea textArea = new TextArea();
	private Button submitBtn = new Button("Beitrag erstellen");
	
	
	public PinnwandBox() {
		
	}
	
	public void onLoad() {
		this.addStyleName("rechteSeite");
		
		// Creating the create box
		createBeitragBox.addStyleName("post radiusless content_margin");
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
		submitBtn.addClickHandler(new addBeitragToPinnwand());
		
		btnWrapper3.add(submitBtn);
		btnWrapper2.add(btnWrapper3);
		btnWrapper.add(btnWrapper2);
		
		parentWrapper.add(contentWrapper);
		parentWrapper.add(btnWrapper);
		
		createBeitragBox.add(parentWrapper);
		
		// Adding Elements to the Pinnwand
		this.add(createBeitragBox);
	}
	
	public BeitragBox createBeitrag(String content) {
		BeitragBox newBeitragBox = new BeitragBox(content, this);
		allBeitragBoxesOfPinnwand.add(newBeitragBox);
		this.add(allBeitragBoxesOfPinnwand.lastElement());
		
		return newBeitragBox;
	}
	
	private class addBeitragToPinnwand implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			String beitragContent = textArea.getValue();
			createBeitrag(beitragContent);
			textArea.setText("");
		}
		
	}
	
	public void deleteBeitrag(BeitragBox deletableBB) {
		deletableBB.removeFromParent();
		allBeitragBoxesOfPinnwand.removeElement(deletableBB);
	}
}
