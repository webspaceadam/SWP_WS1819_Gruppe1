package de.hdm.softwarePraktikumGruppe1.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

/**
 * Die <code>AuthenticationForm</code>-Klasse ist zuständig für
 * die Zugangsberechtigungsüberprüfung der User in das Social-Media-Pinnwand
 * System. 
 * 
 * @author AdamGniady
 * @author JakobBenkoe
 *
 */
public class AuthenticationForm extends FlowPanel {
	private Label welcomeLabel = new Label("Zugang über dein Google Konto");
	private Button googleBtn = new Button();
	private Image imgGoogle = new Image("images/googleLogo.png");
	private Image pinnersLogo = new Image("images/SVG/standard_logo.svg");
	private String loginURL;
	
	public AuthenticationForm() {
	}
	
	public AuthenticationForm(String loginURL) {
		this.loginURL = loginURL;		
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
		
		googleBtn.addClickHandler(new loginClickHandler());
	}
	
	
	//ClickHandler für Login Button
	private class loginClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			
			//Leite User zum Google Login weiter
			Window.Location.assign(loginURL);
		}
		
	}

}
