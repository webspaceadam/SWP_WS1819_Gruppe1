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
import de.hdm.softwarePraktikumGruppe1.shared.bo.Like;
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

	Date start = null;
	Date end = null;
	
	
	UserMapper uMapper = UserMapper.userMapper();	
	BeitragMapper beitragMapper = BeitragMapper.beitragMapper();
	AbonnementMapper abonnementMapper = AbonnementMapper.abonnementMapper();
	LikeMapper likeMapper = LikeMapper.likeMapper();
	
	
	public void init() throws IllegalArgumentException {

	  }

	
	/*
	 * Hier wird der UserReport erstellt
	 */
	@Override
	public UserReport createUserReport(User user, Date date1, Date date2) throws IllegalArgumentException {
		//make sure start date is before end date
		if(date1.before(date2)) {
			start = date1;
			end = date2;
		}else {
			start = date2;
			end = date1;
		}
	
		//create Report
		UserReport userReport = new UserReport();
		userReport.setImprint(new SimpleParagraph("Pinners | Nobelstrasse 10"));
		userReport.setTitle("User Report");
		//Erzeuge einen header
		CompositeParagraph header = new CompositeParagraph();
		header.addSubParagraph(new SimpleParagraph("Report Über den User: " + user.getNickname()));
		header.addSubParagraph(new SimpleParagraph("Vorname: " + user.getFirstName() + "Nachname: " + user.getLastName()));
		header.addSubParagraph(new SimpleParagraph("eMail Adresse: " + user.getGMail()));
		header.addSubParagraph(new SimpleParagraph("Report Im Zeitraum vom " + dayMonthYearFormat.format(start) + 
				" bis " + dayMonthYearFormat.format(end) + " (0 Uhr jeweils)"));
		//Fuege den Header zum UserReport Hinzu
		userReport.setHeaderData(header);

	
		
		//Abonnements
		Vector<Abonnement> abonnements = abonnementMapper.findAbonnementsOfUserBetweenDates(1, start, end);
		//Erzeuge einen GenericReport welcher Informationen über Abonnenten speichert
		GenericReport abonnentenReport = new GenericReport();
		
		if (abonnements.size() == 0) {
			abonnentenReport.setTitle("Informationen über Abonnenten (0)");
			Row row = new Row();
			row.addColumn(new Column("Keine Abonnenten in dem angegebenen Zeitraum gefunden"));
			abonnentenReport.addRow(row);
		}else {
			abonnentenReport.setTitle("Informationen über Abonnenten (" + abonnements.size() + ")");
			for(int i = 0; i < abonnements.size(); i++) {
				Abonnement abonnement = abonnements.get(i);
				//Erzeuge eine Reihe für einen Abonnenten
				Row row = new Row();
				User abonnent = uMapper.findUserById(abonnement.getOwnerId());
				row.addColumn(new Column("Nickname des Users: " + abonnent.getNickname()));
				row.addColumn(new Column("Vorname: " + abonnent.getFirstName() + "Nachname: " + abonnent.getLastName()));
				//row.addColumn(new Column("Abonniert Am: " + abonnement.getCreationTimeStamp().toString()));
				row.addColumn(new Column("Abonnement ID:" + abonnement.getAbonnementId()));
				//Füge die Reihe dem abonnentenReport
				abonnentenReport.addRow(row);
			}
		}
		//Füge die Abonnenteninformationen dem userReport hinzu
		userReport.addSubReport(abonnentenReport);
		
				
		//Beiträge
		Vector<Beitrag> beitraege = beitragMapper.findBeitraegeOfUserBetweenDates(1, start, end);

		//Erzeuge einen GenericReport welcher Informationen über Beiträge speichert
		GenericReport beitraegeReport = new GenericReport();
		
		
		if (beitraege.size() == 0) {
			beitraegeReport.setTitle("Informationen über Beiträge (0)");
			Row row = new Row();
			row.addColumn(new Column("Keine Beiträge in dem angegebenen Zeitraum gefunden"));
			beitraegeReport.addRow(row);
		}else {
			beitraegeReport.setTitle("Informationen über Beiträge (" + beitraege.size() + ")");
			for(int i = 0; i < beitraege.size(); i++) {
				Beitrag beitrag = beitraege.get(i);
				Row row = new Row();
				row.addColumn(new Column("Erstelldatum: " + beitrag.getCreationTimeStamp().toString()));
				row.addColumn(new Column("Inhalt: " + beitrag.getInhalt()));
				row.addColumn(new Column("Beitrag ID:" + beitrag.getBeitragId()));
				//Füge die Reihe dem beitraegeReport hinzu
				beitraegeReport.addRow(row);
			}
		}
		//Füge die Abonnenteninformationen dem userReport hinzu
		userReport.addSubReport(beitraegeReport);
		
		
		//Likes
		Vector<Like> likes = likeMapper.findLikesOfBeitragBetweenDates(1, start, end);
		//Erzeuge einen GenericReport welcher Informationen über Abonnenten speichert
		GenericReport likeReport = new GenericReport();
				
		if (likes.size() == 0) {
			likeReport.setTitle("Informationen über Likes (0)");
			Row row = new Row();
			row.addColumn(new Column("Keine Likes in dem angegebenen Zeitraum gefunden"));
			likeReport.addRow(row);
		}else {
			likeReport.setTitle("Informationen über Likes(" + likes.size() + ")");
			for(int i = 0; i < likes.size(); i++) {
				Like like = likes.get(i);
				//Erzeuge eine Reihe für einen Abonnenten
				Row row = new Row();
				Beitrag gelikterBeitrag = beitragMapper.findBeitragById(like.getBeitragId());
				User gelikterUser = uMapper.findUserById(gelikterBeitrag.getOwnerId());
				row.addColumn(new Column("Like verteilt am: " + like.getCreationTimeStamp().toString()));
				if(gelikterBeitrag != null)row.addColumn(new Column("An Beitrag von: " + gelikterUser.getNickname()));
				row.addColumn(new Column("Like ID:" + like.getLikeId()));
				//Füge die Reihe dem abonnentenReport
				likeReport.addRow(row);
				}
			}
		//Füge die Likesinformationen dem userReport hinzu
		userReport.addSubReport(likeReport);
		
		
		return userReport;
	}

	
	
	
	
	
	
	/*
	 * Hier wird der BeitragsReport erstellt
	 */
	@Override
	public BeitragReport createBeitragReport(int beitragID, Date date1, Date date2) throws IllegalArgumentException {
		//make sure start date is before end date
		if(date1.before(date2)) {
			start = date1;
			end = date2;
		}else {
			start = date2;
			end = date1;
		}
	
		//create Report
		BeitragReport beitragReport = new BeitragReport();
		beitragReport.setImprint(new SimpleParagraph("Pinners | Nobelstrasse 10"));
		beitragReport.setTitle("Beitrag Report");
		//Erzeuge einen header
		CompositeParagraph header = new CompositeParagraph();
		header.addSubParagraph(new SimpleParagraph("Report Über den Beitrag: "));
		header.addSubParagraph(new SimpleParagraph("Erstellt von: "));
		header.addSubParagraph(new SimpleParagraph("Erstellt am: "));
		header.addSubParagraph(new SimpleParagraph("Report Im Zeitraum vom " + dayMonthYearFormat.format(start) + 
				" bis " + dayMonthYearFormat.format(end)  + " (0 Uhr jeweils)"));
		//Fuege den Header zum UserReport Hinzu
		beitragReport.setHeaderData(header);
		
		
		
		return beitragReport;
	}

}
