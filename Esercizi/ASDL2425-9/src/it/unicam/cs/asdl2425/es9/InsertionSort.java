package it.unicam.cs.asdl2425.es9;

import java.util.List;

// TODO completare import

/**
 * Implementazione dell'algoritmo di Insertion Sort integrata nel framework di
 * valutazione numerica. L'implementazione è in loco.
 *
 * @author Template: Luca Tesei, Implementazione: Collettiva
 *
 * @param <E>
 *                Una classe su cui sia definito un ordinamento naturale.
 */
public class InsertionSort<E extends Comparable<E>>
        implements SortingAlgorithm<E> {

    public SortingAlgorithmResult<E> sort(List<E> l) {
        // TODO implementare

      if (l == null) {
        throw new NullPointerException("Error: non sono ammesse liste null");
      }

      // Verifica se sono presenti valori null
      for(E element : l) {
        if(element == null){
          throw new NullPointerException("Error: non possono persistere elementi null nella lista");
        }
      }




        return null;
    }

    public String getName() {
        return "InsertionSort";
    }
}
