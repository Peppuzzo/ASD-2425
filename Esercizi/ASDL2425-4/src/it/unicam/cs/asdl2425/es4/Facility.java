package it.unicam.cs.asdl2425.es4;

/**
 * Una facility generica è una caratteristica o delle dotazioni che una certa
 * aula può avere. La classe va specificata ulteriormente per definire i diversi
 * tipi di facilities.
 *
 * @author Template: Luca Tesei, Implementation: Collective
 *
 */
public abstract class Facility {

    private final String codice;

    private final String descrizione;

    /**
     * Costruisce una certa facility generica.
     *
     * @param codice
     *                        identifica la facility univocamente
     * @param descrizione
     *                        descrizione della facility
     * @throws NullPointerException
     *                                  se una qualsiasi delle informazioni
     *                                  richieste è nulla.
     */
    public Facility(String codice, String descrizione) {
        if(codice.equals(null) || descrizione.equals(null))
          throw new NullPointerException("Error: il costruttore non acetate informazioni di tipo NULL!");
        this.codice = codice;
        this.descrizione = descrizione;
    }

    /**
     * @return the codice
     */
    public String getCodice() {
        return codice;
    }

    /**
     * @return the descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /*
     * Poiché l'uguaglianza è basata sul codice, anche l'hashcode deve essere
     * basato sul codice
     */
    @Override
    public int hashCode() {

        int hash = 45;
        hash = 31 * hash + (getCodice() == null ? 0 : getCodice().hashCode());

        return hash;
    }

    /*
     * L'uguaglianza di due facilities è basata unicamente sul codice
     */
    @Override
    public boolean equals(Object obj) {

      if(this == obj)
        return true;

      if(obj instanceof PresenceFacility){
        PresenceFacility other = (PresenceFacility) obj;
        return this.codice != null && other.getCodice() != null ? this.codice.equals(other.getCodice()) : false;
      } else if (obj instanceof QuantitativeFacility) {
        QuantitativeFacility other = (QuantitativeFacility) obj;
        return this.codice != null && other.getCodice() != null ? this.codice.equals(other.getCodice()) : false;
      }

      return false;
    }

    @Override
    public String toString() {
        return "Facility [codice=" + codice + ", descrizione=" + descrizione
                + "]";
    }

    /**
     * Determina se questa facility soddisfa un'altra facility data. Ad esempio
     * se questa facility indica che è presente un proiettore HDMI, allora essa
     * soddisfa la facility "presenza di un proiettore HDMI". Un altro esempio:
     * se questa facility indica un numero di posti a sedere pari a 30, allora
     * essa soddisfa ogni altra facility che indica che ci sono un numero di
     * posti minore o uguale a 30. Il metodo dipende dal tipo di facility, per
     * questo è astratto e va definito nelle varie sottoclassi.
     *
     * @param o
     *              l'altra facility con cui determinare la compatibilità
     * @return true se questa facility soddisfa la facility passata, false
     *         altrimenti
     * @throws NullPointerException
     *                                  se la facility passata è nulla.
     */
    public abstract boolean satisfies(Facility o);

}
