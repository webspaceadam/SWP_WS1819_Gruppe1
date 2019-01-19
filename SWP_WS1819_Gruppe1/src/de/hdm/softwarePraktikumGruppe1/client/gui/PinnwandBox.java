package de.hdm.softwarePraktikumGruppe1.client.gui;

import java.sql.Timestamp;
import java.util.Vector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import de.hdm.softwarePraktikumGruppe1.client.ClientsideSettings;
import de.hdm.softwarePraktikumGruppe1.shared.PinnwandverwaltungAsync;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;
import de.hdm.softwarePraktikumGruppe1.shared.bo.User;

/**
 * The <code>PinnwandBox</code> is a class to display the 
 * Pinnwand of a User. It contains every <code>Beitrag</code> of the User. 
 * 
 * @author AdamGniady
 *
 */
public class PinnwandBox extends FlowPanel {
	// Pinnwandverwaltung
	PinnwandverwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandverwaltung();
	
	private Vector<BeitragBox> allBeitragBoxesOfPinnwand = new Vector<BeitragBox>();
	private Vector<Beitrag> allBeitraegeOfPinnwand = new Vector<Beitrag>();
	private FlowPanel createBeitragBox = new FlowPanel();
	
	// Elements to create a Beitrag
	private FlowPanel parentWrapper = new FlowPanel();
	private FlowPanel contentWrapper = new FlowPanel();
	private FlowPanel btnWrapper = new FlowPanel();
	private FlowPanel btnWrapper2 = new FlowPanel();
	private FlowPanel btnWrapper3 = new FlowPanel();
	
	private TextArea textArea = new TextArea();
	private Button submitBtn = new Button("Beitrag erstellen");
	
	// Additional Information
	private User user;
	private int userId;
	
	public PinnwandBox(int userId) {
		this.userId = userId;
	}
	
	public PinnwandBox(Vector<BeitragBox> pinnwandBeitragBoxes) {
		this.allBeitragBoxesOfPinnwand = pinnwandBeitragBoxes;
		
		for(int i = 0; i < allBeitragBoxesOfPinnwand.size(); i++) {
			this.add(allBeitragBoxesOfPinnwand.elementAt(i));
		}
	}
	
	public void onLoad() {
		int currentUserId = Integer.parseInt(Cookies.getCookie("userId"));
		//setCurrentUser();
		pinnwandVerwaltung.getUserById(userId, new GetUserByIdCallback());
		//pinnwandVerwaltung.getUserById(currentUserId, new GetUserByIdCallback());

		getOldBeitraege();
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
		
		if(this.userId == currentUserId) {
			createBeitragBox.add(parentWrapper);
			
			// Adding Elements to the Pinnwand
			this.add(createBeitragBox);
		} else {
			GWT.log("Pinnwand of:  " + userId);
		}
	}
	
	public BeitragBox createBeitrag(String content) {
		User currentUser = new User();
		currentUser.setUserId(Integer.parseInt(Cookies.getCookie("userId")));
		currentUser.setFirstName(Cookies.getCookie("firstName"));
		currentUser.setLastName(Cookies.getCookie("lastName"));
		currentUser.setNickname(Cookies.getCookie("nickName"));
		
		BeitragBox newBeitragBox = new BeitragBox(content, this, currentUser);
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
	
	public class GetUserByIdCallback implements AsyncCallback<User> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Problem with the Callback!");
			
		}

		@Override
		public void onSuccess(User result) {
			user = result;
			getOldBeitraege();
		}
		
	}
	
	private void getOldBeitraege() {
		pinnwandVerwaltung.getAllBeitraegeOfUser(this.user, new GetAllBeitraegeOfUser());
	}
	
	private class GetAllBeitraegeOfUser implements AsyncCallback<Vector<Beitrag>> {

		@Override
		public void onFailure(Throwable caught) {
			//Window.alert(caught.toString());
		}

		@Override
		public void onSuccess(Vector<Beitrag> result) {
			allBeitraegeOfPinnwand = result;
			//Window.alert("We hava all Beiträge now");
			addOldBeitraegeToPinnwand();
		}
	}
	
	/**
	 * Methode die alle Beiträge eines Users auf die jeweilige Pinnwand anheftet
	 */
	private void addOldBeitraegeToPinnwand() {
		for(Beitrag b : this.allBeitraegeOfPinnwand) {
			BeitragBox tempBeitragBox = new BeitragBox();
			tempBeitragBox.setAccountName(user.getFirstName(), user.getLastName());
			tempBeitragBox.setBeitragId(b.getBeitragId());
			GWT.log(b.getCreationTimeStamp().toString());
			tempBeitragBox.setCreationDate(b.getCreationTimeStamp().toString());
			tempBeitragBox.setBeitragContent(b.getInhalt());
			this.add(tempBeitragBox);
		}
	}
}
