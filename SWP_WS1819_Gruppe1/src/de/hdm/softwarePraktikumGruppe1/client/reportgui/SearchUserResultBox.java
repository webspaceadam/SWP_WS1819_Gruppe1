package de.hdm.softwarePraktikumGruppe1.client.reportgui;



import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;


import de.hdm.softwarePraktikumGruppe1.shared.bo.User;




public class SearchUserResultBox extends FlowPanel {
	TextBox searchUserInput;

	private User resultUser;
	private String accountNameOfShownUser;
	private String nicknameOfShownUser;

	
	private Label accountNameLabel;
	private Label nicknameLabel;
	
	private FlowPanel accountWrapper;
	private FlowPanel nickWrapper;
	private FlowPanel aboWrapper;
	
	
	Button choseUserBtn = new Button("Auswählen");

	
	/*
	 * Konstruktor, der das Übergeben des aktiven Users und des Suchergebnisses ermöglicht.
	 */
	public SearchUserResultBox(TextBox searchUserInput, User resultUser) {
		this.searchUserInput = searchUserInput;
		this.resultUser = resultUser;
//		
		this.accountNameOfShownUser = resultUser.getFirstName() + " " + resultUser.getLastName();
		this.nicknameOfShownUser=resultUser.getNickname();	
	}
	

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Widget#onLoad()
	 */
	public void onLoad() {
		
		accountNameLabel = new Label();
		nicknameLabel = new Label();
		accountWrapper = new FlowPanel();
		nickWrapper = new FlowPanel();
		aboWrapper = new FlowPanel();
		accountNameLabel.setText(accountNameOfShownUser);
		nicknameLabel.setText(nicknameOfShownUser);
		choseUserBtn.addStyleName("button");
		aboWrapper.add(choseUserBtn);
		choseUserBtn.addClickHandler(new ClickHandler() {		
			@Override
			public void onClick(ClickEvent event) {
			    searchUserInput.setText(resultUser.getGMail());
			}
		});
		
		/*
		 * Style der Dialogbox und alle dazugehörigen Widgets & Panels
		 */
		this.addStyleName("box grid_box radiusless");
		accountWrapper.addStyleName("box-item-ein-viertel");
		nickWrapper.addStyleName("box-item-ein-viertel");
		aboWrapper.addStyleName("box-item-ein-viertel");
		accountNameLabel.addStyleName("title is-size-3");
		nicknameLabel.addStyleName("is-size-4");
		
		/*
		 *  Hinzufuegen der Widgets und Panels zu den einzelnen Wrappern
		 */
		accountWrapper.add(accountNameLabel);
		nickWrapper.add(nicknameLabel);
	
		/*
		 * Hinzufuegen der Wrapper zum Parent Panel
		 */
		this.add(accountWrapper);
		this.add(nickWrapper);
		this.add(aboWrapper);
	}
	
	

	
	
	
	public Button getChoseUserBtn() {
		return choseUserBtn;
	}
	
	public String getGMail() {
		return resultUser.getGMail();
	}
	

	
}
