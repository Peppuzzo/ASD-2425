/**
 *
 */
package it.unicam.cs.asdl2425.es3;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Un time slot è un intervallo di tempo continuo che può essere associato ad
 * una prenotazione. Gli oggetti della classe sono immutabili. Non sono ammessi
 * time slot che iniziano e finiscono nello stesso istante.
 *
 * @author Luca Tesei
 *
 */
public class TimeSlot implements Comparable<TimeSlot> {

    /**
     * Rappresenta la soglia di tolleranza da considerare nella sovrapposizione
     * di due Time Slot. Se si sovrappongono per un numero di minuti minore o
     * uguale a questa soglia allora NON vengono considerati sovrapposti.
     */
    public static final int MINUTES_OF_TOLERANCE_FOR_OVERLAPPING = 5;

    private final GregorianCalendar start;

    private final GregorianCalendar stop;

    /**
     * Crea un time slot tra due istanti di inizio e fine
     *
     * @param start
     *                  inizio del time slot
     * @param stop
     *                  fine del time slot
     * @throws NullPointerException
     *                                      se uno dei due istanti, start o
     *                                      stop, è null
     * @throws IllegalArgumentException
     *                                      se start è uguale o successivo a
     *                                      stop
     */
    public TimeSlot(GregorianCalendar start, GregorianCalendar stop) {

      if(start == null || stop == null)
        throw new NullPointerException("Error: L'inizio e la fine del TimeSlot non possono essere nulli!");

      if(start.after(stop) || start.equals(stop))
        throw new IllegalArgumentException("Error: Lo start non puo' essere maggiore o uguale dello stop.");


        this.start = start;
        this.stop = stop;
    }

    /**
     * @return the start
     */
    public GregorianCalendar getStart() {
        return start;
    }

    /**
     * @return the stop
     */
    public GregorianCalendar getStop() {
        return stop;
    }

    /*
     * Un time slot è uguale a un altro se rappresenta esattamente lo stesso
     * intervallo di tempo, cioè se inizia nello stesso istante e termina nello
     * stesso istante.
     */
    @Override
    public boolean equals(Object obj) {

      if (this == obj)
        return true;
      if (!(obj instanceof TimeSlot))
        return false;
      TimeSlot t = (TimeSlot) obj;
      if (getStart() == null) {
        if (t.getStart() != null)
          return false;
      } else if (!getStart().equals(t.getStart()))
        return false;
      if (getStop() == null) {
        if (t.getStop() != null)
          return false;
      } else if (!this.getStop().equals(t.getStop()))
        return false;
      return true;
    }

    /*
     * Il codice hash associato a un timeslot viene calcolato a partire dei due
     * istanti di inizio e fine, in accordo con i campi usati per il metodo
     * equals.
     */
    @Override
    public int hashCode() {

      int result = 13;

      result = 37 * result + (getStart() != null ? getStart().hashCode() : 0);
      result = 37 * result + (getStop() != null ? getStop().hashCode() : 0);

        return result;
    }

    /*
     * Un time slot precede un altro se inizia prima. Se due time slot iniziano
     * nello stesso momento quello che finisce prima precede l'altro. Se hanno
     * stesso inizio e stessa fine sono uguali, in compatibilità con equals.
     */
    @Override
    public int compareTo(TimeSlot o) {

      // controllo se l'inizio del calendario e' superiore a l'altro
      if(this.getStart().after(o.getStart()))
        return 1;

      // verifico se l'inizio e la fine tra i due calendari coincidono (e quindi sono uguali)
      if(this.start.compareTo(o.getStart()) == o.getStart().compareTo(this.getStart()) && this.stop.compareTo(o.stop) ==
      o.getStop().compareTo(this.stop))
        return 0;

      return -1;
    }

    /**
     * Determina il numero di minuti di sovrapposizione tra questo timeslot e
     * quello passato.
     *
     * @param o
     *              il time slot da confrontare con questo
     * @return il numero di minuti di sovrapposizione tra questo time slot e
     *         quello passato, oppure -1 se non c'è sovrapposizione. Se questo
     *         time slot finisce esattamente al millisecondo dove inizia il time
     *         slot <code>o</code> non c'è sovrapposizione, così come se questo
     *         time slot inizia esattamente al millisecondo in cui finisce il
     *         time slot <code>o</code>. In questi ultimi due casi il risultato
     *         deve essere -1 e non 0. Nel caso in cui la sovrapposizione non è
     *         di un numero esatto di minuti, cioè ci sono secondi e
     *         millisecondi che avanzano, il numero dei minuti di
     *         sovrapposizione da restituire deve essere arrotondato per difetto
     * @throws NullPointerException
     *                                      se il time slot passato è nullo
     * @throws IllegalArgumentException
     *                                      se i minuti di sovrapposizione
     *                                      superano Integer.MAX_VALUE
     */
    public int getMinutesOfOverlappingWith(TimeSlot o) {

      if(o == null)
        throw new NullPointerException("Error: parametro passato is null");

      if(this.getStop().getTimeInMillis() == o.getStart().getTimeInMillis())
        return -1;

      if(this.getStart().getTimeInMillis() == o.getStop().getTimeInMillis())
        return -1;


      return -1;
    }

    /**
     * Determina se questo time slot si sovrappone a un altro time slot dato,
     * considerando la soglia di tolleranza.
     *
     * @param o
     *              il time slot che viene passato per il controllo di
     *              sovrapposizione
     * @return true se questo time slot si sovrappone per più (strettamente) di
     *         MINUTES_OF_TOLERANCE_FOR_OVERLAPPING minuti a quello passato
     * @throws NullPointerException
     *                                  se il time slot passato è nullo
     */
    public boolean overlapsWith(TimeSlot o) {

      if(o == null)
        return false;



      return false;
    }

    /*
     * Ridefinisce il modo in cui viene reso un TimeSlot con una String.
     *
     * Esempio 1, stringa da restituire: "[4/11/2019 11.0 - 4/11/2019 13.0]"
     *
     * Esempio 2, stringa da restituire: "[10/11/2019 11.15 - 10/11/2019 23.45]"
     *
     * I secondi e i millisecondi eventuali non vengono scritti.
     */
    @Override
    public String toString() {

      return "[" + start.get(Calendar.DAY_OF_MONTH) + "/"
        + (start.get(Calendar.MONTH) + 1) + "/"
        + start.get(Calendar.YEAR) + " "
        + start.get(Calendar.HOUR_OF_DAY) + "."
        + start.get(Calendar.MINUTE) + " - "
        + stop.get(Calendar.DAY_OF_MONTH) + "/"
        + (stop.get(Calendar.MONTH) + 1) + "/" + stop.get(Calendar.YEAR)
        + " " + stop.get(Calendar.HOUR_OF_DAY) + "."
        + stop.get(Calendar.MINUTE) + "]";
    }
}
