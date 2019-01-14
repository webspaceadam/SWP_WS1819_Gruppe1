/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.server;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.softwarePraktikumGruppe1.server.db.BeitragMapper;
import de.hdm.softwarePraktikumGruppe1.server.db.UserMapper;
import de.hdm.softwarePraktikumGruppe1.shared.ReportGeneratorService;
import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;
import de.hdm.softwarePraktikumGruppe1.shared.bo.User;
import de.hdm.softwarePraktikumGruppe1.shared.report.BeitragReport;
import de.hdm.softwarePraktikumGruppe1.shared.report.UserReport;



/**
 * @author GianlucaBernert
 * @author JakobBenkoe
 * Serverseite Implementierung des ReportGeneratorService
 */
public class ReportGeneratorServiceImpl extends RemoteServiceServlet implements ReportGeneratorService{


	private static final long serialVersionUID = 1L;
	public final static  SimpleDateFormat yearMonthDayFormat = new SimpleDateFormat ("yyyy.MM.dd");
	public final static SimpleDateFormat dayMonthYearFormat = new SimpleDateFormat ("dd.MM.yyyy");
	
	public void init() throws IllegalArgumentException {

	  }

	
	/*
	 * Hier wird der UserReport erstellt
	 */
	@Override
	public UserReport createUserReport() throws IllegalArgumentException {
		System.out.println("iser");
		UserReport userReport = new UserReport();

		
		UserMapper uMapper = UserMapper.userMapper();
		
		
		BeitragMapper beitragMapper = BeitragMapper.beitragMapper();
		
		Date d = null;
		Date d2 = null;
		try {
			d = dayMonthYearFormat.parse("17.07.1999");
			d2 = dayMonthYearFormat.parse("17.07.2020");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Vector <Beitrag> beitraege = beitragMapper.findBeitraegeOfUserBetweenDates(1, d, d2);
		
		System.out.println(beitraege.get(0).getInhalt());
		
		
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
