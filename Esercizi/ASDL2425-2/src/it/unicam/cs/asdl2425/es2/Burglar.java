package it.unicam.cs.asdl2425.es2;

/**
 * Uno scassinatore è un oggetto che prende una certa cassaforte e trova la
 * combinazione utilizzando la "forza bruta".
 *
 * @author Luca Tesei
 *
 */
public class Burglar {

  CombinationLock combination;

  // tentativi di scassinatura fatti
  int tentativi = 0;


    /**
     * Costruisce uno scassinatore per una certa cassaforte.
     *
     * @param aCombinationLock
     * @throw NullPointerException se la cassaforte passata è nulla
     */
    public Burglar(CombinationLock aCombinationLock) {
      if(aCombinationLock.equals(null))
        throw new NullPointerException("Errore: la combinazione passata non puo' essere nulla.");

      this.combination = aCombinationLock;
    }

    /**
     * Forza la cassaforte e restituisce la combinazione.
     *
     * @return la combinazione della cassaforte forzata.
     */
    public String findCombination() {

      this.combination.lock();

      // i caratteri da dover confrontare per ogni combinazione
      char car1, car2, car3;

      for(car1 = 'A'; car1 <= 'Z'; car1++){
        for(car2 = 'A'; car2 <= 'Z'; car2++){
          for(car3 = 'A'; car3 <= 'Z'; car3++){
            this.combination.setPosition(car1);
            this.combination.setPosition(car2);
            this.combination.setPosition(car3);
            this.tentativi++;
            this.combination.open();
            if(this.combination.isOpen()){
              // visto che siano in complessità O(n^3), per non sprecare memoria utilizzeremo un buffer di stringhe
              StringBuffer combinationFinal = new StringBuffer();
              combinationFinal.append(car1);
              combinationFinal.append(car2);
              combinationFinal.append(car3);
              return combinationFinal.toString();
            }
          }
        }
      }
      return "";
    }

    /**
     * Restituisce il numero di tentativi che ci sono voluti per trovare la
     * combinazione. Se la cassaforte non è stata ancora forzata restituisce -1.
     *
     * @return il numero di tentativi che ci sono voluti per trovare la
     *         combinazione, oppure -1 se la cassaforte non è stata ancora
     *         forzata.
     */
    public long getAttempts() {
        return this.tentativi;
    }
}
