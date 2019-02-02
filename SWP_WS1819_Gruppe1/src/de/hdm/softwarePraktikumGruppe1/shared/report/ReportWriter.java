package de.hdm.softwarePraktikumGruppe1.shared.report;

/**
 * 
 * @author Thies
 *
 */

public abstract class ReportWriter {

	  /**
	   * Übersetzen eines <code>BeitragReport</code> in das
	   * Zielformat.
	   * 
	   * @param r der zu übersetzende Report
	   */
	  public abstract void process(BeitragReport r);

	  /**
	   * Übersetzen eines <code>UserReport</code> in das
	   * Zielformat.
	   * 
	   * @param r der zu übersetzende Report
	   */
	  public abstract void process(UserReport r);

	}
