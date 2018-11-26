package de.hdm.softwarePraktikumGruppe1.client.gui;
import com.google.gwt.user.client.ui.*;

/**
 * Die <code>ProfileBox</code>-Klasse ist eine Custom-Widget-Class die dafür verwendet wird, 
 * um auf der Pinnwand des Users alle wichtigen Informationen über seinen Account anzugeigen.
 * Des Weiteren wird ermöglicht, dass der User über dieses Widget sein Konto bearbeiten kann. 
 * @author Adam Gniady
 * @version 1.0
 */

public class ProfileBox extends FlowPanel {
		// Oberer Teil
	
		// dazugehörige Label
		private Label accountName = new Label("Sebastian Hermann");
		private Label nickName = new Label("@sebmeister");
		private Button editAccountButton = new Button("Konto Bearbeiten");
		private HTML hrElement = new HTML("<hr/>");
		// dazugehörige wrapper
		private FlowPanel wrapper1 = new FlowPanel();
		private FlowPanel wrapper1_el_links = new FlowPanel();
		private FlowPanel wrapper1_el_rechts = new FlowPanel();
		
		
		// unterer Teil
		private Label aboHeader = new Label("ABBONIERT");
		private Label beitragHeader = new Label("BEITRÄGE");
		private Label likeHeader = new Label("LIKES");
		
		private Label aboCount = new Label("22");
		private Label beitragCount = new Label("123");
		private Label likeCount = new Label("312");
		
		// dazugehörige wrapper 2
		private FlowPanel wrapper2 = new FlowPanel();
		private VerticalPanel wrapper2_el_1 = new VerticalPanel();
		private VerticalPanel wrapper2_el_2 = new VerticalPanel();
		private VerticalPanel wrapper2_el_3 = new VerticalPanel();
	
	
		public ProfileBox() {
			// Adding Styling for ProfileBox
			this.addStyleName("box radiusless");
			
			// Wrapper 1 styling
			wrapper1.addStyleName("grid_box");
			wrapper1_el_links.addStyleName("grid_box_links");
			wrapper1_el_rechts.addStyleName("grid_box_rechts");
			
			accountName.addStyleName("title is-size-4 grid_box_element");
			editAccountButton.addStyleName("grid_box_element button bg-primary");
			
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
			wrapper1_el_links.add(accountName);
			wrapper1_el_rechts.add(editAccountButton);
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

		
		/**
		 * In dieser Methode werden die Desings der Buttons festgelegt. Auch
		 * die Kontakt-Editor und ReportGenerator-Buttons werden zum Kopfbereich
		 * des Kontaktverwaltungstools hinzugefügt. 
		 */
		public void onLoad() {
		}
}
