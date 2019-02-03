/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.softwarePraktikumGruppe1.shared.bo.Beitrag;
import de.hdm.softwarePraktikumGruppe1.shared.bo.User;
import de.hdm.softwarePraktikumGruppe1.shared.report.BeitragReport;
import de.hdm.softwarePraktikumGruppe1.shared.report.UserReport;



/**
 * 
 * @author JakobBenkoe
 * Interface das von ReportGeneratorServiceProxy Implementiert wird
 */
public interface ReportGeneratorServiceAsync {

		/**
		 * Methode um den User Report zu erzeugen
		 * @param gMail
		 * @param start
		 * @param end
		 * @param callback
		 */
	
		void createUserReport(String gMail, Date start, Date end, AsyncCallback<UserReport> callback);
		
		/**
		 * Methode um den BeitragReport zu erzeugen
		 * @param beitragID
		 * @param date1
		 * @param date2
		 * @param callback
		 */
		
		void createBeitragReport(int beitragID, Date date1, Date date2, AsyncCallback<BeitragReport> callback);
		
		/**
		 * Methode um den User zu suchen
		 * @param searchQuery
		 * @param callback
		 */
		void searchUserFunction(String searchQuery, AsyncCallback<Vector<User>> callback);
		
		/**
		 * Methode um den Beitrag zu suchen
		 * @param gMail
		 * @param callback
		 */
		
		void searchBeitragFunction(String gMail, AsyncCallback<Vector<Beitrag>> callback);

}
