package it.unicam.cs.asdl2425.es2;

/**
 * Un oggetto cassaforte con combinazione ha una manopola che può essere
 * impostata su certe posizioni contrassegnate da lettere maiuscole. La
 * serratura si apre solo se le ultime tre lettere impostate sono uguali alla
 * combinazione segreta.
 *
 * @author Luca Tesei
 */
public class CombinationLock {


  // La combinazione corretta della cassaforte
    private String combinationCurrent = "";

    private String charManopolaAttuale = "\u0000\u0000\u0000";

  // Stato attuale della cassaforte aperta/chiusa
    private boolean StatoCassaforte;

    /**
     * Costruisce una cassaforte <b>aperta</b> con una data combinazione
     *
     * @param aCombination
     *                         la combinazione che deve essere una stringa di 3
     *                         lettere maiuscole dell'alfabeto inglese
     * @throw IllegalArgumentException se la combinazione fornita non è una
     *        stringa di 3 lettere maiuscole dell'alfabeto inglese
     * @throw NullPointerException se la combinazione fornita è nulla
     */
    public CombinationLock(String aCombination) {

      // Eccezione per la verifica della combinazione passata che non sia NULL
      if (aCombination == null) {
        throw new NullPointerException("Error: la combinazione passata non puo' essere nulla.");
      }

      // Eccezione per la verifica che la combinazione passata non sia < di 3 caratteri
      if (aCombination.length() != 3) {
        throw new IllegalArgumentException("La combinazione deve essere lunga tre caratteri.");
      }

      // Controllo per ogni lettera nella combinazione
      for (int i = 0; i < aCombination.length(); i++) {
        char currentChar = aCombination.charAt(i);
        // In questo caso, verifichiamo se il carattere corertne abbia un valore ASCII compreso tra 65 e 90.
        boolean isUpperCase = (currentChar >= 'A' && currentChar <= 'Z'); // Verifica se la lettera è maiuscola

        // Se il carattere non è maiuscolo, lancia un'eccezione
        if (!isUpperCase) {
          throw new IllegalArgumentException("Error: non e' consentito che la combinazione abbia una lettera minuscola!");
        }
      }

      this.StatoCassaforte = true;
      this.combinationCurrent = aCombination; // Imposta la combinazione se tutti i controlli passano

    }

    /**
     * Imposta la manopola su una certa posizione.
     *
     * @param aPosition
     *                      un carattere lettera maiuscola su cui viene
     *                      impostata la manopola
     * @throws IllegalArgumentException
     *                                      se il carattere fornito non è una
     *                                      lettera maiuscola dell'alfabeto
     *                                      inglese
     */
    public void setPosition(char aPosition) {

        int index = 0; // indice di controllo fino alla lunghezza dell'array
        boolean flag = true;
        boolean isUppercase = (!(aPosition >= 'A' && aPosition <= 'Z')); // verifico se rientra nel range dell'alfabeto inglese

        if(isUppercase) {
          throw new IllegalArgumentException("Il carattere fornito deve essere una lettera maiuscola dell'alfabeto inglese!");
        }

        // Converti la stringa con le posizioni settate (se presenti) in un array di char
      char[] arrayChar = this.charManopolaAttuale.toCharArray();

        do{

          if(index < arrayChar.length){
            if(arrayChar[index] == '\u0000'){
              arrayChar[index] = aPosition;
              flag = false;
            }
          }

          index++;

        } while(flag);

        // creo una nuova stringa che abbia settato le lettere in una data posizione.
        this.charManopolaAttuale = new String(arrayChar);

    }

    /**
     * Tenta di aprire la serratura considerando come combinazione fornita le
     * ultime tre posizioni impostate. Se l'apertura non va a buon fine le
     * lettere impostate precedentemente non devono essere considerate per i
     * prossimi tentativi di apertura.
     */
    public void open() {

      // controllo se le due combinazioni del ladro e della cassaforte coincidono
        if(this.charManopolaAttuale.equals(this.combinationCurrent)){
          this.StatoCassaforte = true;
        }
        // non aprire la cassaforte e resetta la combinazione provata
        else {
          this.StatoCassaforte = false;
          this.charManopolaAttuale = "\u0000\u0000\u0000";
        }
    }

    /**
     * Determina se la cassaforte è aperta.
     *
     * @return true se la cassaforte è attualmente aperta, false altrimenti
     */
    public boolean isOpen() {
        return this.StatoCassaforte;
    }

    /**
     * Chiude la cassaforte senza modificare la combinazione attuale. Fa in modo
     * che se si prova a riaprire subito senza impostare nessuna nuova posizione
     * della manopola la cassaforte non si apre. Si noti che se la cassaforte
     * era stata aperta con la combinazione giusta le ultime posizioni impostate
     * sono proprio la combinazione attuale.
     */
    public void lock() {

      this.StatoCassaforte = false;

      // se la cassaforte è chiusa, resetta la combinazione della manopola attuale
      this.charManopolaAttuale = "\u0000\u0000\u0000";
    }

    /**
     * Chiude la cassaforte e modifica la combinazione. Funziona solo se la
     * cassaforte è attualmente aperta. Se la cassaforte è attualmente chiusa
     * rimane chiusa e la combinazione non viene cambiata, ma in questo caso le
     * lettere impostate precedentemente non devono essere considerate per i
     * prossimi tentativi di apertura.
     *
     * @param aCombination
     *                         la nuova combinazione che deve essere una stringa
     *                         di 3 lettere maiuscole dell'alfabeto inglese
     * @throw IllegalArgumentException se la combinazione fornita non è una
     *        stringa di 3 lettere maiuscole dell'alfabeto inglese
     * @throw NullPointerException se la combinazione fornita è nulla
     */
    public void lockAndChangeCombination(String aCombination) {

      if(this.StatoCassaforte){

        int count = 0;

        if(aCombination.length() != 3){
          throw new IllegalArgumentException("Error: La combinazione deve essere composta da tre caratteri");
        }

        do {

          if (!(aCombination.charAt(count) >= 'A' && aCombination.charAt(count) <= 'Z')) {
            throw new IllegalArgumentException("La string contiene una o piu' caratteri non " +
              "corrispondenti all'alfabeto inglese.");
          }
          count++;
        } while (count < 3);

        this.combinationCurrent = aCombination;
        this.StatoCassaforte = false;

      }
      this.charManopolaAttuale = "\u0000\u0000\u0000"; // si dovra' anche resettare la manopola attuale oltre la iniziale
    }
}
