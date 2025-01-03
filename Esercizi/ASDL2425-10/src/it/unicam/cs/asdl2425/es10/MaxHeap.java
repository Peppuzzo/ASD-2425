package it.unicam.cs.asdl2425.es10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Classe che implementa uno heap binario che può contenere elementi non nulli
 * possibilmente ripetuti.
 *
 * @author Template: Luca Tesei, Implementation: collettiva
 *
 * @param <E>
 *                il tipo degli elementi dello heap, che devono avere un
 *                ordinamento naturale.
 */
public class MaxHeap<E extends Comparable<E>> {

    /*
     * L'array che serve come base per lo heap
     */
    private ArrayList<E> heap;

    /**
     * Costruisce uno heap vuoto.
     */
    public MaxHeap() {
        this.heap = new ArrayList<E>();
    }

    /**
     * Restituisce il numero di elementi nello heap.
     *
     * @return il numero di elementi nello heap
     */
    public int size() {
        return this.heap.size();
    }

    /**
     * Determina se lo heap è vuoto.
     *
     * @return true se lo heap è vuoto.
     */
    public boolean isEmpty() {
        return this.heap.isEmpty();
    }

    /**
     * Costruisce uno heap a partire da una lista di elementi.
     *
     * @param list
     *                 lista di elementi
     * @throws NullPointerException
     *                                  se la lista è nulla
     */
    public MaxHeap(List<E> list) {
        // TODO implementare
      if(list == null) {
        throw new NullPointerException("la lista passata è nulla!");
      }
      // copia di tutti gli elementi presenti in list
      this.heap = new ArrayList<>(list);

      // ricordina l'heap partendo da un nodo non foglia
      for(int i = (this.heap.size() / 2) - 1; i >= 0; i--){
        heapify(i);
      }
    }

    /**
     * Inserisce un elemento nello heap
     *
     * @param el
     *               l'elemento da inserire
     * @throws NullPointerException
     *                                  se l'elemento è null
     *
     */
    public void insert(E el) {
        // TODO implementare
      if(el == null){
        throw new NullPointerException("Non è possibile inserire valori null");
      }
      this.heap.add(el); // inserimento del valore corrente.
    }

    /*
     * Funzione di comodo per calcolare l'indice del figlio sinistro del nodo in
     * posizione i. Si noti che la posizione 0 è significativa e contiene sempre
     * la radice dello heap.
     */
    private int leftIndex(int i) {
        return 2 + i +1;
    }

    /*
     * Funzione di comodo per calcolare l'indice del figlio destro del nodo in
     * posizione i. Si noti che la posizione 0 è significativa e contiene sempre
     * la radice dello heap.
     */
    private int rightIndex(int i) {
        return 2 * i + 2;
    }

    /*
     * Funzione di comodo per calcolare l'indice del genitore del nodo in
     * posizione i. Si noti che la posizione 0 è significativa e contiene sempre
     * la radice dello heap.
     */
    private int parentIndex(int i) {
      if( i == 0){
        throw new IllegalArgumentException("La radice non ha un genitore!");
      }
      return (i - 1) / 2;
    }

    /**
     * Ritorna l'elemento massimo senza toglierlo.
     *
     * @return l'elemento massimo dello heap oppure null se lo heap è vuoto
     */
    public E getMax() {
        // TODO implementare
        return null;
    }

    /**
     * Estrae l'elemento massimo dallo heap. Dopo la chiamata tale elemento non
     * è più presente nello heap.
     *
     * @return l'elemento massimo di questo heap oppure null se lo heap è vuoto
     */
    public E extractMax() {
        // TODO implementare
        return null;
    }

    /*
     * Ricostituisce uno heap a partire dal nodo in posizione i assumendo che i
     * suoi sottoalberi sinistro e destro (se esistono) siano heap.
     */
    private void heapify(int i) {
        // TODO implementare
      // il nodo corrente (si presumo che all'inizio sia il MAX)
      int indexCurrent = i;

      // confronto del figlio sinistro
      if(leftIndex(indexCurrent) < this.heap.size() && this.heap.
        get(leftIndex(indexCurrent)).compareTo(this.heap.
          get(indexCurrent)) > 0){
            indexCurrent = leftIndex(i); // aggiornamento del nuovo massimo
      }

      //confronto del figlio destro
      if(rightIndex(indexCurrent) < this.heap.size() && this.heap.
        get(indexCurrent).compareTo(this.heap.
          get(indexCurrent))  > 0){
            indexCurrent = rightIndex(i); // aggiornamento del massimo
      }

      if(indexCurrent != i){
        //scambio del nodo corrente con il più grande
        E temp = this.heap.get(i);
        this.heap.set(i, this.heap.get(indexCurrent));
        this.heap.set(indexCurrent, temp);
        // chiamata ricorsiva sui sottoalberi
        heapify(indexCurrent);
      }
    }

    /**
     * Only for JUnit testing purposes.
     *
     * @return the arraylist representing this max heap
     */
    protected ArrayList<E> getHeap() {
        return this.heap;
    }
}
