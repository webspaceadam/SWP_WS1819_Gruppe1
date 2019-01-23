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

import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;


import de.hdm.softwarePraktikumGruppe1.shared.ReportGeneratorService;
import de.hdm.softwarePraktikumGruppe1.shared.ReportGeneratorServiceAsync;
import de.hdm.softwarePraktikumGruppe1.shared.bo.User;

/**
 * Die Klasse <code>SearchBox</code> ermöglicht die Suche nach Usern. 
 * 
 * @author JakobBenkoe
 *
 */
public class SearchUserBox extends FlowPanel {
	private String gMail;
	
	private FlowPanel inputWrapper = new FlowPanel();
	private FlowPanel searchWrapper = new FlowPanel();
	
	private TextBox searchUserInput = new TextBox();
	private Button searchBtn = new Button("Suche!");
	
	SearchUserDialogBox dlg;
	
	
	public SearchUserBox() {
		this.addStyleName("grid_box box radiusless");
		inputWrapper.addStyleName("grid_box_element");
		searchWrapper.addStyleName("grid_box_element");
		searchUserInput.addStyleName("input radiusless");
		searchUserInput.getElement().setPropertyString("placeholder", "Suche nach Usern!");
		searchBtn.addStyleName("button bg-primary has-text-white");
		
		inputWrapper.add(searchUserInput);
		searchWrapper.add(searchBtn);
		searchBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				ReportGeneratorServiceAsync proxy = (ReportGeneratorServiceAsync)GWT.create(ReportGeneratorService.class);
				proxy.searchUserFunction((searchUserInput.getText()), new SearchResultCallback());				
			}
		});
		
		this.add(inputWrapper);
		this.add(searchWrapper);
	}
	
	public void onLoad() {

	}
	
	
	public String getUserString() {
		return searchUserInput.getText();
	}
	
	
	
	
	
	private class SearchUserDialogBox extends DialogBox implements ClickHandler {
		
		private ScrollPanel parentScrolling = new ScrollPanel();
		private FlowPanel userParentPanel = new FlowPanel();
		private int ergebnisCounter;
		
		
		
		private Vector<SearchUserResultBox> searchResultBoxes = new Vector<SearchUserResultBox>();
		
		public SearchUserDialogBox(Vector<User> searchResults) {
			this.ergebnisCounter = searchResults.size();
			
			setText("Deine Suche ergab "+ergebnisCounter + " Treffer");

			
			for(User u: searchResults) {
				SearchUserResultBox singleUserBox = new SearchUserResultBox(searchUserInput, u);
				singleUserBox.getChoseUserBtn().addClickHandler(new ClickHandler() {	
					@Override
					public void onClick(ClickEvent event) {
						hide();		
					}
				});
				searchResultBoxes.add(singleUserBox);
			}
			
			for(SearchUserResultBox s: searchResultBoxes) {
				userParentPanel.add(s);
			}
			
			
			parentScrolling.add(userParentPanel);
			parentScrolling.setSize("800px", "400px");
			
			
			Image cancelImage = new Image("images/SVG/timesCircle.png");
  		    cancelImage.getElement().setPropertyString("style", "max-width: 25px;");
			cancelImage.addClickHandler(this);

			DockPanel dock = new DockPanel();
			dock.setSpacing(6);
			dock.add(parentScrolling, DockPanel.CENTER);
			dock.add(cancelImage, DockPanel.EAST);
			
			//safeButton.addClickHandler();

			//dock.setCellHorizontalAlignment(safeButton, DockPanel.ALIGN_CENTER);
			dock.setWidth("900px");
			dock.setHeight("400px");
			setWidget(dock);
		}

		@Override
		public void onClick(ClickEvent event) {
			hide();
		}
	}
	
	
	public class SearchResultCallback implements AsyncCallback<Vector<User>>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Shit");
			
		}

		@Override
		public void onSuccess(Vector<User> result) {
			if (result.size()>0) {
				//Window.alert("Deine Suche ergab " + result.size()+ " Treffer");
			
				dlg = new SearchUserDialogBox(result);
				dlg.center();
				
			}else {
				Window.alert("Deine Suche ergab 0 Treffer");
			}
			
			
		}
	
	}
	
	


}
