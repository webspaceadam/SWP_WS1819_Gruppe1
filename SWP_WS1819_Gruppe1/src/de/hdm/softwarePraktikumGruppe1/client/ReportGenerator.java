package de.hdm.softwarePraktikumGruppe1.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.softwarePraktikumGruppe1.client.reportgui.Header;
import de.hdm.softwarePraktikumGruppe1.client.reportgui.userReportForm;

/**
 * Die Klasse <code>ReportGenerator</code> enthält alle Elemente zur 
 * Generierung und Darstellung des Reports
 * Grundlage der GUI ist hier ein DockLayoutPanel
 * 
 * 
 *@author Jakob Benkö
 */

public class ReportGenerator  implements EntryPoint {
	public void onModuleLoad() {

		DockLayoutPanel dockPanel = new DockLayoutPanel(Unit.EM);
		

		dockPanel.addNorth(new Header(), 4);
		dockPanel.addWest(new userReportForm(), 25);
		dockPanel.add(new Label("Hier wird der Report eingeblendet werden"));
		
		
		LayoutPanel panel = new LayoutPanel();
		panel.add(dockPanel);
		
		RootPanel.get().add(dockPanel);
	}
}
