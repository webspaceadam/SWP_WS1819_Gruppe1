/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.server;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
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
import de.hdm.softwarePraktikumGruppe1.shared.bo.Kommentar;
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
	public final static SimpleDateFormat dayMonthYearTimeFormat = new SimpleDateFormat ("dd.MM.yyyy HH:mm");
	
	public final static String trenner = "------------------";
	
	Date start = null;
	Date end = null;
	
	
	UserMapper uMapper = UserMapper.userMapper();	
	BeitragMapper beitragMapper = BeitragMapper.beitragMapper();
	AbonnementMapper abonnementMapper = AbonnementMapper.abonnementMapper();
	LikeMapper likeMapper = LikeMapper.likeMapper();
	KommentarMapper kommentarMaper = KommentarMapper.kommentarMapper();
	
	
	public void init() throws IllegalArgumentException {

	  }

	
	/*
	 * Hier wird der UserReport erstellt
	 */
	@Override
	public UserReport createUserReport(String gmail, Date date1, Date date2) throws IllegalArgumentException {
		//create Report
		UserReport userReport = new UserReport();
		//make sure start date is before end date
		if(date1.before(date2)) {
			start = date1;
			end = date2;
		}else {
			start = date2;
			end = date1;
		}
		userReport.setImprint(new SimpleParagraph("Report über den Zeitraum vom " + dayMonthYearFormat.format(start) + 
				" bis zum " + dayMonthYearFormat.format(end)));
		
		//user of the userReport
		User user = null;
		int userID;
		//Make Sure Beitrag with selected ID exists
		try {
			user = uMapper.findUserByGmail(gmail);
			userReport.setTitle("Report Über den User " + user.getNickname());
			userID = user.getUserId();
		//Otherwise return report that indicates the missing beitrag	
		}catch(Exception e){
			CompositeParagraph header = new CompositeParagraph();
			header.addSubParagraph(new SimpleParagraph("Keinen User mit der eMail [ " + gmail + " ] gefunden."));
			header.addSubParagraph(new SimpleParagraph("Bitte gebe eine existierende User eMail an"));
			userReport.setHeaderData(header);
			return userReport;
		}
	

		//Erzeuge einen header
		CompositeParagraph header = new CompositeParagraph();
		header.addSubParagraph(new SimpleParagraph("Vorname: " + user.getFirstName()));
		header.addSubParagraph(new SimpleParagraph("Nachname: " + user.getLastName()));
		header.addSubParagraph(new SimpleParagraph("Nickname: " + user.getNickname()));
		header.addSubParagraph(new SimpleParagraph("eMail Adresse: " + user.getGMail()));
		//Fuege den Header zum UserReport Hinzu
		userReport.setHeaderData(header);

	
		
		//Abonnements
		Vector<Abonnement> abonnements = abonnementMapper.findAbonnementsOfUserBetweenDates(userID, start, end);
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
				Row row = new Row(true);
				User abonnent = uMapper.findUserById(abonnement.getOwnerId());
				abonnentenReport.addRow(new Row(new Column("")));
				row.addColumn(new Column("Nickname des Users: " + abonnent.getNickname()));
				row.addColumn(new Column("eMail des User: " + abonnent.getGMail()));
				row.addColumn(new Column("Abonniert am: " + dayMonthYearTimeFormat.format(abonnement.getCreationTimeStamp()).toString()));
				//Füge die Reihe dem abonnentenReport
				abonnentenReport.addRow(row);
			}
		}
		//Füge die Abonnenteninformationen dem userReport hinzu
		userReport.addSubReport(abonnentenReport);
		
				
		//Beiträge
		Vector<Beitrag> beitraege = beitragMapper.findBeitraegeOfUserBetweenDates(userID, start, end);

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
				row.addColumn(new Column(dayMonthYearTimeFormat.format(beitrag.getCreationTimeStamp())));
				beitraegeReport.addRow(row);
				row = new Row(true);
				row.addColumn(new Column(beitrag.getInhalt()));
				//Füge die Reihe dem beitraegeReport hinzu
				beitraegeReport.addRow(row);
			}
		}
		//Füge die Abonnenteninformationen dem userReport hinzu
		userReport.addSubReport(beitraegeReport);
		
		
		//Likes
		Vector<Like> likes = likeMapper.findLikesOfUserBetweenDates(userID, start, end);
		//Erzeuge einen GenericReport welcher Informationen über Likes speichert
		GenericReport likeReport = new GenericReport();
				
		if (likes.size() == 0) {
			likeReport.setTitle("Informationen über Likes (0)");
			Row row = new Row();
			row.addColumn(new Column("Keine Likes in dem angegebenen Zeitraum gefunden"));
			likeReport.addRow(row);
		}else {
			likeReport.setTitle("Informationen über Likes (" + likes.size() + ")");
			for(int i = 0; i < likes.size(); i++) {
				Like like = likes.get(i);
				//Erzeuge eine Reihe für einen Abonnenten
				Row row = new Row(true);
				Beitrag gelikterBeitrag = beitragMapper.findBeitragById(like.getBeitragId());
				User gelikterUser = uMapper.findUserById(gelikterBeitrag.getOwnerId());
				row.addColumn(new Column("Like verteilt am: " + dayMonthYearTimeFormat.format(like.getCreationTimeStamp()).toString()));
				if(gelikterBeitrag != null)row.addColumn(new Column("An Beitrag von: " + gelikterUser.getGMail()));
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
		//create result Report
		BeitragReport beitragReport = new BeitragReport();
		beitragReport.setTitle("Report Über den Beitrag mit der ID " + beitragID);
		//make sure start date is before end date
		if(date1.before(date2)) {
			start = date1;
			end = date2;
		}else {
			start = date2;
			end = date1;
		}
		beitragReport.setImprint(new SimpleParagraph("Report über den Zeitraum vom " + dayMonthYearFormat.format(start) + 
				" bis zum " + dayMonthYearFormat.format(end)));

		Beitrag beitrag = null;
		User inhaber = null;
		
		//Make Sure Beitrag with selected ID exists
		try {
			beitrag = beitragMapper.findBeitragById(beitragID);
			inhaber = uMapper.findUserById(beitrag.getOwnerId());
			
		//Otherwise return report that indicates the missing beitrag	
		}catch(Exception e){	
			CompositeParagraph header = new CompositeParagraph();
			header.addSubParagraph(new SimpleParagraph("Keinen Beitrag mit der ID [ " + beitragID + " ] gefunden."));
			header.addSubParagraph(new SimpleParagraph("Bitte gebe eine existierende Beitrags ID an"));
			beitragReport.setHeaderData(header);
			return beitragReport;
		}
		
		
		

			//Create header
			CompositeParagraph header = new CompositeParagraph();
				try {
					header.addSubParagraph(new SimpleParagraph("Beitrag erstellt von: " +  inhaber.getFirstName() + " " + inhaber.getLastName()));
					header.addSubParagraph(new SimpleParagraph("Autor Nickname: " + inhaber.getNickname()));
					header.addSubParagraph(new SimpleParagraph("Autor eMail: " + inhaber.getGMail()));					
				}catch(Exception e) {
					header.addSubParagraph(new SimpleParagraph("Beitrag erstellt von: Zu diesem Beitrag konnte kein Autor gefunden werden"));
				}
			header.addSubParagraph(new SimpleParagraph(""));
			header.addSubParagraph(new SimpleParagraph("Beitrag erstellt am:  " + dayMonthYearTimeFormat.format(beitrag.getCreationTimeStamp()).toString()));
			header.addSubParagraph(new SimpleParagraph(beitrag.getInhalt()));
			//Add header to result report
			beitragReport.setHeaderData(header);
			
			
			
			//Kommentare
			Vector<Kommentar> kommentare = kommentarMaper.findKommentareOfBeitrag(beitragID, start, end);
			//Erzeuge einen GenericReport welcher Informationen über Kommentare speichert
			GenericReport kommentarReport = new GenericReport();
					
			if (kommentare.size() == 0) {
				kommentarReport.setTitle("Informationen über Kommentare (0)");
				Row row = new Row();
				row.addColumn(new Column("Keine Kommentare in dem angegebenen Zeitraum gefunden"));
				kommentarReport.addRow(row);
			}else {
				kommentarReport.setTitle("Informationen über Kommentare (" + kommentare.size() + ")");
				for(int i = 0; i < kommentare.size(); i++) {
					Kommentar kommentar = kommentare.get(i);
					//Erzeuge eine Reihe für einen Abonnenten
					Row row = new Row(true);
					User autor = uMapper.findUserById(kommentar.getOwnerId()); 
					row.addColumn(new Column("Autor: " + autor.getNickname()));
					row.addColumn(new Column("Erstellungsdatum: " + dayMonthYearTimeFormat.format(kommentar.getCreationTimeStamp())));
					row.addColumn(new Column("Inhalt: " + kommentar.getInhalt()));
					//Füge die Reihe dem abonnentenReport
					kommentarReport.addRow(row);
					}
				}
			//Füge die Likesinformationen dem userReport hinzu
			beitragReport.addSubReport(kommentarReport);
			
			
			//Likes
			Vector<Like> likes = likeMapper.findLikesOfBeitragBetweenDates(beitragID, start, end);
			//Erzeuge einen GenericReport welcher Informationen über Likes speichert
			GenericReport likeReport = new GenericReport();
					
			if (likes.size() == 0) {
				likeReport.setTitle("Informationen über Likes (0)");
				Row row = new Row();
				row.addColumn(new Column("Keine Likes in dem angegebenen Zeitraum gefunden"));
				likeReport.addRow(row);
			}else {
				likeReport.setTitle("Informationen über Likes (" + likes.size() + ")");
				for(int i = 0; i < likes.size(); i++) {
					Like like = likes.get(i);
					//Erzeuge eine Reihe für einen Abonnenten
					Row row = new Row(true);
					User likeUser = uMapper.findUserById(like.getOwnerId());
					row.addColumn(new Column("Like erhalten am: " + dayMonthYearTimeFormat.format(like.getCreationTimeStamp()).toString()));
					row.addColumn(new Column("Like erhalten von von: " + likeUser.getNickname()));
					//Füge die Reihe dem abonnentenReport
					likeReport.addRow(row);
					}
				}
		//Füge die Likesinformationen dem userReport hinzu
		beitragReport.addSubReport(likeReport);
		
		
		//return result report
		return beitragReport;
	}
	
	
	
	
	/**Methode um einen User zu suchen
	 * @param searchQuery
	 * @return users gibt die User zurück die gefunden wurden.
	 */
	

	public Vector<User> searchUserFunction(String searchQuery){
		HashSet<User> hs = new HashSet<User>();
		Vector<User> users = new Vector<User>();
		String s = searchQuery;
		if(uMapper.findUserByFirstName(s) != null)hs.addAll(uMapper.findUserByFirstName(s));
		if(uMapper.findUserByLastName(s) != null)hs.addAll(uMapper.findUserByLastName(s));
		if(uMapper.findUserByNickname(s) != null)hs.addAll(uMapper.findUserByNickname(s));
		if(uMapper.findUserByGmail(s) != null)hs.add(uMapper.findUserByGmail(s));
		
		Iterator<User> it = hs.iterator();
	     while(it.hasNext()){
	        users.add(it.next());
	     }
		
		return users;
	}
	
	
	
	@Override
	public Vector<Beitrag> searchBeitragFunction(String gMail) {

		User tempUser = uMapper.findUserByGmail(gMail);
		if (tempUser != null)return beitragMapper.findBeitraegeOfUser(tempUser.getUserId());
		return null;
	}
}
