/**
 *
 */
package it.unicam.cs.asdl2425.pt1;

import java.util.List;

/**
 * Classe che implementa un algoritmo di ordinamento basato su uno heap
 * ternario. Usa una variante dei metodi di un MaxHeap ternario in modo da
 * implementare l'algoritmo utilizzando solo un array (arraylist) e alcune
 * variabili locali di appoggio (implementazione cosiddetta "in loco" o "in
 * place", si veda https://it.wikipedia.org/wiki/Algoritmo_in_loco)
 *
 * Uno heap ternario è uno heap in cui ogni nodo ha tre figli e non due, come in
 * uno heap binario. La strategia di rappresentazione e i metodi di inserimento
 * / estrazione del minimo / heapify devono essere adattati al caso di tre
 * figli, ma algoritmicamente sono analoghi.
 *
 * Lo heap ternario deve essere pensato in modo che accetti elementi ripetuti e
 * non accetti elementi null.
 *
 * @author Luca Tesei (template) GIUSEPPE, CALABRESE
 *         giusepp.calabrese@studenti.unicam.it (implementazione)
 *
 */
public class Heap3Sort<E extends Comparable<E>> implements SortingAlgorithm<E> {

  private int heapTreeSize; // l'unghezza dell'HeapTree
  private int compareInt; // numero di volte in cui viene eseguito uno scambio


    @Override
    public SortingAlgorithmResult<E> sort(List<E> l) {
      if(l == null) throw new NullPointerException();

      setLengthHeap(l);
      this.compareInt = 0;
      // Costruzione del Heap ternario
      for(int i = (getLengthHeap() / 3) - 1; i >= 0; i--) {
        heapify(l, i);
      }
      // Ordinamento dell'Heap ternario
      for(int i = getLengthHeap() - 1; i > 0; i--){
        E element = l.get(i);
        l.set(i, l.get(0));
        l.set(0, element);
        this.heapTreeSize--;
        heapify(l, 0);
      }
      return new SortingAlgorithmResult<E>(l, this.compareInt);
    }


    private void heapify(List<E> l, int i) {
      int maxIndex = i;

      // Caso in cui si confronta il figlio sinistro
      if(getLeftIndex(i) < getLengthHeap() && l.get(getLeftIndex(i)).
        compareTo(l.get(maxIndex)) > 0) {
        maxIndex = getLeftIndex(i);
      }
      // Caso in cui si confronta il figlio centrale
      if(getCentralIndex(i) < getLengthHeap() && l.get(getCentralIndex(i)).
        compareTo(l.get(maxIndex)) > 0) {
        maxIndex = getCentralIndex(i);
      }
      // Caso in cui si confronta il figlio destro
      if(getRightIndex(i) < getLengthHeap() && l.get(getRightIndex(i)).
        compareTo(l.get(maxIndex)) > 0) {
        maxIndex = getRightIndex(i);
      }

      /* Altrimenti, il max sarà "i" stesso ed in questo caso non viene scambiato
      *  nulla, oppure uno dei suoi 3 figli è in quest'ultimo caso, e vengono
      *  scambiati con l'elemento "i"
      */
      if(maxIndex != i){
        this.compareInt++;
        E element = l.get(i);
        l.set(i, l.get(maxIndex));
        l.set(maxIndex, element);
        heapify(l, maxIndex);
      }
    }

    @Override
    public String getName() {
        return "Heap3Sort";
    }

    /*
     * // TODO inserire eventuali metodi privati di comodo per calcolare
     * l'indice del primo, secondo e terzo di un nodo in posizione i. Si noti
     * che la posizione 0 è significativa e contiene sempre la radice dello
     * heap.
     */

  /**
   * Metodo privato per il calcolo dell'indice per il figlio sinistro
   *
   * @author Giuseppe Calabrese
   * @return l'indice corrispondente del figlio sinistro
   */
  private int getLeftIndex(int index){
    return 3 * index + 1;
  }

  /**
   * Metodo privato per il calcolo dell'indice per il figlio destro
   *
   * @author Giuseppe Calabrese
   *
   * @return l'indice corrispondente del figlio destro
   */
  private int getRightIndex(int index){
    return 3 * index + 3;
  }


  /**
   * Metodo privato per il calcolo dell'indice del figlio centrale, dal
   * momento in cui Heap è composto da 3 figli
   *
   * @author Giuseppe Calabrese
   *
   * @return l'indice del figlio centrale
   */
  private int getCentralIndex(int index){
    return 3 * index + 2;
  }

  /**
   * Metodo per leggere la lunghezza dell'Heap
   *
   * @author Giuseppe Calabrese
   *
   * @return il numero di lunghezza per questo HeapSortTree
   */
  private int getLengthHeap(){
    return this.heapTreeSize;
  }

  /**
   * Metodo per settare la lunghezza dell'Heap
   *
   *
   * @param l la lista come argomento
   */
  private void setLengthHeap(List l){
    this.heapTreeSize = l.size();
  }

}
