/**
 *
 */
package it.unicam.cs.asdl2425.es9;

import java.util.List;

// TODO completare import

/**
 * Implementazione del QuickSort con scelta della posizione del pivot fissa.
 * L'implementazione è in loco.
 *
 * @author Template: Luca Tesei, Implementazione: collettiva
 * @param <E>
 *                il tipo degli elementi della sequenza da ordinare.
 *
 */
public class QuickSort<E extends Comparable<E>> implements SortingAlgorithm<E> {

    @Override
    public SortingAlgorithmResult<E> sort(List<E> l) {
        // TODO implementare

      if(l == null)
        throw new NullPointerException("Error: non è possibile passare come parametri liste null!");

      for(E elements : l){
        if(elements == null){
          throw new NullPointerException("Error: la lista contiene valori null!");
        }
      }



      return null;
    }

    @Override
    public String getName() {
        return "QuickSort";
    }

}
