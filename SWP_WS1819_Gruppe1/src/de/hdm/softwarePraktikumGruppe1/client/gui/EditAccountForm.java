package de.hdm.softwarePraktikumGruppe1.client.gui;

import com.google.gwt.user.client.ui.*;

public class EditAccountForm extends FlowPanel {
	// Whole Wrappers
	private FlowPanel nickWrapper = new FlowPanel();
	private FlowPanel nameWrapper = new FlowPanel();
	private FlowPanel sureNameWrapper = new FlowPanel();
	private FlowPanel submitWrapper = new FlowPanel();
	
	private Label nickName = new Label("Nickname");
	private Label lastName = new Label("Nachname");
	private Label sureName = new Label("Vorname");
	private Button submitBtn = new Button("Speichern");
	
	private TextBox nickInput = new TextBox();
	private TextBox lastInput = new TextBox();
	private TextBox sureInput = new TextBox();
	
	public EditAccountForm() {
		// Nickname
		nickWrapper.addStyleName("content_margin");
		nickName.addStyleName("label has-text-primary");
		nickInput.addStyleName("control input");
		nickInput.getElement().setPropertyString("placeholder", "Dein Nickname");
		
		nickWrapper.add(nickName);
		nickWrapper.add(nickInput);
		
		// Nachname
		nameWrapper.addStyleName("content_margin");
		lastName.addStyleName("label has-text-primary");
		lastInput.addStyleName("control input");
		lastInput.getElement().setPropertyString("placeholder", "Dein Nachname");
		
		nameWrapper.add(lastName);
		nameWrapper.add(lastInput);
		
		// Vorname
		sureNameWrapper.addStyleName("content_margin");
		sureName.addStyleName("label has-text-primary");
		sureInput.addStyleName("control input");
		sureInput.getElement().setPropertyString("placeholder", "Dein Vorname");
		
		sureNameWrapper.add(sureName);
		sureNameWrapper.add(sureInput);
		
		// Button
		submitWrapper.addStyleName("content_margin");
		submitBtn.addStyleName("control button bg-primary");
		
		submitWrapper.add(submitBtn);
		
		
		
		this.add(nickWrapper);
		this.add(nameWrapper);
		this.add(sureNameWrapper);
		this.add(submitWrapper);
	}
	
	public void onLoad() {
		
	}
}
