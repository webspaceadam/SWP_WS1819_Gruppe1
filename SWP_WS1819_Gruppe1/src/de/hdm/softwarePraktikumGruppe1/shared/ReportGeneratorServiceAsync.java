/**
 * 
 */
package de.hdm.softwarePraktikumGruppe1.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author GianlucaBernert & JakobBenkoe
 * Interface das von ReportGeneratorServiceProxy Implementiert wird
 */
public interface ReportGeneratorServiceAsync {

	void init(AsyncCallback<Void> initReportGeneratorCallback);



}
