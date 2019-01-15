/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.softwarePraktikumGruppe1.shared.bo.User;
import de.hdm.softwarePraktikumGruppe1.shared.report.BeitragReport;
import de.hdm.softwarePraktikumGruppe1.shared.report.UserReport;



/**
 * @author GianlucaBernert & JakobBenkoe
 * Interface das von ReportGeneratorServiceProxy Implementiert wird
 */
public interface ReportGeneratorServiceAsync {

		void init(AsyncCallback<Void> initReportGeneratorCallback);

		void createUserReport(User user, Date start, Date end, AsyncCallback<UserReport> callback);

		void createBeitragReport(int beitragID, Date date1, Date date2, AsyncCallback<BeitragReport> callback);

		  

}
