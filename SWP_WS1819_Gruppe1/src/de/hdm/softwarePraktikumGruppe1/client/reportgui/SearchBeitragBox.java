package de.hdm.softwarePraktikumGruppe1.client.reportgui;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.softwarePraktikumGruppe1.shared.ReportGeneratorService;
import de.hdm.softwarePraktikumGruppe1.shared.ReportGeneratorServiceAsync;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;


/**
 * 
 * Mit dieser Box kann der User nach Beiträgen suchen.
 * Der User gibt einen Text ein und drückt dann den Suchen Button, um dazu passende Beiträge zu sehen.
 * @author Jakob Benkö
 *
 */
public class SearchBeitragBox extends VerticalPanel {
	private FlowPanel inputWrapper = new FlowPanel();
	private FlowPanel searchWrapper = new FlowPanel();
	
	private TextBox searchUserInput = new TextBox();
	private Button searchBtn = new Button("Nach Beiträgen suchen!");
	private Label beitragLabel;
	
	private SearchUserDialogBox dlg;
	
	//Identifier for author
	SearchUserBox searchUserBox;
	
	public SearchBeitragBox() {
		this.addStyleName("grid_box box radiusless");
		inputWrapper.addStyleName("grid_box_element");
		searchWrapper.addStyleName("grid_box_element");
		searchUserInput.addStyleName("input radiusless");
		searchUserInput.setReadOnly(true);
		searchUserInput.getElement().setPropertyString("placeholder", "Suche nach Beiträge");
		searchBtn.addStyleName("button bg-primary has-text-white");
		searchBtn.addClickHandler(new searchBeitragClickHandler());
		
		inputWrapper.add(searchUserInput);
		searchWrapper.add(searchBtn);
		
		//this.add(inputWrapper);
		this.add(searchWrapper);
		//this.add(new Label("sd"));
	}
	
	public void onLoad() {
		
	}
	
	
	public void setAuthor(SearchUserBox searchUserBox) {
		this.searchUserBox = searchUserBox;
	}
	
	public String getEnteredText() {
		return searchUserInput.getText();
	}
	
	
	
	private class searchBeitragClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			
			if(searchUserBox.getUserString().equals("")) {
				
				Window.alert("Bitte zuerst einen User auswählen.");
				
			}else {
				
				ReportGeneratorServiceAsync proxy = (ReportGeneratorServiceAsync)GWT.create(ReportGeneratorService.class);
				proxy.searchBeitragFunction((searchUserBox.getUserString()), new SearchResultCallback());	
				
			}
			
		}
		
	}
	
	
	
	private class SearchResultCallback implements AsyncCallback<Vector<Beitrag>>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("sdf");
			
		}

		@Override
		public void onSuccess(Vector<Beitrag> result) {

			if (result.size()>0) {
				//Window.alert("Deine Suche ergab " + result.size()+ " Treffer");
			
				dlg = new SearchUserDialogBox(result);
				dlg.center();
				
			}else {
				Window.alert("Keine Beiträge für den gewählten Nutzer gefunden."
						+ " Bitte einen anderen Nutzer wählen.");
			}
	
		}
		
	}
	
	
	
	
	/*
	 * DialogBox that displays resultUser that match to search string
	 * User can select on resultUser.
	 */
	private class SearchUserDialogBox extends DialogBox implements ClickHandler {
		
		private ScrollPanel parentScrolling = new ScrollPanel();
		private FlowPanel userParentPanel = new FlowPanel();
		private int ergebnisCounter;
		
		
		
		private Vector<searchBeitragResultBox> searchResultBoxes = new Vector<searchBeitragResultBox>();
		
		public SearchUserDialogBox(Vector<Beitrag> searchResults) {
			this.ergebnisCounter = searchResults.size();
			
			setText("Für den User wurden " + ergebnisCounter + " Beiträge gefunden. Bitte wähle einen davon aus.");

			
			for(Beitrag b: searchResults) {
				searchBeitragResultBox singleBeitragBox = new searchBeitragResultBox(searchUserInput, b);
				singleBeitragBox.getChoseUserBtn().addClickHandler(new ClickHandler() {	
					@Override
					public void onClick(ClickEvent event) {
						hide();		
					}
				});
				searchResultBoxes.add(singleBeitragBox);
			}
			
			for(searchBeitragResultBox s: searchResultBoxes) {
				userParentPanel.add(s);
			}
			
			
			parentScrolling.add(userParentPanel);
			parentScrolling.setSize("800px", "500px");
			
			
			Image cancelImage = new Image("images/SVG/timesCircle.png");
  		    cancelImage.getElement().setPropertyString("style", "max-width: 25px;");
			cancelImage.addClickHandler(this);

			DockPanel dock = new DockPanel();
			dock.setSpacing(6);
			dock.add(parentScrolling, DockPanel.CENTER);
			dock.add(cancelImage, DockPanel.EAST);
			
			dock.setWidth("900px");
			dock.setHeight("500px");
			setWidget(dock);
		}

		@Override
		public void onClick(ClickEvent event) {
			hide();
		}
	}
	
	

}