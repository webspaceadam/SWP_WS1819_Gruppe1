package de.hdm.softwarePraktikumGruppe1.client.reportgui;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

/**
 * Die <code>datePickerBox</code>-Klasse ist eine Custom-Widget-Class die 
 * dem User die Möglichkeit gibt, ein Datum auszuwählen.
 * 
 *   
 * @author JakobBenkoe
 * @version 1.0
 */
public class datePickerBox extends FlowPanel {

	
	
	
	DatePicker datePicker = new DatePicker();
	
	public datePickerBox() {
		this.addStyleName("grid_box box radiusless");


	
		
			//Add Styling to Date Box and DatePicker
		   @SuppressWarnings("deprecation")
		   	DateTimeFormat dateFormat = DateTimeFormat.getLongDateFormat();
		    DateBox dateBox = new DateBox();
		    dateBox.addStyleName("input radiusless");
		    dateBox.getElement().setPropertyString("placeholder", "Wähle ein Datum aus");
		    dateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
		    dateBox.getDatePicker().setStyleName("datePicker");
		    dateBox.getDatePicker().setYearArrowsVisible(true);
		
		
		
		
		this.add(dateBox);
		
	}
	
	public void onLoad() {
		
	}

}