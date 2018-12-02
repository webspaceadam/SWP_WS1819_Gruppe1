package de.hdm.softwarePraktikumGruppe1.client.gui;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.*;

/**
 * Die <code>Beitrag</code>-Klasse ist eine Custom-Widget-Class die daf�r verwendet wird, 
 * um einen Beitrag im System korrekt anzuzeigen
 * 
 * @author AdamGniady
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

	
	// Paragraph Elements
	private Label beitragContent = new Label("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ornare magna eros, eu pellentesque tortor vestibulum ut. Maecenas non massa sem. Etiam finibus odio quis feugiat facilisis. ");
	
	
	// Images for the Buttons
	private Image likeHeart = new Image("images/SVG/heart.svg");
	private Image likeHeartBtn = new Image("images/SVG/heart.svg");
	private Image replyBtn = new Image("images/SVG/reply.svg");
	private Image editPenBtn = new Image("images/SVG/pen.svg");
	
	// Other Elements for this Widget
	private FlowPanel heartWrapper = new FlowPanel();
	private FlowPanel replyWrapper = new FlowPanel();
	
	// Creating Kommentar
	private VerticalPanel createKommentarWrapper = new VerticalPanel();
	private HTML hrElementKommentar = new HTML("<hr/>");
	private TextArea kommentarTextArea = new TextArea();
	private Button addKommentarBtn = new Button("Poste Kommentar");
	
	
	public BeitragBox() {
		// Date
		Date now = new Date();
		DateTimeFormat fmt = DateTimeFormat.getFormat("HH:mm:ss, EEEE, dd MMMM, yyyy");
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
		likeInfoWrapper.addStyleName("grid_box");
		
		editPenBtn.addStyleName("grid_box_element");
		editPenBtn.addClickHandler(new EditBeitragBoxClickHandler(this));
		editPenBtn.getElement().setPropertyString("style", "max-width: 25px;");
		
		// Social Wrapper
		heartWrapper.addStyleName("grid_box_element");
		replyWrapper.addStyleName("grid_box_element");
		likeHeartBtn.getElement().setPropertyString("style", "max-width: 25px;");
		replyBtn.getElement().setPropertyString("style", "max-width: 25px;");
		
		heartWrapper.add(likeHeartBtn);
		replyWrapper.add(replyBtn);
		
		// ClickHandler Call for Like Action
		likeHeartBtn.addClickHandler(new LikeCountClickHandler(this));
		socialWrapper.add(heartWrapper);
		socialWrapper.add(replyWrapper);
		creationDate.setText("Erstellungszeitpunkt: " + date);
		
		// Likecount info
		likeHeart.setWidth("1rem");
		likeHeart.addStyleName("small-padding-right");
		likeCountText.addStyleName("is-size-6 is-italic");
		likeCountText.setText(" auf diesem Beitrag: " + likeCount);
		
		// Adding Elements to the Wrapper
		likeInfoWrapper.add(likeHeart);
		likeInfoWrapper.add(likeCountText);
		
		// Here we can create a Kommentar
		createKommentarWrapper.setWidth("100%");
		createKommentarWrapper.addStyleName("post_content");
		kommentarTextArea.getElement().setPropertyString("placeholder", "Erstelle hier deinen Kommentar!");
		kommentarTextArea.setWidth("100%");
		kommentarTextArea.addStyleName("textarea content_margin control");
		addKommentarBtn.addStyleName("button bg-primary");
		
		// Adding Elements to KommentarParent
		createKommentarWrapper.add(hrElementKommentar);
		createKommentarWrapper.add(kommentarTextArea);
		createKommentarWrapper.add(addKommentarBtn);
		
		
		// Add Elements to Wrapper
		userInfoWrapper.add(accountName);
		userInfoWrapper.add(nickName);
		userInfoWrapper.add(editPenBtn);
		creationInfoWrapper.add(creationDate);
		contentWrapper.add(beitragContent);
		
		
		// Add Wrappers to Element
		this.add(userInfoWrapper);
		this.add(creationInfoWrapper);
		this.add(contentWrapper);
		this.add(likeInfoWrapper);
		this.add(hrElement);
		this.add(socialWrapper);
		this.add(createKommentarWrapper);
	}
	
	public void onLoad() {
		
	}
	
	/**
	 * Die innere Klasse <code>LikeCountClickHandler</code> implementiert das Clickhandler 
	 * Interface und dessen dazugeh�rige <code>onClick(ClickEvent event)</code> Methode.
	 * Diese Methode arbeitet mit der Anzahl der Likes auf einem Beitrag und passt die Anzahl auf den 
	 * dazugeh�rigen Beitrag an. 
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
			parentBB.likeCountText.setText(" auf diesem Beitrag: " + parentBB.likeCount);
			GWT.log("Like Count is: " + parentBB.likeCount);
		}
	}
	
	private class EditBeitragBoxClickHandler implements ClickHandler {
		private BeitragBox parentBB;
		
		public EditBeitragBoxClickHandler(BeitragBox bb) {
			parentBB = bb;
		}
		
		public void onClick(ClickEvent event) {
			EditBeitragDialogBox dlg = new EditBeitragDialogBox(parentBB);
			dlg.center();
		}
	}
	
	/**
	 * Die innere Klasse <code>EditBeitragDialogBox</code> implementiert das Clickhandler 
	 * Interface und dessen dazugeh�rige <code>onClick(ClickEvent event)</code> Methode.
	 * Diese Methode ist daf�r zust�ndig die Editierung eines Beitrags zu erm�glichen.
	 * @author Adam Gniady
	 *
	 */
	private class EditBeitragDialogBox extends DialogBox implements ClickHandler {
		BeitragBox parentBB;
		
		public EditBeitragDialogBox(BeitragBox bb) {
			parentBB = bb;
			
			setText("Editiere deinen Beitrag");


			Button safeButton = new Button("Speichere den Edit", this);
			safeButton.addStyleName("button bg-primary");
			Image cancelImage = new Image("images/SVG/timesCircle.png");
			cancelImage.getElement().setPropertyString("style", "max-width: 25px;");
			cancelImage.addClickHandler(this);
			
			// Creating TextArea and filling it with the content of the "Beitrag".
			String beitragText = parentBB.beitragContent.getText();
			TextArea beitragTextArea = new TextArea();
			beitragTextArea.getElement().setPropertyString("style", "min-width: 590px;");
			beitragTextArea.setText(beitragText);
			HTML msg = new HTML("Hier kannst du deinen Text editieren",true);

			DockPanel dock = new DockPanel();
			dock.setSpacing(6);
			dock.add(beitragTextArea, DockPanel.CENTER);
			dock.add(safeButton, DockPanel.SOUTH);
			dock.add(cancelImage, DockPanel.EAST);
			dock.add(msg, DockPanel.NORTH);
			
			safeButton.addClickHandler(new SafeEditedContentClickHandler(parentBB, beitragTextArea));

			dock.setCellHorizontalAlignment(safeButton, DockPanel.ALIGN_CENTER);
			dock.setWidth("600px");
			setWidget(dock);
		}

		@Override
		public void onClick(ClickEvent event) {
			hide();
		}
		
		private class SafeEditedContentClickHandler implements ClickHandler {
			BeitragBox parentBB;
			TextArea newContent;
			
			public SafeEditedContentClickHandler(BeitragBox bb, TextArea textArea) {
				parentBB = bb;
				newContent = textArea;
			}
			
			@Override
			public void onClick(ClickEvent event) {
				GWT.log(newContent.getValue());
				parentBB.beitragContent.setText(newContent.getValue());
			}
			
		}
	}
}
