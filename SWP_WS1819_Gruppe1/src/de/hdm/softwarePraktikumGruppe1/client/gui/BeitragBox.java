package de.hdm.softwarePraktikumGruppe1.client.gui;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.*;

/**
 * Die <code>Beitrag</code>-Klasse ist eine Custom-Widget-Class die dafür verwendet wird, 
 * um einen Beitrag im System korrekt anzuzeigen
 * 
 * @author Adam Gniady
 * @version 1.0
 */

public class BeitragBox extends FlowPanel {
	// Panels for the Element
	private VerticalPanel parentVerticalPanel = new VerticalPanel();
	private FlowPanel userInfoWrapper = new FlowPanel();
	private FlowPanel creationInfoWrapper = new FlowPanel();
	private FlowPanel contentWrapper = new FlowPanel();
	private FlowPanel socialWrapper = new FlowPanel();
	private HorizontalPanel likeInfoWrapper = new HorizontalPanel();
	private HTML hrElement = new HTML("<hr/>");
	
	// Labels
	private Label accountName = new Label("Sebastian Hermann");
	private Label nickName = new Label("@sebmeister");
	private Label creationDate = new Label();
	private Label likeCountText = new Label();
	private int likeCount = 0;
	
	// Buttons for Social
	private Button commentBtn = new Button("Kommentiere");
	private Button likeBtn = new Button("Like");

	
	// Paragraph Elements
	private Label beitragContent = new Label("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ornare magna eros, eu pellentesque tortor vestibulum ut. Maecenas non massa sem. Etiam finibus odio quis feugiat facilisis. ");
	
	
	// Other Elements for this Widget
	
	public BeitragBox() {
		// Date
		Date now = new Date();
		DateTimeFormat fmt = DateTimeFormat.getFormat("EEEE, dd MMMM, yyyy");
		String date = fmt.format(now).toString();
		
		// Stylingelements for this Widget
		this.addStyleName("box radiusless");
		parentVerticalPanel.addStyleName("post_content");
		accountName.addStyleName("is-size-4");
		nickName.addStyleName("is-size-6");
		creationDate.addStyleName("is-size-7");
		userInfoWrapper.addStyleName("grid_box content_margin");
		creationInfoWrapper.addStyleName("content_margin");
		contentWrapper.addStyleName("content_margin");
		
		// Social Wrapper
		socialWrapper.addStyleName("grid_box_links");
		
		
		commentBtn.addStyleName("button bg-primary grid_box_element");
		likeBtn.addStyleName("button bg-primary grid_box_element");
		
		socialWrapper.add(commentBtn);
		socialWrapper.add(likeBtn);
		
		creationDate.setText("Erstellungszeitpunkt: " + date);
		
		likeCountText.addStyleName("is-size-6 is-italic");
		likeCountText.setText("Likes auf diesem Beitrag: " + likeCount);
		
		
		likeInfoWrapper.add(likeCountText);
		
		// Add ClickHandler for Button
		likeBtn.addClickHandler(new LikeCountClickHandler(this));
		
		
		// Add Elements to Wrapper
		userInfoWrapper.add(accountName);
		userInfoWrapper.add(nickName);
		creationInfoWrapper.add(creationDate);
		contentWrapper.add(beitragContent);
		
		
		// Add Wrappers to Element
		this.add(userInfoWrapper);
		this.add(creationInfoWrapper);
		this.add(contentWrapper);
		this.add(likeInfoWrapper);
		this.add(hrElement);
		this.add(socialWrapper);
	}
	
	public void onLoad() {
		
	}
	
	/**
	 * Die innere Klasse <code>LikeCountClickHandler</code> implementiert das Clickhandler 
	 * Interface und dessen dazugehörige <code>onClick(ClickEvent event)</code> Methode.
	 * Diese Methode arbeitet mit der Anzahl der Likes auf einem Beitrag und passt die Anzahl auf den 
	 * dazugehörigen Beitrag an. 
	 * @author Adam Gniady
	 *
	 */
	private class LikeCountClickHandler implements ClickHandler {
		private BeitragBox parentBB;
		
		public LikeCountClickHandler(BeitragBox bb) {
			parentBB = bb;
		}
			
		@Override
		public void onClick(ClickEvent event) {
			parentBB.likeCount += 1;
			parentBB.likeCountText.setText("Likes auf diesem Beitrag: " + parentBB.likeCount);
			GWT.log("Like Count is: " + parentBB.likeCount);
		}
	}
}
