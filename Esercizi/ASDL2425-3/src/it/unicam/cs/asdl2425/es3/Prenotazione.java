package it.unicam.cs.asdl2425.es3;

/**
 * Una prenotazione riguarda una certa aula per un certo time slot.
 *
 * @author Luca Tesei
 *
 */
public class Prenotazione implements Comparable<Prenotazione> {

    private final String aula;

    private final TimeSlot timeSlot;

    private String docente;

    private String motivo;

    /**
     * Costruisce una prenotazione.
     *
     * @param aula
     *                     l'aula a cui la prenotazione si riferisce
     * @param timeSlot
     *                     il time slot della prenotazione
     * @param docente
     *                     il nome del docente che ha prenotato l'aula
     * @param motivo
     *                     il motivo della prenotazione
     * @throws NullPointerException
     *                                  se uno qualsiasi degli oggetti passati è
     *                                  null
     */
    public Prenotazione(String aula, TimeSlot timeSlot, String docente,
            String motivo) {
        if(aula == null || timeSlot == null || docente == null || motivo == null){
          throw  new NullPointerException("Error: Non e' consentito avere caratteri nulli");
        }
        this.aula = aula;
        this.timeSlot = timeSlot;
        this.docente = docente;
        this.motivo = motivo;
    }

    /**
     * @return the aula
     */
    public String getAula() {
        return aula;
    }

    /**
     * @return the timeSlot
     */
    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    /**
     * @return the docente
     */
    public String getDocente() {
        return docente;
    }

    /**
     * @return the motivo
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * @param docente
     *                    the docente to set
     */
    public void setDocente(String docente) {
        this.docente = docente;
    }

    /**
     * @param motivo
     *                   the motivo to set
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /*
     * Due prenotazioni sono uguali se hanno la stessa aula e lo stesso time
     * slot. Il docente e il motivo possono cambiare senza influire
     * sull'uguaglianza.
     */
    @Override
    public boolean equals(Object obj) {

      if (this == obj)
        return true;
      if (!(obj instanceof Prenotazione))
        return false;
      Prenotazione other = (Prenotazione) obj;
      if (aula == null) {
        if (other.aula != null)
          return false;
      } else if (!aula.equals(other.aula))
        return false;
      if (timeSlot == null) {
        if (other.timeSlot != null)
          return false;
      } else if (!timeSlot.equals(other.timeSlot))
        return false;
      return true;

    }

    /*
     * L'hashcode di una prenotazione si calcola a partire dai due campi usati
     * per equals.
     */
    @Override
    public int hashCode() {

      int result = 17; // utilizzeremo un numero primo per evitare possibili collisioni con il valore finale

      // calcolo dell'Hashcode in entrambi i casi della prenotazione.
      result = 31 * result + (getAula() != null ? getAula().hashCode() : 0);
      result = 31 * result + (getTimeSlot() != null ? getTimeSlot().hashCode() : 0);

      return result;
    }

    /*
     * Una prenotazione precede un altra in base all'ordine dei time slot. Se
     * due prenotazioni hanno lo stesso time slot allora una precede l'altra in
     * base all'ordine tra le aule.
     */
    @Override
    public int compareTo(Prenotazione o) {

      // Confronta i time slot
      int timeSlotComparison = this.timeSlot.compareTo(o.timeSlot);
      if (timeSlotComparison != 0) {
        return timeSlotComparison; // Se sono diversi, restituisci il risultato del confronto
      }

      // Se i time slot sono uguali, confronta le aule
      return this.aula.compareTo(o.aula); // Confronto delle aule
    }

    @Override
    public String toString() {
        return "Prenotazione [aula = " + aula + ", time slot =" + timeSlot
                + ", docente=" + docente + ", motivo=" + motivo + "]";
    }

}
