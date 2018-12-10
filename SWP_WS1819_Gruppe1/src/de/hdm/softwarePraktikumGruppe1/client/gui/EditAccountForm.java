package de.hdm.softwarePraktikumGruppe1.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

import de.hdm.softwarePraktikumGruppe1.client.gui.ProfileBox.EditProfileBoxDialogBox;

/**
 * @author AdamGniady
 *
 */
public class EditAccountForm extends FlowPanel {
	// Whole Wrappers
	private FlowPanel nickWrapper = new FlowPanel();
	private FlowPanel nameWrapper = new FlowPanel();
	private FlowPanel firstNameWrapper = new FlowPanel();
	
	private Label nickName = new Label("Nickname");
	private Label lastName = new Label("Nachname");
	private Label firstName = new Label("Vorname");
	
	private TextBox nickInput = new TextBox();
	private TextBox lastInput = new TextBox();
	private TextBox firstInput = new TextBox();
	private Button safeButton = new Button("Speichere den Edit");
	
	private ProfileBox parentPB;
	private EditProfileBoxDialogBox parentDialogBox;
	
	public EditAccountForm() {
	}
	
	public EditAccountForm(ProfileBox parentProfileBox, EditProfileBoxDialogBox parentDB) {
		this.parentPB = parentProfileBox;
		this.parentDialogBox = parentDB;
	}
	
	public void onLoad() {
		// Nickname
		nickWrapper.addStyleName("content_margin");
		nickName.addStyleName("label has-text-primary content_margin");
		nickInput.addStyleName("control input");
		nickInput.setWidth("300px");
		nickInput.getElement().setPropertyString("placeholder", "Dein Nickname");
		
		nickWrapper.add(nickName);
		nickWrapper.add(nickInput);
		
		// Nachname
		nameWrapper.addStyleName("content_margin");
		lastName.addStyleName("label has-text-primary content_margin");
		lastInput.addStyleName("control input");
		lastInput.setWidth("300px");
		lastInput.getElement().setPropertyString("placeholder", "Dein Nachname");
		
		nameWrapper.add(lastName);
		nameWrapper.add(lastInput);
		
		// Vorname
		firstNameWrapper.addStyleName("content_margin");
		firstName.addStyleName("label has-text-primary content_margin");
		firstInput.addStyleName("control input");
		firstInput.setWidth("300px");
		firstInput.getElement().setPropertyString("placeholder", "Dein Vorname");
		
		firstNameWrapper.add(firstName);
		firstNameWrapper.add(firstInput);
		
		safeButton.addStyleName("button bg-primary");
		safeButton.addClickHandler(new SafeNewNames());
		
		// Adding the Elements to the Form
		this.add(nickWrapper);
		this.add(firstNameWrapper);
		this.add(nameWrapper);
		this.add(safeButton);
	}
	
	private class SafeNewNames implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			String newNickname = nickInput.getValue();
			String newVorname = firstInput.getValue();
			String newNachname = lastInput.getValue();
			
			parentPB.setNickname(newNickname);
			parentPB.setAccountname(newVorname, newNachname);
			parentDialogBox.hide();
		}
		
	}
	
}
