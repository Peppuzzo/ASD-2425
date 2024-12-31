/**
 *
 */
package it.unicam.cs.asdl2425.es9;

import java.util.List;

// TODO completare import

/**
 * Implementazione dell'algoritmo di Merge Sort integrata nel framework di
 * valutazione numerica. Non è richiesta l'implementazione in loco.
 *
 * @author Template: Luca Tesei, Implementazione: collettiva
 *
 */
public class MergeSort<E extends Comparable<E>> implements SortingAlgorithm<E> {

    public SortingAlgorithmResult<E> sort(List<E> l) {
        // TODO implementare

      if(l == null){
        throw new NullPointerException("Error: non è possibile passare come riferimento" +
          "liste null");
      }

      for(E elements : l){
        if(elements == null){
          throw new NullPointerException("Error: la lista non può contenere valori null!");
        }
      }

        return null;
    }

    public String getName() {
        return "MergeSort";
    }
}
