package de.hdm.softwarePraktikumGruppe1.client.reportgui;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;



/**
 * Die <code>beitragReportForm</code>-Klasse ist eine Custom-Widget-Class die 
 * dem User die Möglichkeit gibt, die Rahmendaten für den Beitragreport 
 * einzugeben
 *   
 * @author JakobBenkoe
 * @version 1.0
 */

public class BeitragReportForm extends FlowPanel {

	
	// dazugehörige Label
	private Label reportName = new Label("Beiträge Report");
	private Label reportInfo = new Label("Informationen über Beiträge je Zeitraum");
	private HTML hrElement = new HTML("<hr/>");
	// dazugehörige wrapper
	private FlowPanel wrapper1 = new FlowPanel();
	private FlowPanel wrapper1_el_links = new FlowPanel();
	private FlowPanel wrapper1_el_rechts = new FlowPanel();
	//Eingabemöglichkeiten
	SearchUserBox searchUserBox = new SearchUserBox();
	SearchBeitragBox searchBeitragBox = new SearchBeitragBox();
	DatePickerBox datePickerBox1 = new DatePickerBox();
	DatePickerBox datePickerBox2 = new DatePickerBox();
	GeneratorBox generatorBox = new GeneratorBox();
	


	public BeitragReportForm() {
		// Adding Styling for userReportForm
		this.addStyleName("box radiusless");
		
		// Wrapper 1 styling
		wrapper1.addStyleName("grid_box");
		wrapper1_el_links.addStyleName("grid_box_links");
		wrapper1_el_rechts.addStyleName("grid_box_rechts");
		
		reportName.addStyleName("title is-size-4 grid_box_element");
		
		// nickname styling
		reportInfo.addStyleName("is-size-5");
		

		// Adding Elements to Wrapper 1
		wrapper1_el_links.add(reportName);
		wrapper1.add(wrapper1_el_links);
		wrapper1.add(wrapper1_el_rechts);
		
		
		// Wrapping all up 
		this.add(wrapper1);
		this.add(reportInfo);
		this.add(hrElement);

		
		this.add(searchUserBox);
		this.add(searchBeitragBox);
		this.add(datePickerBox1);
		this.add(datePickerBox2);
		this.add(generatorBox);
		
		
		//set Author reference in searchBeitragBox
		searchBeitragBox.setAuthor(searchUserBox);
	}

	

	public void onLoad() {
	}
	
	
	/**
	 * necessary in order to add clickHanlder in @see ReportGenerator
	 * @return GeneratorBox
	 */
	public GeneratorBox getGeneratorBox() {
		return generatorBox;
	}


	/**
	 * @return SearchBeitragBox
	 * In der @code TextBox searchUserInput der @code SearchBeitragBox ist
	 * die ausgewählte Beitrags-ID gespeichert
	 */
	public SearchBeitragBox getSearchBeitragBox() {
		return searchBeitragBox;
	}


	/**
	 * @return DatePickerBox
	 */
	public DatePickerBox getDatePickerBox1() {
		return datePickerBox1;
	}


	/**
	 * @return DatePickerBox
	 */
	public DatePickerBox getDatePickerBox2() {
		return datePickerBox2;
	}
	
	
	
}
