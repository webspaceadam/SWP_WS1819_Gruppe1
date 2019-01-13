package de.hdm.softwarePraktikumGruppe1.shared.report;



public abstract class ReportWriter {

	  /**
	   * Übersetzen eines <code>AllAccountsOfCustomerReport</code> in das
	   * Zielformat.
	   * 
	   * @param r der zu übersetzende Report
	   */
	  public abstract void process(BeitragReport r);

	  /**
	   * Übersetzen eines <code>AllAccountsOfAllCustomersReport</code> in das
	   * Zielformat.
	   * 
	   * @param r der zu übersetzende Report
	   */
	  public abstract void process(UserReport r);

	}
