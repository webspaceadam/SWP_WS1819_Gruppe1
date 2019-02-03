
package de.hdm.softwarePraktikumGruppe1.client.reportgui;



import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;



/**
 * Die <code>Header</code>-Klasse ist eine Custom-Widget-Class die 
 * dem User anzeigt, dass er sich auf dem Report client befindet.
 * 
 * Außerdem kann hier zwischen dem Beitrags- und Userreport gewählt werden.
 * 
 * @author JakobBenkoe
 * @version 1.0
 */

public class ReportHeader extends FlowPanel {
	
	// Create Header Divs 
	private FlowPanel headerLogoDiv = new FlowPanel();
	private FlowPanel headerLogo = new FlowPanel();
	private FlowPanel headerLeft = new FlowPanel(); 
	private FlowPanel headerRight = new FlowPanel();

	//Create Buttons for Selection of report type
	private Button beitraegeButton = new Button("Beiträge");
	private Button userButton = new Button("User");
	private Button editorButton = new Button("Zum Editor");

	
	// Create ParentDivs for Buttons
	private FlowPanel beitraegeDiv = new FlowPanel();
	private FlowPanel userDiv = new FlowPanel();
	private FlowPanel editorDiv = new FlowPanel();

	
	// Create Images
	Image logo = new Image();
	
	
	//Create Label
	Label reportLabel = new Label("Report Generator");
	


	public ReportHeader() {
		// Add styling to this element
		this.addStyleName("header bg-primary");
		headerLeft.addStyleName("header_left");
		headerRight.addStyleName("header_right");
		
		//Add logo to headerLogoDiv and add headerLogoDiv to headerLogo
		logo.setUrl("/images/pinners_with_primary_white.png");		
		headerLogoDiv.add(logo);
		headerLogoDiv.addStyleName("header_element");
		
		headerLogo.addStyleName("header_logo");
		headerLogo.add(headerLogoDiv);
		
		
		
		//Add styling to Label and add label to parentDiv
		reportLabel.addStyleName("header_element has-text-white");	
		headerLeft.add(reportLabel);
		
		
		
		//Add styling to buttons and add buttons to header
		beitraegeButton.addStyleName("button bg-primary");
		userButton.addStyleName("button bg-primary");
		editorButton.addStyleName("button bg-primary");

		beitraegeDiv.addStyleName("header_element");
		userDiv.addStyleName("header_element");
		editorDiv.addStyleName("header_element");

		beitraegeDiv.add(beitraegeButton);
		userDiv.add(userButton);
		editorDiv.add(editorButton);

		headerRight.add(beitraegeDiv);
		headerRight.add(userDiv);
		headerRight.add(editorDiv);
		
		//Add elements to header
		this.add(headerLogo);
		this.add(headerLeft);
		this.add(headerRight);
	
		
		//Add ClickHandler
		editorButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				Window.Location.assign(GWT.getHostPageBaseURL() + "SWP_WS1819_Gruppe1.html");
				
			}
		});
	}

	




	public void onLoad() {
	}
	

		/**
		 * Methode die den Beitragsbutton zurückgibt
		 * @return beitraegeButton
		 */
	
		public Button getBeitraegeButton() {
			return beitraegeButton;
		}
		
		/**
		 * Methode die den UserButton zurückgibt
		 * @return userButton
		 */
		
		public Button getUserButton() {
			return userButton;
		}
	
}