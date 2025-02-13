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
      if(list == null) {
        throw new NullPointerException("la lista passata è nulla!");
      }

      // copia di tutti gli elementi presenti in list
      this.heap = new ArrayList<>(list);

      // riordina l'heap partendo da un nodo non foglia in MaxHeap
      for (int i = (this.heap.size() / 2) - 1; i >= 0; i--) {
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
      if(el == null){
        throw new NullPointerException("Non è possibile inserire valori null");
      }

      this.heap.add(el); // inserimento del valore corrente.

      // indice dell'ultimo elemento inserito
      int currentIndex  = this.heap.size() - 1;

      while(currentIndex > 0 && this.heap.get(currentIndex).
        compareTo(this.heap.
          get(parentIndex(currentIndex))) > 0) {
             //scambio dell'elemento corrente con il genitore
             E positionTemp = this.heap.get(currentIndex);
             this.heap.set(currentIndex , this.heap.get(parentIndex(currentIndex)));
             this.heap.set(parentIndex(currentIndex), positionTemp);

        //aggiorno l'indice del nuovo parent
        currentIndex = parentIndex(currentIndex);
      }
    }

    /*
     * Funzione di comodo per calcolare l'indice del figlio sinistro del nodo in
     * posizione i. Si noti che la posizione 0 è significativa e contiene sempre
     * la radice dello heap.
     */
    private int leftIndex(int i) {
        return 2 * i + 1;
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
      return (i - 1) / 2;
    }

    /**
     * Ritorna l'elemento massimo senza toglierlo.
     *
     * @return l'elemento massimo dello heap oppure null se lo heap è vuoto
     */
    public E getMax() {
     if(this.heap.isEmpty()) {
       return null;
     }
     return this.heap.getFirst();
    }

    /**
     * Estrae l'elemento massimo dallo heap. Dopo la chiamata tale elemento non
     * è più presente nello heap.
     *
     * @return l'elemento massimo di questo heap oppure null se lo heap è vuoto
     */
    public E extractMax() {
      if(this.heap.isEmpty()){
        return null;
      }

      E maxElement = this.getMax();

      // rimuove l'ultimo elemento presente nella lista
      E lastElement = this.heap.remove(this.heap.size() - 1);

      if(!this.heap.isEmpty()){
        this.heap.set(0, lastElement);
        heapify(0);
      }

      return maxElement;
    }

    /*
     * Ricostituisce uno heap a partire dal nodo in posizione i assumendo che i
     * suoi sottoalberi sinistro e destro (se esistono) siano heap.
     */
    private void heapify(int i) {
      int indexCurrent = i; // il nodo corrente (si presumo che all'inizio sia il MAX)
      int left = leftIndex(i);
      int right = rightIndex(i);

      // Confronto con il figlio sinistro
      if (left < this.heap.size() && this.heap.get(left).compareTo(this.heap.get(indexCurrent)) > 0) {
        indexCurrent = left;
      }

      // Confronto con il figlio destro
      if (right < this.heap.size() && this.heap.get(right).compareTo(this.heap.get(indexCurrent)) > 0) {
        indexCurrent = right;
      }

      if(indexCurrent != i){
        //scambio del nodo corrente con il più grande
        E temp = this.heap.get(i);
        this.heap.set(i, this.heap.get(indexCurrent));
        this.heap.set(indexCurrent, temp);
        // chiamata ricorsiva sui sotto-alberi
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
