package de.hdm.softwarePraktikumGruppe1.client.reportgui;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;

import de.hdm.softwarePraktikumGruppe1.client.gui.SearchBox;

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

		
		
		this.add(new SearchBeitragBox());
		this.add(new DatePickerBox());
		this.add(new DatePickerBox());
		this.add(new GeneratorBox());
	}

	

	public void onLoad() {
	}
}
