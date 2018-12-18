package de.hdm.softwarePraktikumGruppe1.client.gui;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;




/**
 * Die <code>ProfileBox</code>-Klasse ist eine Custom-Widget-Class die daf�r verwendet wird, 
 * um auf der Pinnwand des Users alle wichtigen Informationen �ber seinen Account anzugeigen.
 * Des Weiteren wird erm�glicht, dass der User �ber dieses Widget sein Konto bearbeiten kann. 
 * @author AdamGniady
 * @version 1.0
 */

public class ProfileBox extends FlowPanel {
		// Oberer Teil
	
		// dazugeh�rige Label
		private Label vorName = new Label("Sebastian");
		private Label nachName = new Label("Hermann");
		private Label nickName = new Label("sebmeister");
		private Image editPenBtn = new Image("images/SVG/cog.png");
		private HTML hrElement = new HTML("<hr/>");
		
		// dazugeh�rige wrapper
		private FlowPanel wrapper1 = new FlowPanel();
		private FlowPanel wrapper1_el_links = new FlowPanel();
		private FlowPanel wrapper1_el_rechts = new FlowPanel();
		private FlowPanel accountNameWrapper = new FlowPanel();
		
		// unterer Teil
		private Label aboHeader = new Label("ABBONIERT");
		private Label beitragHeader = new Label("BEITRÄGE");
		private Label likeHeader = new Label("LIKES");
		
		private Label aboCount = new Label("22");
		private Label beitragCount = new Label("123");
		private Label likeCount = new Label("312");
		
		// dazugeh�rige wrapper 2
		private FlowPanel wrapper2 = new FlowPanel();
		private VerticalPanel wrapper2_el_1 = new VerticalPanel();
		private VerticalPanel wrapper2_el_2 = new VerticalPanel();
		private VerticalPanel wrapper2_el_3 = new VerticalPanel();
	
	
		public ProfileBox() {
		}
		
		public ProfileBox(String newVorname, String newNachname, String newNickname) {
			this.vorName.setText(newVorname);
			this.nachName.setText(newNachname);
			this.nickName.setText("@" + newNickname);
		}

		
		public void onLoad() {
			// Adding Styling for ProfileBox
			this.addStyleName("box radiusless");
			
			// Wrapper 1 styling
			wrapper1.addStyleName("grid_box");
			wrapper1_el_links.addStyleName("grid_box_links");
			wrapper1_el_rechts.addStyleName("grid_box_rechts");
			
			
			accountNameWrapper.addStyleName("grid_box_element");
			vorName.addStyleName("title margin_right is-size-4");
			nachName.addStyleName("title margin_right is-size-4");
			accountNameWrapper.add(vorName);
			accountNameWrapper.add(nachName);
			
			editPenBtn.addStyleName("grid_box_element");
			editPenBtn.getElement().setPropertyString("style", "max-width: 25px;");
			editPenBtn.addClickHandler(new EditProfileBoxClickHandler(this));
			
			// nickname styling
			nickName.addStyleName("is-size-5");
			
			// wrapper 2 styling
			wrapper2.addStyleName("grid_box");
			wrapper2_el_1.addStyleName("grid_box_element has-text-centered");
			wrapper2_el_2.addStyleName("grid_box_element has-text-centered");
			wrapper2_el_3.addStyleName("grid_box_element has-text-centered");
			
			aboHeader.addStyleName("heading");
			beitragHeader.addStyleName("heading");
			likeHeader.addStyleName("heading");
			
			aboCount.addStyleName("title");
			beitragCount.addStyleName("title");
			likeCount.addStyleName("title");
			
			// Adding Elements to Wrapper 1
			wrapper1_el_links.add(accountNameWrapper);
			wrapper1_el_rechts.add(editPenBtn);
			wrapper1.add(wrapper1_el_links);
			wrapper1.add(wrapper1_el_rechts);
			
			// Adding Elements to Wrapper 2
			wrapper2_el_1.add(aboHeader);
			wrapper2_el_1.add(aboCount);
			
			wrapper2_el_2.add(beitragHeader);
			wrapper2_el_2.add(beitragCount);
			
			wrapper2_el_3.add(likeHeader);
			wrapper2_el_3.add(likeCount);
			
			wrapper2.add(wrapper2_el_1);
			wrapper2.add(wrapper2_el_2);
			wrapper2.add(wrapper2_el_3);
			
			// Wrapping all up in the end
			this.add(wrapper1);
			this.add(nickName);
			this.add(hrElement);
			this.add(wrapper2);
		}
		
		private class EditProfileBoxClickHandler implements ClickHandler {
			private ProfileBox parentPB;
			
			public EditProfileBoxClickHandler(ProfileBox pb) {
				parentPB = pb;
			}
			
			public void onClick(ClickEvent event) {
				EditProfileBoxDialogBox dlg = new EditProfileBoxDialogBox(parentPB);
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
		public class EditProfileBoxDialogBox extends DialogBox implements ClickHandler {
			ProfileBox parentPB;
			
			public EditProfileBoxDialogBox(ProfileBox pb) {
				parentPB = pb;
				Image cancelImage = new Image("images/SVG/timesCircle.png");
				cancelImage.getElement().setPropertyString("style", "max-width: 25px;");
				cancelImage.addClickHandler(this);
				
				// Creating TextArea and filling it with the content of the "Beitrag".
				EditAccountForm editForm = new EditAccountForm(parentPB, this);

				DockPanel dock = new DockPanel();
				dock.setSpacing(6);
				dock.add(editForm, DockPanel.CENTER);
				dock.add(cancelImage, DockPanel.EAST);

				dock.setWidth("600px");
				setWidget(dock);
			}

			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
			
			private class SafeEditedContentClickHandler implements ClickHandler {
				
				public SafeEditedContentClickHandler(BeitragBox bb, TextArea textArea) {
				}
				
				@Override
				public void onClick(ClickEvent event) {
				}
				
			}
		}
		
		public void setNickname(String newNickname) {
			this.nickName.setText(newNickname);
		}
		
		public void setVorname(String newVorname) {
			this.vorName.setText(newVorname);
		}
		
		public void setNachname(String newNachname) {
			this.nachName.setText(newNachname);
		}
		
		public String getNickname() {
			return this.nickName.getText();
		}
		
		public String getVorname() {
			return this.vorName.getText();
		}
		
		public String getNachname() {
			return this.nachName.getText();
		}
}
