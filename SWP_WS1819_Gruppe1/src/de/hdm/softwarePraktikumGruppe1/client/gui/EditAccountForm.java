package de.hdm.softwarePraktikumGruppe1.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import de.hdm.softwarePraktikumGruppe1.client.ClientsideSettings;
import de.hdm.softwarePraktikumGruppe1.client.gui.ProfileBox.EditProfileBoxDialogBox;
import de.hdm.softwarePraktikumGruppe1.shared.PinnwandverwaltungAsync;
import de.hdm.softwarePraktikumGruppe1.shared.bo.User;

/**
 * Die Klasse <code>EditAccountForm</code> wird für die Änderung der Account-Daten
 * eines eingeloggten Users genutzt. 
 * @author AdamGniady
 */
public class EditAccountForm extends FlowPanel {
	// User und Verwaltung des Systems
	User user = null;
	PinnwandverwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandverwaltung();
	
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
	private Button safeButton = new Button("Speichern");
	
	private ProfileBox parentPB;
	private EditProfileBoxDialogBox parentDialogBox;
	
	public EditAccountForm() {
	}
	
	/**
	 * Der Konstruktor der <code>EditAccountForm</code>-Klasse speichert die übergeordnete 
	 * <em>parentProfileBox</em> und die dazugehörige <em>DialogBox</em>. 
	 * @param parentProfileBox
	 * @param parentDB
	 * 
	 */
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
		nickInput.getElement().setPropertyString("placeholder", "Dein aktueller Nickname: " + parentPB.getNickname());
		nickInput.setValue(parentPB.getNickname());
		
		nickWrapper.add(nickName);
		nickWrapper.add(nickInput);
		
		// Nachname
		nameWrapper.addStyleName("content_margin");
		lastName.addStyleName("label has-text-primary content_margin");
		lastInput.addStyleName("control input");
		lastInput.setWidth("300px");
		lastInput.getElement().setPropertyString("placeholder", "Dein Nachname");
		lastInput.setValue(parentPB.getNachname());
		
		nameWrapper.add(lastName);
		nameWrapper.add(lastInput);
		
		// Vorname
		firstNameWrapper.addStyleName("content_margin");
		firstName.addStyleName("label has-text-primary content_margin");
		firstInput.addStyleName("control input");
		firstInput.setWidth("300px");
		firstInput.getElement().setPropertyString("placeholder", "Dein Vorname");
		firstInput.setValue(parentPB.getVorname());
		
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
	
	/**
	 * Die innere Klasse <code>SafeNewNames</code> implementiert das ClickHandler Interface
	 * um die Speicherung der neu eingegebenen Namen zu speichern. 
	 * 
	 * @author AdamGniady
	 */
	private class SafeNewNames implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			int currentUserId = Integer.parseInt(Cookies.getCookie("userId"));
			pinnwandVerwaltung.getUserById(currentUserId, new GetUserByIdCallback());
			
			String newNickname = nickInput.getValue();
			String newVorname = firstInput.getValue();
			String newNachname = lastInput.getValue();
			
			// Check if Input for Nickname is empty
			if(newNickname.isEmpty()) {
				Window.alert("'Nickname' is empty!");
			} else {
				parentPB.setNickname(newNickname);
				user.setNickname(newNickname);
			}
			
			// Check if Input for Vorname is empty
			if(newVorname.isEmpty()) {
				Window.alert("'Vorname' is empty!");
			} else {
				parentPB.setVorname(newVorname);
				user.setFirstName(newVorname);
			}
			
			// Check if Input for Nickname is empty
			if(newNachname.isEmpty()) {
				Window.alert("'Nachname' is empty!");
			} else {
				parentPB.setNachname(newNachname);
				user.setLastName(newNachname);
			}
			
			pinnwandVerwaltung.editUser(user, new EditUserCallback());
			
			parentDialogBox.hide();
			
		}
		
	}
	
	/**
	 * Die innere Klasse GetUserByIdCallback implementiert das AsyncCallback Interface,
	 * das nötig ist um einen korrekten RPC-Aufruf zu ermöglichen.
	 * 
	 * @author AdamGniady
	 *
	 */
	private class GetUserByIdCallback implements AsyncCallback<User> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Problem with the Callback!" + "\n " + caught.toString());
			
		}

		@Override
		public void onSuccess(User result) {
			user = result;
		}
		
	}
	
	private class EditUserCallback implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("EditUserCallbackFailed" + "\n " + caught.toString());
		}

		@Override
		public void onSuccess(Void result) {
			GWT.log("User was edited");
		}
		
	}
	
}
