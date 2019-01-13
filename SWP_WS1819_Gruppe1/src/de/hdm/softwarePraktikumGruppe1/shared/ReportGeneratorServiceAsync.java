/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.softwarePraktikumGruppe1.shared.report.BeitragReport;
import de.hdm.softwarePraktikumGruppe1.shared.report.UserReport;



/**
 * @author GianlucaBernert & JakobBenkoe
 * Interface das von ReportGeneratorServiceProxy Implementiert wird
 */
public interface ReportGeneratorServiceAsync {

		void init(AsyncCallback<Void> initReportGeneratorCallback);

		void createUserReport(AsyncCallback<UserReport> callback);

		void createBeitragReport(AsyncCallback<BeitragReport> callback);

		  

}
