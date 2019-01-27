package de.hdm.softwarePraktikumGruppe1.client.reportgui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;


import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;


public class searchBeitragResultBox extends FlowPanel {
	TextBox searchUserInput;

	private Beitrag resultBeitrag;
	private String beitragDate;
	private String firstContent;

	
	private Label beitragDateLabel;
	private Label firstContentLabel;
	
	private FlowPanel accountWrapper;
	private FlowPanel nickWrapper;
	private FlowPanel userWrapper;
	
	
	Button choseUserBtn = new Button("Auswählen");

	
	/*
	 * Konstruktor, der das Übergeben des aktiven Users und des Suchergebnisses ermöglicht.
	 */

	public searchBeitragResultBox(TextBox searchUserInput, Beitrag resultBeitrag) {
		this.searchUserInput = searchUserInput;
		this.resultBeitrag = resultBeitrag;
//		
		this.beitragDate = DateTimeFormat.getFormat(PredefinedFormat.DATE_MEDIUM).format(resultBeitrag.getCreationTimeStamp());
		this.firstContent = resultBeitrag.getInhalt();	
	}
	

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Widget#onLoad()
	 */
	public void onLoad() {
		
		beitragDateLabel = new Label();
		firstContentLabel = new Label();
		accountWrapper = new FlowPanel();
		nickWrapper = new FlowPanel();
		userWrapper = new FlowPanel();
		beitragDateLabel.setText(beitragDate);
		firstContentLabel.setText(firstContent);
		choseUserBtn.addStyleName("button");
		userWrapper.add(choseUserBtn);
		choseUserBtn.addClickHandler(new ClickHandler() {		
			@Override
			public void onClick(ClickEvent event) {
			    searchUserInput.setText(""+ resultBeitrag.getBeitragId());
			}
		});
		
		/*
		 * Style der Dialogbox und alle dazugehörigen Widgets & Panels
		 */
		this.addStyleName("box grid_box radiusless");
		accountWrapper.addStyleName("box-item-ein-viertel");
		nickWrapper.addStyleName("box-item-ein-viertel");
		userWrapper.addStyleName("box-item-ein-viertel");
		beitragDateLabel.addStyleName("title is-size-3");
		firstContentLabel.addStyleName("is-size-4");
		
		/*
		 *  Hinzufuegen der Widgets und Panels zu den einzelnen Wrappern
		 */
		accountWrapper.add(beitragDateLabel);
		nickWrapper.add(firstContentLabel);
	
		/*
		 * Hinzufuegen der Wrapper zum Parent Panel
		 */
		this.add(accountWrapper);
		this.add(nickWrapper);
		this.add(userWrapper);
	}
	
	

	
	
	
	public Button getChoseUserBtn() {
		return choseUserBtn;
	}
	

	

	
}
