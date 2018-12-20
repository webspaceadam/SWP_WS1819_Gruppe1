package de.hdm.softwarePraktikumGruppe1.client.gui;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.*;

/**
 * Die Klasse <code>KommentarBox</code> ist zuständig für die Anzeige eines Kommentars unter 
 * einer BeitragBox.  
 * @author AdamGniady
 *
 */
public class KommentarBox extends FlowPanel {
	// Panels for the Element
	private VerticalPanel parentVerticalPanel = new VerticalPanel();
	private FlowPanel userInfoWrapper = new FlowPanel();
	private FlowPanel creationInfoWrapper = new FlowPanel();
	private FlowPanel contentWrapper = new FlowPanel();
	private HTML hrElement = new HTML("<hr/>");
	
	// Content des Kommentars
	private Label accountName = new Label();
	private Label nickName = new Label();
	private Label kommentarContent = new Label();
	private Label creationDate = new Label();
	
	// Additional Information
	private Image editPenBtn = new Image("images/SVG/pen.svg");
	private BeitragBox parentBeitragBox;
	private Button removeKommentarBtn = new Button("Delete");
	
	public KommentarBox() {
		// Adding Author relationship
		accountName.setText("Johny Smith");
		nickName.setText("@" + "johnnysmith");
		kommentarContent.setText("Some Kommentarstuff inside a Kommentar");
	}
	
	/**
	 * Der Konstruktor übernimmt einen Inhalt und die dazugehörige <em>parentBeitragBox</em>. 
	 * 
	 * @param inhalt
	 * @param parentBB
	 */
	public KommentarBox(String inhalt, BeitragBox parentBB) {
		// Adding Author relationship
		accountName.setText("Johny Smith");
		nickName.setText("@" + "johnnysmith");
		
		// Adding the Content
		kommentarContent.setText(inhalt);
		this.parentBeitragBox = parentBB;
	}
	
	public void onLoad() {
		// Date Stuff
		Date now = new Date();
		DateTimeFormat fmt = DateTimeFormat.getFormat("HH:mm:ss, EEEE, dd MMMM, yyyy");
		String date = fmt.format(now).toString();
		creationDate.setText("Erstellungszeitpunkg: " + date);
		
		this.addStyleName("post_content kommentar_margin");
		parentVerticalPanel.addStyleName("control");
		accountName.addStyleName("is-size-4");
		nickName.addStyleName("is-size-6");
		creationDate.addStyleName("is-size-7");
		userInfoWrapper.addStyleName("grid_box content_margin");
		creationInfoWrapper.addStyleName("content_margin");
		removeKommentarBtn.addStyleName("button is-danger");
		
		// Editierbutton
		editPenBtn.addClickHandler(new EditKommentarBoxClickHandler(this));
		editPenBtn.getElement().setPropertyString("style", "max-width: 20px;");

		userInfoWrapper.add(accountName);
		userInfoWrapper.add(nickName);
		userInfoWrapper.add(editPenBtn);
		creationInfoWrapper.add(creationDate);
		contentWrapper.add(kommentarContent);
		
		parentVerticalPanel.add(userInfoWrapper);
		parentVerticalPanel.add(creationInfoWrapper);
		parentVerticalPanel.add(contentWrapper);
		
		
		this.add(hrElement);
		this.add(parentVerticalPanel);		
	}
	
	private class EditKommentarBoxClickHandler implements ClickHandler {
		KommentarBox parentKB;
		
		public EditKommentarBoxClickHandler(KommentarBox kb) {
			parentKB = kb;
		}
		
		@Override
		public void onClick(ClickEvent event) {
			GWT.log("Works");
			EditKommentarDialogBox dlg = new EditKommentarDialogBox(parentKB);
			dlg.center();
		}
		
	}
	
	private class EditKommentarDialogBox extends DialogBox implements ClickHandler {
		KommentarBox parentKB;

		public EditKommentarDialogBox(KommentarBox kb) {
			parentKB = kb;
			
			setText("Editiere deinen Kommentar");

			Button safeButton = new Button("Speichere den Edit", this);
			safeButton.addStyleName("button bg-primary");
			Image cancelImage = new Image("images/SVG/timesCircle.png");
			cancelImage.getElement().setPropertyString("style", "max-width: 25px;");
			cancelImage.addClickHandler(this);
			
			// Creating TextArea and filling it with the Content of the Kommentar
			String beitragText = parentKB.kommentarContent.getText();
			TextArea beitragTextArea = new TextArea();
			beitragTextArea.getElement().setPropertyString("style", "min-width: 590px;");
			beitragTextArea.setText(beitragText);
			HTML msg = new HTML("Hier kannst du deinen Text editieren", true);

			// Button
			removeKommentarBtn.addClickHandler(new removeKommentarFromParent(parentKB, this));
			
			DockPanel dock = new DockPanel();
			dock.setSpacing(6);
			dock.add(beitragTextArea, DockPanel.CENTER);
			dock.add(safeButton, DockPanel.SOUTH);
			dock.add(cancelImage, DockPanel.EAST);
			dock.add(removeKommentarBtn, DockPanel.EAST);
			dock.add(msg, DockPanel.NORTH);
			
			safeButton.addClickHandler(new SafeEditedKommentarContentClickHandler(parentKB, beitragTextArea));

			dock.setCellHorizontalAlignment(safeButton, DockPanel.ALIGN_CENTER);
			dock.setWidth("600px");
			setWidget(dock);
		}
		
		@Override
		public void onClick(ClickEvent event) {
			hide();
		}
		
		private class SafeEditedKommentarContentClickHandler implements ClickHandler {
			KommentarBox parentKB;
			TextArea newContent;
			
			public SafeEditedKommentarContentClickHandler(KommentarBox kb, TextArea textArea) {
				parentKB = kb;
				newContent = textArea;
			}
			
			@Override
			public void onClick(ClickEvent event) {
				parentKB.kommentarContent.setText(newContent.getValue());
			}
			
		}
		
	}
	
	/**
	 * This Method calls a the <code>deleteKommentar</code> Method in the 
	 * parent BeitragBox. 
	 * 
	 * @author AdamGniady
	 *
	 */
	private class removeKommentarFromParent implements ClickHandler {
		KommentarBox thisKommentarBox;
		EditKommentarDialogBox parentDialogBox;
		
		public removeKommentarFromParent(KommentarBox thisKB, EditKommentarDialogBox kommentarDialogBox) {
			thisKommentarBox = thisKB;
			parentDialogBox = kommentarDialogBox;
		}
		
		@Override
		public void onClick(ClickEvent event) {
			parentBeitragBox.deleteKommentar(thisKommentarBox);
			parentDialogBox.hide();
		}
		
	}
	
}
