package de.hdm.softwarePraktikumGruppe1.client.gui;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.*;

public class KommentarBox extends FlowPanel {
	// Panels for the Element
	private VerticalPanel parentVerticalPanel = new VerticalPanel();
	private FlowPanel userInfoWrapper = new FlowPanel();
	private FlowPanel creationInfoWrapper = new FlowPanel();
	private FlowPanel contentWrapper = new FlowPanel();
	private HTML hrElement = new HTML("<hr/>");
	
	// Content des Kommentars
	private Label accountName = new Label("John Smith");
	private Label nickName = new Label("@john");
	private Label kommentarContent = new Label("Some Kommentar Content about Kommentars");
	private Label creationDate = new Label();
	
	public KommentarBox() {
		// Date Stuff
		Date now = new Date();
		DateTimeFormat fmt = DateTimeFormat.getFormat("EEEE, dd MMMM, yyyy");
		String date = fmt.format(now).toString();
		creationDate.setText("Erstellungszeitpunkg: " + date);
		
		this.addStyleName("post_content kommentar_margin");
		parentVerticalPanel.addStyleName("control");
		accountName.addStyleName("is-size-4");
		nickName.addStyleName("is-size-6");
		creationDate.addStyleName("is-size-7");
		userInfoWrapper.addStyleName("grid_box content_margin");
		creationInfoWrapper.addStyleName("content_margin");

		userInfoWrapper.add(accountName);
		userInfoWrapper.add(nickName);
		creationInfoWrapper.add(creationDate);
		contentWrapper.add(kommentarContent);
		
		parentVerticalPanel.add(userInfoWrapper);
		parentVerticalPanel.add(creationInfoWrapper);
		parentVerticalPanel.add(contentWrapper);
		
		
		this.add(hrElement);
		this.add(parentVerticalPanel);
	}
	
	public void onLoad() {
		
	}
}
