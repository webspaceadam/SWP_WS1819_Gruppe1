package de.hdm.softwarePraktikumGruppe1.shared.report;

import java.util.Vector;

/**
 * <p>
 * Ein einfacher Report, der neben den Informationen der Superklasse <code>
 * Report</code> eine Tabelle mit "Positionsdaten" aufweist. Die Tabelle greift
 * auf zwei Hilfsklassen namens <code>Row</code> und <code>Column</code> zurück.
 * </p>
 * 
 * @see Row
 * @see Column
 * 
 * @author Thies
 * @author JakobBenkoe
 */
public abstract class SimpleReport extends Report {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * Tabelle mit Positionsdaten. Die Tabelle wird zeilenweise in diesem
   * <code>Vector</code> abgelegt.
   */
  private Vector<Row> table = new Vector<Row>();

  /**
   * Hinzufügen einer Zeile.
   * 
   * @param r die hinzuzufügende Zeile
   */
  public void addRow(Row r) {
    this.table.addElement(r);
  }

  /**
   * Entfernen einer Zeile.
   * 
   * @param r die zu entfernende Zeile.
   */
  public void removeRow(Row r) {
    this.table.removeElement(r);
  }

  /**
   * Auslesen sämtlicher Positionsdaten.
   * 
   * @return die Tabelle der Positionsdaten
   */
  public Vector<Row> getRows() {
    return this.table;
  }
}