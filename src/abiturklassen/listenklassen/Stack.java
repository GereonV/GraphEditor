package abiturklassen.listenklassen;
/**
 * <p>
 * Materialien zu den zentralen NRW-Abiturpruefungen im Fach Informatik ab 2017.
 * </p>
 * <p>
 * Generische Klasse Stack<ContentType>
 * </p>
 * <p>
 * Objekte der generischen Klasse Stack (Stapel) verwalten beliebige
 * Objekte vom Typ ContentType nach dem Last-In-First-Out-Prinzip, d.h., das
 * zuletzt abgelegte Objekt wird als erstes wieder entnommen. Alle Methoden haben
 * eine konstante Laufzeit, unabhaengig von der Anzahl der verwalteten Objekte.
 * </p>
 * 
 * @author pardella, da die
 * Qualitaets- und UnterstuetzungsAgentur - Landesinstitut fuer Schule, 
 * Materialien zum schulinternen Lehrplan Informatik SII diese Klasse
 * wohl nicht zur Verfügung stellt.
 * @version Generisch_01 2016-10-03
 */
public class Stack<ContentType> {
	
	private List<ContentType> storage;
	
	/**
	 * Eine leerer Stack wird erzeugt. 
	 * Objekte, die in diesem Stack verwaltet werden, muessen vom Typ
	 * ContentType sein.
	 */
	public Stack() {
		storage = new List<ContentType>();
	}

	/**
	 * Die Anfrage liefert den Wert true, wenn der Stack keine Objekte enthaelt, 
	 * sonst liefert sie den Wert false.
	 * 
	 * @return true, falls der Stack leer ist, sonst false
	 */
	public boolean isEmpty() {
		return storage.isEmpty();
	}

	/**
	 * Das Objekt pContentType wird auf den Stack gelegt. 
	 * Falls pContentType gleich null ist, bleibt der Stack unveraendert.
	 * 
	 * @param pContent
	 *            das aufzustapelnde Objekt vom Typ ContentType
	 */
	public void push(ContentType pContent) {
		if (pContent != null) {
                        storage.toLast();
			storage.append(pContent);
		}
	}

	/**
	 * Das letzte Objekt wird vom Stapel genommen. 
	 * Falls der Stapel leer ist, wird er nicht veraendert.
	 */
	public void pop() {
		if (!this.isEmpty()) {
			storage.toLast();
                        storage.remove();
                        
		}
	}

	/**
	 * Die Anfrage liefert das letzte (oberste) Objekt des Stapels. 
	 * Der Stapel bleibt unveraendert. 
	 * Falls die Schlange leer ist, wird null zurueckgegeben.
	 *
	 * @return das letzte (oberste) Objekt des Stacks vom Typ ContentType oder null,
	 *         falls der Stack leer ist
	 */
	public ContentType top() {
		if (this.isEmpty()) {
			return null;
		} else {
                        storage.toLast();
			return storage.getContent();
		}
	}
}
