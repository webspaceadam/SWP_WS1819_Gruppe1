/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.server;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.softwarePraktikumGruppe1.server.db.AbonnementMapper;
import de.hdm.softwarePraktikumGruppe1.server.db.BeitragMapper;
import de.hdm.softwarePraktikumGruppe1.server.db.KommentarMapper;
import de.hdm.softwarePraktikumGruppe1.server.db.LikeMapper;
import de.hdm.softwarePraktikumGruppe1.server.db.PinnwandMapper;
import de.hdm.softwarePraktikumGruppe1.server.db.UserMapper;
import de.hdm.softwarePraktikumGruppe1.shared.ReportGeneratorService;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Abonnement;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;
import de.hdm.softwarePraktikumGruppe1.shared.bo.User;
import de.hdm.softwarePraktikumGruppe1.shared.report.BeitragReport;
import de.hdm.softwarePraktikumGruppe1.shared.report.Column;
import de.hdm.softwarePraktikumGruppe1.shared.report.CompositeParagraph;
import de.hdm.softwarePraktikumGruppe1.shared.report.GenericReport;
import de.hdm.softwarePraktikumGruppe1.shared.report.Row;
import de.hdm.softwarePraktikumGruppe1.shared.report.SimpleParagraph;
import de.hdm.softwarePraktikumGruppe1.shared.report.UserReport;



/**
 * @author GianlucaBernert
 * @author JakobBenkoe
 * Serverseite Implementierung des ReportGeneratorService
 */
public class ReportGeneratorServiceImpl extends RemoteServiceServlet implements ReportGeneratorService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static  SimpleDateFormat yearMonthDayFormat = new SimpleDateFormat ("yyyy.MM.dd");
	public final static SimpleDateFormat dayMonthYearFormat = new SimpleDateFormat ("dd.MM.yyyy");

	Date d = null;
	Date d2 = null;
	
	public void init() throws IllegalArgumentException {

	  }

	
	/*
	 * Hier wird der UserReport erstellt
	 */
	@Override
	public UserReport createUserReport(User user, Date start, Date end) throws IllegalArgumentException {
		UserReport userReport = new UserReport();
		System.out.println("iser");
		
		userReport.setImprint(new SimpleParagraph("Hier ist das Impressum | Nobelstrasse 10"));
		userReport.setTitle("User Report");
		//Erzeuge einen header
		CompositeParagraph header = new CompositeParagraph();
		header.addSubParagraph(new SimpleParagraph("Report Über den User " + user.getNickname()));
		header.addSubParagraph(new SimpleParagraph("Vorname: " + user.getFirstName() + "Nachname: " + user.getLastName()));
		header.addSubParagraph(new SimpleParagraph("eMail Adresse: " + user.getGMail()));
		header.addSubParagraph(new SimpleParagraph("Im Zeitraum von " + dayMonthYearFormat.format(start) + 
				"bis " + dayMonthYearFormat.format(end)));
		//Fuege den Header zum UserReport Hinzu
		userReport.setHeaderData(header);

		
		UserMapper uMapper = UserMapper.userMapper();	
		BeitragMapper beitragMapper = BeitragMapper.beitragMapper();
		AbonnementMapper abonnementMapper = AbonnementMapper.abonnementMapper();
		
		

		try {
			d = dayMonthYearFormat.parse("17.07.1999");
			d2 = dayMonthYearFormat.parse("17.07.2020");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//Abonnements
		Vector<Abonnement> abonnements = abonnementMapper.findAbonnementsOfUserBetweenDates(1, d, d2);
		//Erzeuge einen GenericReport welcher Informationen über Abonnenten speichert
		GenericReport abonnentenReport = new GenericReport();
		
		if (abonnements == null) {
			abonnentenReport.setTitle("Informationen über Abonnenten (0)");

		}else {
			abonnentenReport.setTitle("Informationen über Abonnenten (" + (abonnements.size() + 1) + ")");
			for(int i = 0; i < abonnements.size(); i++) {
				Abonnement abonnement = abonnements.get(i);
				//Erzeuge eine Reihe für einen Abonnenten
				Row row = new Row();
				User abonnent = uMapper.findUserById(abonnement.getOwnerId());
				row.addColumn(new Column("Nickname des Users: " + abonnent.getNickname()));
				row.addColumn(new Column("Vorname: " + abonnent.getFirstName() + "Nachname: " + abonnent.getLastName()));
				row.addColumn(new Column("Abonniert Am: " + abonnement.getCreationTimeStamp().toString()));
				//Füge die Reihe dem abonnentenReport
				abonnentenReport.addRow(row);

			}
		}
		
		
		
		
		//Beiträge
		Vector<Beitrag> beitraege = beitragMapper.findBeitraegeOfUserBetweenDates(1, d, d2);

		//Erzeuge einen GenericReport welcher Informationen über Beiträge speichert
		GenericReport beitraegeReport = new GenericReport();
		
		System.out.println(beitraege.get(0).getInhalt());
		
		if (beitraege == null) {
			beitraegeReport.setTitle("Informationen über Beiträge (0)");
		}else {
			beitraegeReport.setTitle("Informationen über Beiträge (" + (beitraege.size() + 1) + ")");
			for(int i = 0; i < beitraege.size(); i++) {
				Beitrag beitrag = beitraege.get(i);
				Row row = new Row();
				row.addColumn(new Column("Erstelldatum: " + beitrag.getCreationTimeStamp().toString()));
				row.addColumn(new Column("Inhalt: " + beitrag.getInhalt()));
				//Füge die Reihe dem beitraegeReport hinzu
				beitraegeReport.addRow(row);
				//Füge die Abonnenteninformationen dem userReport hinzu
				userReport.addSubReport(beitraegeReport);
			}
		}
		
		
		return userReport;
	}

	
	
	
	
	
	
	/*
	 * Hier wird der BeitragsReport erstellt
	 */
	@Override
	public BeitragReport createBeitragReport() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

}
