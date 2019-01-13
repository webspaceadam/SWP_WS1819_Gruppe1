package de.hdm.softwarePraktikumGruppe1.shared.report;

public class reportTest {
	
	
	/**
	 * Klasse zum Testen von dem Befüllen und Auslesen von Reports.
	 * 
	 * 
	 * @author JakobBenkoe
	 */	
	public static void main(String[] args) {
		
		
		
		/*
		 * Test eines UserReports
		 */
		UserReport userReport = new UserReport();
		
		userReport.setImprint(new SimpleParagraph("Hier ist das Impressum | Nobelstrasse 10"));
		userReport.setTitle("User Report");
		
		//Erzeuge einen header
		CompositeParagraph header = new CompositeParagraph();
		header.addSubParagraph(new SimpleParagraph("Report Über den User []"));
		header.addSubParagraph(new SimpleParagraph("Im Zeitraum von [] bis []"));
		//Fuege den Header zum UserReport Hinzu
		userReport.setHeaderData(header);
		
		
		
		//Erzeuge einen GenericReport welcher Informationen über Abonnenten speichert
		GenericReport abonnentenReport = new GenericReport();
		abonnentenReport.setTitle("Informationen über Abonnenten ([Anzahl])");
		//Erzeuge eine Reihe für einen Abonnenten
		Row ersterAbonnent = new Row();
		ersterAbonnent.addColumn(new Column("Name des Users: []"));
		ersterAbonnent.addColumn(new Column("Abonniert Am: []"));
		//Füge die Reihe dem abonnentenReport
		abonnentenReport.addRow(ersterAbonnent);
		//Füge dem abonnenteReport dem userReport hinzu
		userReport.addSubReport(abonnentenReport);
		
		
		
		//Erzeuge einen GenericReport welcher Informationen über Beiträge speichert
		GenericReport beitraegeReport = new GenericReport();
		beitraegeReport.setTitle("Informationen über Beiträge ([Anzahl])");
		Row ersterBeitrag = new Row();
		ersterBeitrag.addColumn(new Column("Erstelldatum: []"));
		ersterBeitrag.addColumn(new Column("Inhalt: []"));
		//Füge die Reihe dem beitraegeReport hinzu
		beitraegeReport.addRow(ersterBeitrag);
		//Füge die Abonnenteninformationen dem userReport hinzu
		userReport.addSubReport(beitraegeReport);
		
		
		//Erzeuge einen GenericReport welcher Informationen über Likes speichert
		GenericReport likesReport = new GenericReport();
		likesReport.setTitle("Informationen über Likes ([Anzahl])");
		Row ersterLike = new Row();
		ersterLike.addColumn(new Column("Like verteilt am: []"));
		ersterLike.addColumn(new Column("An Beitrag von: []"));
		//Füge die Reihe dem likesReport hinzu
		likesReport.addRow(ersterLike);
		//Füge die Abonnenteninformationen dem userReport hinzu
		userReport.addSubReport(likesReport);
		
	}

}
