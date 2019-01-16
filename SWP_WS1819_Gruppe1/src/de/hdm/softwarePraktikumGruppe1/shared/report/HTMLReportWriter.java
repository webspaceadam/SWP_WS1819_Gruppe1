package de.hdm.softwarePraktikumGruppe1.shared.report;

import java.util.Vector;



/**
 * Ein <code>ReportWriter</code>, der Reports mittels HTML formatiert. Das im
 * Zielformat vorliegende Ergebnis wird in der Variable <code>reportText</code>
 * abgelegt und kann nach Aufruf der entsprechenden Prozessierungsmethode mit
 * <code>getReportText()</code> ausgelesen werden.
 * 
 * @author JakobBenkoe
 */


public class HTMLReportWriter extends ReportWriter{
	
	  /**
	   * Diese Variable wird mit dem Ergebnis einer Umwandlung (vgl.
	   * <code>process...</code>-Methoden) belegt. Format: HTML-Text
	   */
	  private String reportText = "";

	  /**
	   * Zurücksetzen der Variable <code>reportText</code>.
	   */
	  public void resetReportText() {
	    this.reportText = "";
	  }

	  /**
	   * Umwandeln eines <code>Paragraph</code>-Objekts in HTML.
	   * 
	   * @param p der Paragraph
	   * @return HTML-Text
	   */
	  public String paragraph2HTML(Paragraph p) {
	    if (p instanceof CompositeParagraph) {
	      return this.paragraph2HTML((CompositeParagraph) p);
	    }
	    else {
	      return this.paragraph2HTML((SimpleParagraph) p);
	    }
	  }
	  
	  
	  
	  /**
	   * Umwandeln eines <code>GenericReport</code>-Objekts in HTML.
	   * 
	   * @param r der Report
	   * @return HTML-Text
	   */
	  public String genericReport2HTML(SimpleReport r) {
		  StringBuffer result = new StringBuffer();
		  
		  result.append("<H2>" + r.getTitle() + "</H2>");
		  
		  Vector<Row> rows = r.getRows();;
		  
		  for (int i = 0; i < rows.size(); i++) {
			  Row row = rows.get(i);
			  Vector<Column> columns = row.getColumns();
			  result.append("<p>");
			  
			  	for (int i2 = 0; i2 < columns.size(); i2++) {
			  		Column column = columns.get(i2);
			  		if(i2>0)result.append("   |   ");
			  		result.append(column.getValue());			  		
			  	}
			  
			  result.append("</p>");
		  }
		  
		  
		  return result.toString();
	  }
	  
	  /**
	   * Umwandeln eines <code>CompositeParagraph</code>-Objekts in HTML.
	   * 
	   * @param p der CompositeParagraph
	   * @return HTML-Text
	   */
	  public String paragraph2HTML(CompositeParagraph p) {
	    StringBuffer result = new StringBuffer();

	    for (int i = 0; i < p.getNumParagraphs(); i++) {
	      result.append("<p>" + p.getParagraphAt(i) + "</p>");
	    }

	    return result.toString();
	  }

	  /**
	   * Umwandeln eines <code>SimpleParagraph</code>-Objekts in HTML.
	   * 
	   * @param p der SimpleParagraph
	   * @return HTML-Text
	   */
	  public String paragraph2HTML(SimpleParagraph p) {
	    return "<p>" + p.toString() + "</p>";
	  }

	  /**
	   * HTML-Header-Text produzieren.
	   * 
	   * @return HTML-Text
	   */
	  public String getHeader() {
	    StringBuffer result = new StringBuffer();

	    result.append("<html><head><title></title></head><body>");
	    return result.toString();
	  }

	  /**
	   * HTML-Trailer-Text produzieren.
	   * 
	   * @return HTML-Text
	   */
	  public String getTrailer() {
	    return "</body></html>";
	  }

	  
	  
	  
	  /**
	   * Prozessieren des übergebenen Reports und Ablage im Zielformat. Ein Auslesen
	   * des Ergebnisses kann später mittels <code>getReportText()</code> erfolgen.
	   * 
	   * @param r der zu prozessierende Report
	   */
	  @Override
	public void process(BeitragReport r) {
		    // Zunächst löschen wir das Ergebnis vorhergehender Prozessierungen.
		    this.resetReportText();
		    
		    /*
		     * In diesen Buffer schreiben wir während der Prozessierung sukzessive
		     * unsere Ergebnisse.
		     */
		    StringBuffer result = new StringBuffer();
		    result.append("<H2 class='is-size-4'>" + r.getTitle() + "</H2>");
		    result.append("<p> Report Erstellt am " + r.getCreated().toString() + "</p>");
		    result.append("<p>" + r.getImprint().toString() + "</p>");
		    result.append("<hr>");
		    result.append("<p>" + paragraph2HTML(r.getHeaderData()) + "</p>");
		    
		    

		    /*
		     * Nun werden Schritt für Schritt die einzelnen Bestandteile des Reports
		     * ausgelesen und in HTML-Form übersetzt.
		     */
		    
		    result.append("<hr>");
		  
		    for (int i = 0; i < r.getNumSubReports(); i++) {
		    	GenericReport genericReport = (GenericReport) r.getSubReportAt(i);
		    	result.append(genericReport2HTML(genericReport));
		    	result.append("<hr>");
		    }
		    
		    reportText = result.toString(); 
	  }

	  /**
	   * Prozessieren des übergebenen Reports und Ablage im Zielformat. Ein Auslesen
	   * des Ergebnisses kann später mittels <code>getReportText()</code> erfolgen.
	   * 
	   * @param r der zu prozessierende Report
	   */
	  @Override
	public void process(UserReport r) {
		    // Zunächst löschen wir das Ergebnis vorhergehender Prozessierungen.
		    this.resetReportText();
		    
		    /*
		     * In diesen Buffer schreiben wir während der Prozessierung sukzessive
		     * unsere Ergebnisse.
		     */
		    StringBuffer result = new StringBuffer();
		    result.append("<H2  class='is-size-4'>" + r.getTitle() + "</H2>");
		    result.append("<p> Report Erstellt am" + r.getCreated().toString() + "</p>");		    
		    result.append("<p>" + r.getImprint().toString() + "</p>");
		    result.append("<hr>");
		    result.append("<p>" + paragraph2HTML(r.getHeaderData()) + "</p>");

		    
		    /*
		     * Nun werden Schritt für Schritt die einzelnen Bestandteile des Reports
		     * ausgelesen und in HTML-Form übersetzt.
		     */
		    
		    result.append("<hr>");
		  
		    for (int i = 0; i < r.getNumSubReports(); i++) {
		    	GenericReport genericReport = (GenericReport) r.getSubReportAt(i);
		    	result.append(genericReport2HTML(genericReport));
		    	result.append("<hr>");
		    }
		    
		    reportText = result.toString();
		  
	  }

	  
	  
	  
	  /**
	   * Auslesen des Ergebnisses der zuletzt aufgerufenen Prozessierungsmethode.
	   * 
	   * @return ein String im HTML-Format
	   */
	  public String getReportText() {
	    return this.getHeader() + this.reportText + this.getTrailer();
	  }
	}