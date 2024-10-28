/**
 *
 */
package it.unicam.cs.asdl2425.es4;

import java.util.GregorianCalendar;
import java.util.Calendar;

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

      if(start.equals(null) || stop.equals(null))
        throw new NullPointerException("Error: Lo start iniziale del calendario non puo' essere nullo!");
      else if (start.equals(stop) || start.after(stop)) {
        throw new IllegalArgumentException("Error: Lo start del calendario non puo' essere successivo o uguale a stop.!");
      }

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

      int number = 3;

      // calcolo l'hashcode di un determinato timeslot a partire dai due istanti di tempo
      number = 31 * number + (getStart() != null && getStop() != null ? this.start.hashCode() + this.stop.hashCode(): 0);

      return number;
    }

    /*
     * Un time slot precede un altro se inizia prima. Se due time slot iniziano
     * nello stesso momento quello che finisce prima precede l'altro. Se hanno
     * stesso inizio e stessa fine sono uguali, in compatibilità con equals.
     */
    @Override
    public int compareTo(TimeSlot o) {

      // controllo se l'inizio del calendario è superiore a l'altro
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

      if(o.equals(null))
        throw new NullPointerException("Error: impossibile calcolare il numero di minuti di sovrapposizione" +
          "con un TimeSlot NULL");

      // Controllo tutti i possibili casi di sovrapposizione
      int svr1 = this.start.compareTo(o.getStart());
      int svr2 = this.start.compareTo(o.getStop());
      int svr3 = this.stop.compareTo(o.getStart());
      int svr4 = this.stop.compareTo(o.getStop());

      long overlappingMilliseconds;

      if(svr1 <= 0 && svr3 >= 0 && svr4 <= 0){
        // Questo timeslot inizia prima di quello passato e termina dopo che
        // quello passato è iniziato
        // this.start ... [o.start ... this.stop] ... o.stop

        overlappingMilliseconds = this.getStop().getTimeInMillis() - o.getStart().getTimeInMillis();

        return computeMinutes(overlappingMilliseconds);

      } else if (svr1 <= 0 && svr4 >= 0) {
        // Questo timeslot inizia prima di quello passato e termina dopo che
        // quello passato è finito
        // this.start ... [o.start ... o.stop] ... this.stop
        overlappingMilliseconds = o.stop.getTimeInMillis()
          - o.start.getTimeInMillis();
        return computeMinutes(overlappingMilliseconds);

      } else if (svr1 >= 0 && svr2 <= 0 && svr4 >= 0) {
        // Questo timeslot inizia dopo di quello passato e termina dopo che
        // quello passato è terminato
        // o.start ... [this.start ... o.stop] ... this.stop
        overlappingMilliseconds = o.stop.getTimeInMillis()
          - this.start.getTimeInMillis();
        return computeMinutes(overlappingMilliseconds);

      } else if (svr1 >= 0 && svr4 <= 0) {
        // Questo timeslot inizia prima di quello passato e termina dopo che
        // quello passato è finito
        // o.start ... [this.start ... this.stop] ... o.stop
        overlappingMilliseconds = this.stop.getTimeInMillis()
          - this.start.getTimeInMillis();
        return computeMinutes(overlappingMilliseconds);
      }

      // non c'è sovrapposizione
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
      if(o.equals(null))
        throw new NullPointerException("Error: il time slot passato è nullo");

      int minutes = this.getMinutesOfOverlappingWith(o);

      if(minutes == -1 || minutes <= MINUTES_OF_TOLERANCE_FOR_OVERLAPPING)
        // non c'è sovrapposizione oppure la soglia non è superata
        return false;

      // esiste la sovrapposizione
      return true;
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
        return String.format("[%s - %s]", formatCalendar(getStart()), formatCalendar(getStop()));
    }


  /**
   * Calcola il numero di minuti di sovrapposizione a partire dai millisecondi
   * facendo il troncamento. Gestisce il caso particolare in cui il numero di
   * millisecondi passati è 0.
   *
   * @throws IllegalArgumentException
   *           se il numero di minuti è troppo grande per un int.
   *
   * @return <code> (int) truncatedOverlappingMinutes </code>
   *                      il cast esplicito per il numero di minuti troncati.
   */
  private int computeMinutes(long overlappingMilliseconds) {
    // Caso particolare di 0 millisecondi di sovrapposizione, da considerare
    // non sovrapposizione.
    if (overlappingMilliseconds == 0)
      return -1;
    // la divisione intera tra long butta via i decimali
    // 60000 millisecondi = 1 minuto
    long truncatedOverlappingMinutes = overlappingMilliseconds / 60000;
    if (truncatedOverlappingMinutes > Integer.MAX_VALUE)
      throw new IllegalArgumentException(
        "Numero di minuti di sovrapposizione troppo grande per un int");

    return (int) truncatedOverlappingMinutes;
  }


  /**
   * Il formato del nuovo calendario.
   *
   * @param cal il calendario di riferimento
   * @return il formato per il calendario di riferimento
   */
  private String formatCalendar(Calendar cal){
      return String.format("%d/%d/%d %d.%d",
        cal.get(Calendar.DAY_OF_MONTH),
        cal.get(Calendar.MONTH) + 1,
        cal.get(Calendar.YEAR),
        cal.get(Calendar.HOUR_OF_DAY),
        cal.get(Calendar.MINUTE));
  }

}
