package de.hdm.softwarePraktikumGruppe1.client;

import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;

import de.hdm.softwarePraktikumGruppe1.client.reportgui.BeitragReportForm;
import de.hdm.softwarePraktikumGruppe1.client.reportgui.ReportHeader;
import de.hdm.softwarePraktikumGruppe1.client.reportgui.UserReportForm;
import de.hdm.softwarePraktikumGruppe1.shared.ReportGeneratorService;
import de.hdm.softwarePraktikumGruppe1.shared.ReportGeneratorServiceAsync;
import de.hdm.softwarePraktikumGruppe1.shared.bo.User;
import de.hdm.softwarePraktikumGruppe1.shared.report.BeitragReport;
import de.hdm.softwarePraktikumGruppe1.shared.report.HTMLReportWriter;
import de.hdm.softwarePraktikumGruppe1.shared.report.UserReport;


/**
 * Die Klasse <code>ReportGenerator</code> enthält alle Elemente zur 
 * Generierung und Darstellung des Reports.
 * Von hier werden RPCs des Report-Client angestoßen.
 * 
 * Grundlage der GUI ist hier ein DockLayoutPanel.
 * 
 * 
 *@author JakobBenkoe
 */

public class ReportGenerator  implements EntryPoint {
	
	private DockLayoutPanel dockPanel = new DockLayoutPanel(Unit.EM);
	private ReportHeader header = new ReportHeader();
	private UserReportForm userReportForm = new UserReportForm();
	private BeitragReportForm beitragReportForm = new BeitragReportForm();

	
	public void onModuleLoad() {

	
		//Adding Custom ClickHandlers
		header.getBeitraegeButton().addClickHandler(new beitraegeButtonClickHandler());
		header.getUserButton().addClickHandler(new userButtonClickHandler());
		userReportForm.getGeneratorBox().getButton().addClickHandler(new generateUserReportClickHandler());
		beitragReportForm.getGeneratorBox().getButton().addClickHandler(new generateBeitragReportClickHandler());
		
		
		//Adding Content to Rootpanel
		dockPanel.clear();
		dockPanel.addNorth(header, 4);
		//dockPanel.addWest(userReportForm, 25);
		LayoutPanel panel = new LayoutPanel();
		panel.add(dockPanel);
		RootPanel.get().add(dockPanel);

	}
	
	
	

	
	
	
	/*
	 * Clickhandler for userButton @see ReportHeader
	 * Remove existing ReportForm then add userReportForm
	 */
		private class userButtonClickHandler implements ClickHandler{
			@Override
			public void onClick(ClickEvent event) {

				dockPanel.clear();
				dockPanel.addNorth(header, 4);
				dockPanel.addWest(userReportForm, 25);
				
			}			
		}	
	
		
	/*
	 * Clickhandler for beitraegeButton @see ReportHeader
	 * Remove existing ReportForm then add userReportForm
	 */
	private class beitraegeButtonClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			
			dockPanel.clear();
			dockPanel.addNorth(header, 4);
			dockPanel.addWest(beitragReportForm, 25);
		}		
	}
		
			
	/*
	 * ClickHandler for generateUserReport
	 * UserReport will be created.
	 * @see UserReportForm
	 * @see GeneratorBox
	 * 
	*/
		private class generateUserReportClickHandler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
								
				if (userReportForm.getDatePickerBox1().getDate() == null || userReportForm.getDatePickerBox2().getDate() == null)
				{
					Window.alert("Bitte ein gültiges Datum angeben");
				}else if(userReportForm.getUserMail() == null){
					Window.alert("Bitte eine gültige User eMail angeben");
				}else {
				//Try to make RPC with entered user Data
					try {
						//get Dates From DatePicker Boxes
						Date date1 = userReportForm.getDatePickerBox1().getDate();
						Date date2 = userReportForm.getDatePickerBox2().getDate();
						
						ReportGeneratorServiceAsync proxy = (ReportGeneratorServiceAsync)GWT.create(ReportGeneratorService.class);
						AsyncCallback<UserReport> callback = new AsyncCallback<UserReport>() {					
							public void onFailure(Throwable caught) {
								
								dockPanel.clear();
								dockPanel.add(new Label("RPC Failure. " + caught.toString()));
							}
	
							@Override
							public void onSuccess(UserReport result) {
								
								HTMLReportWriter htmlWriter = new HTMLReportWriter();
								htmlWriter.process(result);
								
								//display retrieved report
								dockPanel.clear();
								dockPanel.addNorth(header, 4);
								dockPanel.addWest(userReportForm, 25);
								ScrollPanel reportPanel = new ScrollPanel();
								reportPanel.setStyleName("box");
								reportPanel.add(new HTML(htmlWriter.getReportText()));
								dockPanel.add(reportPanel);
								
							}
						};
						DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd.MM.yyyy");
						proxy.createUserReport(userReportForm.getUserMail(), date1, date2, callback);
					}catch(NullPointerException e) {
						Window.alert("Bitte ein gültiges Datum eintragen!");
					}
					catch(Exception e) {
						Window.alert(e.toString());
					}
				}
			}	
		}	

		
		
		/*
		 * ClickHandler for generateBeitragReport
		 * BeitragReport will be created.
		 * @see BeitragReportForm
		 * @see GeneratorBox
		 * 
		*/				
		private class generateBeitragReportClickHandler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
								
				if (beitragReportForm.getDatePickerBox1().getDate() == null || beitragReportForm.getDatePickerBox2().getDate() == null)
				{
					Window.alert("Bitte ein gültiges Datum angeben");
				}else if(beitragReportForm.getSearchBeitragBox().getEnteredText() == null){
					Window.alert("Bitte einen gültigen Beitrag angeben");
				}else {
				//Try to make RPC with entered user Data
					try {
						//get Dates From DatePicker Boxes
						Date date1 = beitragReportForm.getDatePickerBox1().getDate();
						Date date2 = beitragReportForm.getDatePickerBox2().getDate();
						
						int beitragID = Integer.parseInt(beitragReportForm.getSearchBeitragBox().getEnteredText());
						ReportGeneratorServiceAsync proxy = (ReportGeneratorServiceAsync)GWT.create(ReportGeneratorService.class);
						AsyncCallback<BeitragReport> callback = new AsyncCallback<BeitragReport>() {					
							public void onFailure(Throwable caught) {
								
								dockPanel.clear();
								dockPanel.add(new Label("RPC Failure. " + caught.toString()));
							}
	
							@Override
							public void onSuccess(BeitragReport result) {
								
								HTMLReportWriter htmlWriter = new HTMLReportWriter();
								htmlWriter.process(result);
								
								//display retrieved report
								dockPanel.clear();
								dockPanel.addNorth(header, 4);
								dockPanel.addWest(beitragReportForm, 25);
								ScrollPanel reportPanel = new ScrollPanel();
								reportPanel.setStyleName("box");
								reportPanel.add((new HTML(htmlWriter.getReportText())));
								dockPanel.add(reportPanel);
								
							}
						};
						proxy.createBeitragReport(beitragID, date1, date2, callback);
					}catch(NumberFormatException e) {
						Window.alert("Bitte eine gültige Beitrags ID eingeben!");
					}
					catch(Exception e) {
						Window.alert(e.toString());
					}
				}
				
					
				
			}		
		}	
			
}	

