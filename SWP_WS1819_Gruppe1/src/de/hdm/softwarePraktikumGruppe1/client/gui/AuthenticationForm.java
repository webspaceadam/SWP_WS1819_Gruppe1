package de.hdm.softwarePraktikumGruppe1.client.gui;

import com.google.gwt.user.client.ui.*;

/**
 * Die <code>AuthenticationForm</code>-Klasse ist zuständig für
 * die Zugangsberechtigungsüberprüfung der User in das Social-Media-Pinnwand
 * System. 
 * 
 * @author AdamGniady
 *
 */
public class AuthenticationForm extends FlowPanel {
	private Label welcomeLabel = new Label("Zugang �ber dein Google Konto");
	private Button googleBtn = new Button();
	private Image imgGoogle = new Image("images/googleLogo.png");
	private Image pinnersLogo = new Image("images/SVG/standard_logo.svg");
	
	public AuthenticationForm() {
	}
	
	public void onLoad() {
		this.addStyleName("box radiusless");
		this.getElement().setAttribute("style", "width: 500px; height:350px; text-align: center;");
		imgGoogle.getElement().setAttribute("style", "width: 35px;");
		pinnersLogo.addStyleName("image_style is_128");
		pinnersLogo.getElement().setAttribute("style", "padding: 30px; display: inline-block;");
		
		
		welcomeLabel.addStyleName("title is-size-4 has-text-primary");
		welcomeLabel.getElement().setAttribute("style", "display: inline_block;");
		googleBtn.addStyleName("button is-block is-large is-fullwidth");
		googleBtn.getElement().appendChild(imgGoogle.getElement());
		googleBtn.getElement().setAttribute("style", "display: inline-block;");
		
		this.add(pinnersLogo);
		this.add(welcomeLabel);
		this.add(googleBtn);
	}
}
