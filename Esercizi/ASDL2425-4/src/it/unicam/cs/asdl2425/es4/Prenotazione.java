package it.unicam.cs.asdl2425.es4;

/**
 * Una prenotazione riguarda una certa aula per un certo time slot.
 *
 * @author Template: Luca Tesei, Implementation: Collective
 *
 */
public class Prenotazione implements Comparable<Prenotazione> {

    private final Aula aula;

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
    public Prenotazione(Aula aula, TimeSlot timeSlot, String docente,
            String motivo) {

      if(aula == null || timeSlot == null || docente == null || motivo == null)
        throw new NullPointerException("I parametri passati al costruttore non possono essere nulli!");

      this.aula = aula;
      this.timeSlot = timeSlot;
      this.docente = docente;
      this.motivo = motivo;
    }

    /**
     * @return the aula
     */
    public Aula getAula() {
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
     * @param docente the docente to set
     */
    public void setDocente(String docente) {
        this.docente = docente;
    }

    /**
     * @param motivo the motivo to set
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public int hashCode() {

        int result = 11; // si utilizzerà un numero primo per evitare possibili collisioni.

        result = 31 * result + (getAula() != null ? getAula().hashCode() : 0);
        result = 31 * result + (getTimeSlot() != null ? getTimeSlot().hashCode() : 0);

        return result;
    }

    /*
     * L'uguaglianza è data solo da stessa aula e stesso time slot. Non sono
     * ammesse prenotazioni diverse con stessa aula e stesso time slot.
     */
    @Override
    public boolean equals(Object obj) {

      if (this == obj) return true; // Se i riferimenti sono uguali
      if (!(obj instanceof Prenotazione)) return false; // Controlla se obj è di tipo Prenotazione

      Prenotazione other = (Prenotazione) obj; // Effettua il cast in modo sicuro
      return this.aula.equals(other.getAula()) && this.getTimeSlot().equals(other.getTimeSlot());
    }

    /*
     * Una prenotazione precede un altra in base all'ordine dei time slot. Se
     * due prenotazioni hanno lo stesso time slot allora una precede l'altra in
     * base all'ordine tra le aule.
     */
    @Override
    public int compareTo(Prenotazione o) {

      int TimeSlotComparison = this.getTimeSlot().compareTo(o.getTimeSlot());

      if (TimeSlotComparison != 0)
        return TimeSlotComparison;

      return this.getAula().compareTo(o.getAula());
    }

    @Override
    public String toString() {
        return "Prenotazione [aula = " + aula + ", time slot =" + timeSlot
                + ", docente=" + docente + ", motivo=" + motivo + "]";
    }

}
