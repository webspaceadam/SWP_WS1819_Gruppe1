package de.hdm.softwarePraktikumGruppe1.shared.report;

public class reportTest {
	
	
	/**
	 * Klasse zum Testen von dem Befüllen und Auslesen von Reports.
	 * 
	 * 
	 * @author JakobBenkoe
	 */
	
	public static void main(String[] args) {
		
		
		
		
		UserReport userReport = new UserReport();
		
		userReport.setImprint(new SimpleParagraph("Hier ist das Impressum | Nobelstrasse 10"));
		userReport.setHeaderData(new SimpleParagraph("Hier sind die Kopfdaten | z.B. Angelegt an"));
		
		userReport.setTitle("User Report");
		

	}

}
