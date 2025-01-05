/**
 *
 */
package it.unicam.cs.asdl2425.es10;

import java.util.List;

/**
 * Classe che implementa un algoritmo di ordinamento basato su heap.
 *
 * @author Template: Luca Tesei, Implementation: collettiva
 *
 */
public class HeapSort<E extends Comparable<E>> implements SortingAlgorithm<E> {

    @Override
    public SortingAlgorithmResult<E> sort(List<E> l) {
        // TODO implementare - Nota: usare una variante dei metodi della classe
        // MaxHeap in modo da implementare l'algoritmo utilizzando solo un array
        // (arraylist) e alcune variabili locali di appoggio (implementazione
        // cosiddetta "in loco" o "in place", si veda
        // https://it.wikipedia.org/wiki/Algoritmo_in_loco)
      if(l == null){
        throw new NullPointerException("Impossibile ordinare la lista se è null!");
      }


      int countCompare = 0;

      int lengthOriginal = l.size(); // per pura efficienza e sicurezza

      // costruzione del MaxHeap (a partire dai nodi non foglia)
      for(int i = (lengthOriginal / 2) - 1; i >= 0; i--){
        heapify(l, lengthOriginal, i);
      }

      // estrazione del massimo e aggiunto alla fine della lista
      for(int i = lengthOriginal - 1; i > 0; i--){
        E temp = l.get(0);
        l.set(0, l.get(i));
        l.set(i, temp);

        // costruzione del MaxHeap sulla porzione rimanente
        countCompare += heapify(l, i, 0);
      }
      // in loco, return la stessa struttura dati ma sovrascritta con il nuovo output
      return new SortingAlgorithmResult<E>(l, countCompare);

    }

  private int heapify(List<E> l, int lenghtOriginal, int i) {
      int largest = i;
      int left = 2 * i + 1;
      int right = 2 * i + 2;
      int count = 0;

    // confronto con il figlio sinistro
    if(left < lenghtOriginal){
      count++;
      if(l.get(left).compareTo(l.get(largest)) > 0){
        largest = left;
      }
    }

    // confronto con il destro sinistro
    if(right < lenghtOriginal){
      count++;
      if(l.get(right).compareTo(l.get(largest)) > 0){
        largest = right;
      }
    }

    // se il nodo corrente non è il massimo, scambia e continua
    if(largest != i) {
      E temp = l.get(i);
      l.set(i, l.get(largest));
      l.set(largest, temp);

      //ricorsivamente si aggiungono i confronti
      count += heapify(l, lenghtOriginal, largest);
    }
    return count;
  }

  @Override
    public String getName() {
        return "HeapSort";
    }

}
